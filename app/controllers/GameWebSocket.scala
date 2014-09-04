package controllers

import ags.messages._
import play.api.libs.iteratee.{Iteratee, Concurrent}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.WebSocket
import ags.actors.Actors.{game, players}
import akka.pattern.ask
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
import play.api.libs.json._

/**
 * Created by sirkleber on 03/09/14.
 */
object GameWebSocket extends WebSocketController {
  private def failConn = Json.obj("connect" -> false, "description" -> "App connected")

  def consume = WebSocket.using[JsValue]{ req =>
    def ip = req.remoteAddress
    val (out, channel) = Concurrent.broadcast[JsValue]
    def in = Iteratee.foreach[JsValue]{ json =>
      (game ? GameMessage((json \ "game").asOpt[String])).map {
        case GameConnected => players ! GetPlayerData((json \ "player_id").as[Int], (json \ "data").as[JsValue])

        case GameCannotConnect =>
          channel push failConn
          channel eofAndEnd
      }
    } map { _ => game ! GameDisconnect}

    (in, out)
  }

}
