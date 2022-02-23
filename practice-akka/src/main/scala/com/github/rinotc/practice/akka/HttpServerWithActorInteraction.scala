package com.github.rinotc.practice.akka

import akka.actor.typed.scaladsl.AskPattern.{Askable, schedulerFromActorSystem}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives
import akka.util.Timeout
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn

object HttpServerWithActorInteraction extends Directives with SprayJsonSupport with DefaultJsonProtocol {

  object Auction {
    sealed trait Message

    // 入札
    case class Bid(userId: String, offer: Int) extends Message

    case class GetBids(replyTo: ActorRef[Bids]) extends Message

    case class Bids(bids: List[Bid])

    def apply: Behaviors.Receive[Message] = apply(List.empty)

    def apply(bids: List[Bid]): Behaviors.Receive[Message] = Behaviors.receive {
      case (ctx, bid @ Bid(userId, offer)) =>
        ctx.log.info(s"Bid complete: $userId, $offer")
        apply(bids :+ bid)
      case (_, GetBids(replyTo)) =>
        replyTo ! Bids(bids)
        Behaviors.same
    }
  }

  implicit val bidFormat: RootJsonFormat[Auction.Bid]   = jsonFormat2(Auction.Bid)
  implicit val bidsFormat: RootJsonFormat[Auction.Bids] = jsonFormat1(Auction.Bids)

  def main(args: Array[String]): Unit = {

    import Auction._

    implicit val system: ActorSystem[Auction.Message]       = ActorSystem(Auction.apply, "auction")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val auction: ActorSystem[Auction.Message] = system

    val route = path("auction") {
      concat(
        put {
          parameters("bid".as[Int], "user") { (bid, user) =>
            auction ! Bid(user, bid)
            complete(StatusCodes.Accepted, "bid placed")
          }
        },
        get {
          implicit val timeout: Timeout = 5.seconds

          val bids: Future[Bids] = (auction ? GetBids).mapTo[Bids]
          complete(bids)
        }
      )
    }

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
