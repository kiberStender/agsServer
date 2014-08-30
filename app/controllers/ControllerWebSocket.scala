package controllers

/**
 * Created by sirkleber on 30/08/14.
 */

import play.api.mvc.Action

object ControllerWebSocket extends WebSocketController{
  def connect = Action{ req =>
    Ok("") as jsonApp
  }

}
