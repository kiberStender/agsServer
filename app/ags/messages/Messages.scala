package ags.messages

import models.Key
import play.api.libs.iteratee.Concurrent.Channel
import play.api.libs.json.JsValue

/**
 * Created by sirkleber on 30/08/14.
 */

case class PlayerConnect(ip: String)

case class PlayerDisconnect(ip:String)

case class PlayerConnected()

case class PlayerNotConnected()

case class PlayerMessage(id: Int)

case class NewPlayer(id: Int)

case class AddPLayer(id: Int, ip: String, channel: Channel[JsValue])

case class Command(id: Int, keys: Option[Array[Key]], commands: Option[JsValue])

case class Quit(ip: String)
