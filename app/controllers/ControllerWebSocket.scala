package controllers

/**
 * Created by sirkleber on 30/08/14.
 */

import play.api.mvc.Action
import akka.pattern.ask
import ags.actors.Actors
import ags.messages.PlayerConnect

object ControllerWebSocket extends WebSocketController {

  def connect = Action.async { req =>
    (Actors.players ? PlayerConnect(req.remoteAddress)).map{ res =>
      Ok(res) as jsonApp
    }
  }

}