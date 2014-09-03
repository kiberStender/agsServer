package controllers

/**
 * Created by sirkleber on 30/08/14.
 */

import play.api.libs.json.JsValue
import play.api.mvc.Action
import akka.pattern.ask
import ags.actors.Actors
import ags.messages.{PlayerDisconnect, PlayerConnect}
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._

object ControllerWebSocket extends WebSocketController {

  def connect = Action.async { req =>
    (Actors.players ? PlayerConnect(req.remoteAddress)).mapTo[(Unit, Int)].map{
      case ((_, id)) => Ok("" + id) as jsonApp
    }
  }

  def disconnect = Action.async {req =>
    (Actors.players ? PlayerDisconnect(req.remoteAddress)).mapTo[(Unit, String)].map { _ =>
      Ok(Json.obj("disconnect" -> true)) as jsonApp
    }
  }

}