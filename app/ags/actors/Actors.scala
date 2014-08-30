package ags.actors

import akka.actor.Props
import akka.util.Timeout
import play.api.libs.concurrent.Akka
import scala.concurrent.duration._

/**
 * Created by sirkleber on 30/08/14.
 */
object Actors {
  implicit val timeout = Timeout(3 second)
  lazy val players = Akka.system.actorOf(Props[PlayerActor])
}
