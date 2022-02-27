package com.github.rinotc.practice.akka

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object HttpServerLowLevel {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing]               = ActorSystem(Behaviors.empty, "lowLevel")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val requestHandler: HttpRequest => HttpResponse = {
      case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
        HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, "<html><body>Hello world!</body></html>"))
      case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
        HttpResponse(entity = "PONG!")
      case HttpRequest(GET, Uri.Path("/crash"), _, _, _) =>
        sys.error("BOOM!")
      case r: HttpRequest =>
        r.discardEntityBytes() // important to drain incoming HTTP Entity stream
        HttpResponse(404, entity = "Unknown resource")
    }

    val bindingFuture = Http().newServerAt("localhost", 8080).bindSync(requestHandler)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind())                 // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
