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
  private lazy val robot = new Robot
  private implicit val timeout = Timeout(5 second)

  private def keyMngr[A](key: Option[Int])(f: Int => A): Either[String, A] = key match {
    case Some(k) => Right(f(k))
    case None => Left("Tecla não encontrada") //false
  }

  def press(optKeys: Option[Seq[Key]]): Seq[Either[String, Unit]] = optKeys match {
    case None => Seq(Left("NoKey"))

    case Some(keys) => for {
      key <- keys
    } yield key match {
        case Key(key, "pressed") => keyMngr(Command getKey key) (robot keyPress)
        case Key(key, "released") => keyMngr(Command getKey key) (robot keyRelease)
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
