package dev.tchiba.onlineBookstore.rest.akka

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

object HttpClientSingleRequest {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing]               = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://localhost:8080/auction"))

    responseFuture
      .onComplete {
        case Failure(_)   => sys.error("something wrong!")
        case Success(res) => println(res)
      }

    Thread.sleep(5000)
  }
}
