package models

import java.awt.Robot

import play.api.libs.iteratee.Concurrent.Channel
import play.api.libs.json.JsValue
import models.Command

/**
 * Created by sirkleber on 30/08/14.
 */
case class Player(id: Int, ip: String, channel: Channel[JsValue]) {
  import Player._
  private lazy val robot = new Robot

  private def keyMngr[A](key: Option[Int])(f: Int => A): Either[String, A] = key match {
    case Some(k) => Right(f(k))
    case None => Left("Tecla não encontrada") //false
  }

  def press(optKeys: Option[Seq[Key]]): Seq[Either[String, Unit]] = optKeys match {
    case None => Seq(Left("NoKey"))

    case Some(keys) => for {
      key <- keys
    } yield key match {
        case Key(key, pressed) => keyMngr(Command getKey key) (robot keyPress)
        case Key(key, released) => keyMngr(Command getKey key) (robot keyRelease)
      }
  }

  def getId: Int = id
  def getIp: String = ip
}

object Player {
  lazy val pressed = "pressed"
  lazy val released = "released"
}
