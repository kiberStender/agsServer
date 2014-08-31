package ags.messages

import play.api.libs.json.JsValue

/**
 * Created by sirkleber on 31/08/14.
 */

trait Data{
  def data: JsValue
}

case class GetPlayerData(id: Int, data: JsValue) extends Data

case class GameData(data: JsValue) extends Data
