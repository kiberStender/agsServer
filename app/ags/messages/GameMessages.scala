package ags.messages

import play.api.libs.iteratee.Concurrent.Channel
import play.api.libs.json.JsValue

/**
 * Created by sirkleber on 01/09/14.
 */

sealed trait GameStatus

case class GameConnected() extends GameStatus

case class GameCannotConnect() extends GameStatus

case class GameNotConnected()

case class GameConnect(ip: String, nome: String, channel: Channel[JsValue])

case class GameDisconnect()

case class SendData(id: Int, data: JsValue)

case class GameMessage(nome: Option[String])