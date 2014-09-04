package controllers

/**
 * Created by sirkleber on 30/08/14.
 */

import play.api.libs.iteratee.{Iteratee, Concurrent}
import play.api.libs.json.JsValue
import play.api.mvc.Action
import akka.pattern.ask
import ags.actors.Actors
import ags.messages._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import play.api.mvc.WebSocket
import models.Key
import Actors.players

import scala.concurrent.Future

object ControllerWebSocket extends WebSocketController {

  def connect = Action.async { req =>
    (players ? PlayerConnect(req.remoteAddress)).mapTo[(Unit, Int)].map {
      case ((_, id)) => Ok(Json.obj("id" -> id)) as jsonApp
    }
  }

  def disconnect = Action.async { req =>
    (players ? PlayerDisconnect(req.remoteAddress)).mapTo[(Unit, String)].map { _ =>
      Ok(Json.obj("disconnect" -> true)) as jsonApp
    }
  }

  //WebSocket
  def command(id: Int) = WebSocket.using[JsValue] { reqHeader =>
    def ip = reqHeader.remoteAddress
    val (out, channel) = Concurrent.broadcast[JsValue]

    def in = Iteratee.foreach[JsValue] { json =>
      (players ? PlayerMessage(id)).map {
        case PlayerConnected => players ! Command(id, (json \ "keys").asOpt[Seq[Key]], (json \ "gameData").asOpt[JsValue])

        case PlayerNotConnected => players ! AddPLayer(id, ip, channel)
      }
    }.map { _ => players ! Quit(ip)}

    (in, out)
  }
}