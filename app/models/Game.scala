package models

import play.api.libs.iteratee.Concurrent.Channel
import play.api.libs.json.JsValue

/**
 * Created by sirkleber on 01/09/14.
 */
case class Game(ip: String, nome: String, channel: Channel[JsValue])
