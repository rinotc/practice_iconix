package dev.tchiba.onlineBookstore.rest.akka

import akka.Done
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn

object SprayJsonExample extends SprayJsonSupport with DefaultJsonProtocol with Directives {

  // ルートを実行するために必要
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "SplayExample")

  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  var orders: List[Item] = Nil

  // domain model
  final case class Item(name: String, id: Long)
  final case class Order(items: List[Item])

  implicit val itemFormat: RootJsonFormat[Item]   = jsonFormat2(Item)
  implicit val orderFormat: RootJsonFormat[Order] = jsonFormat1(Order)

  // fake async database query
  def fetchItem(itemId: Long): Future[Option[Item]] = Future {
    orders.find { order => order.id == itemId }
  }

  def saveOrder(order: Order): Future[Done] = {
    orders = order match {
      case Order(items) => items ::: orders
      case _            => orders
    }
    Future { Done }
  }

  def main(args: Array[String]): Unit = {
    val getEndPoint = get {
      pathPrefix("item" / LongNumber) { id =>
        val maybeItem: Future[Option[Item]] = fetchItem(id)

        onSuccess(maybeItem) {
          case Some(item) => complete(item)
          case None       => complete(StatusCodes.NotFound)
        }
      }
    }

    val postEndPoint = post {
      path("create-order") {
        entity(as[Order]) { order =>
          val saved: Future[Done] = saveOrder(order)
          onSuccess(saved) { _ =>
            complete("order created")
          }
        }
      }
    }

    val route: Route = concat(getEndPoint, postEndPoint)

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind())                 // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
