package models

import java.awt.Robot

import ags.actors.Actors
import ags.messages.GameData
import play.api.libs.iteratee.Concurrent.Channel
import play.api.libs.json.JsValue
import akka.pattern.ask
import scala.language.postfixOps
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import akka.util.Timeout
import scala.concurrent.duration._

/**
 * Created by sirkleber on 30/08/14.
 */

case class Player(id: Int, ip: String, channel: Channel[JsValue]) {
  private type State = (Unit, String)
  private lazy val robot = new Robot
  private implicit val timeout = Timeout(5 second)

  private def keyMngr: Option[Int] => (Int => Unit) => State = key => f => key match {
    case Some(k) => (f(k), "")
    case None => ((), "Key not found")
  }

  def press(optKeys: Option[Seq[Key]]): State = {

    def concatLog: State => State => State = log => log2 => ((), log._2 + ", " + log2._2)

    def keyPresser: Seq[Key] => State => State = keys => log => (for {
      key <- keys
      cat = concatLog(log)
      cmdKey = Command getKey _
    } yield key match {
        case Key(_key, "pressed") => cat(keyMngr(cmdKey(_key))(robot keyPress))
        case Key(_key, "released") => cat(keyMngr(cmdKey(_key))(robot keyRelease))
      }).head

    optKeys match {
      case None => ((), "NoKey")
      case Some(keys) => keyPresser(keys) ((), "")
    }
  }

  def sendCmd(cmd: Option[JsValue]): Future[String] = cmd match {
    case None => Future("NoCommand")
    case Some(command) => for {
      value <- Actors.game ? GameData(command)
    } yield s"$value executed"
  }

  def getId: Int = id
  def getIp: String = ip
}
