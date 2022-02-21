package dev.tchiba.onlineBookstore.rest.akka

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.Directives.{complete, path}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object HttpServerRoutingMinimal {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing]               = ActorSystem(Behaviors.empty, "interface")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val route = path("hello") {
      Directives.get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    }

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind())                 // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done.
  }
}
