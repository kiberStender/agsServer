package ags.actors

/**
 * Created by sirkleber on 30/08/14.
 */

import ags.messages.PlayerConnect
import akka.actor.Actor
import models.Player
import play.api.libs.json.Json
import scala.language.postfixOps
import scala.collection.mutable.Map

class PlayerActor extends Actor{
  def receive = {
    case PlayerConnect(ip) => sender ! Json.obj("id" -> (waitingLine get ip match {
      case None => (for {
        (_, Player(id, ip_, _)) <- players
        if ip_ == ip
      } yield id).headOption match {
        case None => add(ip)
        case Some(id) => id
      }
      case Some(id) => id
    }))
  }

  private lazy val players: Map[Int, Player] = Map()

  private lazy val waitingLine: Map[String, Int] = Map()

  def add(ip: String): Int = {
    waitingLine += (ip -> (waitingLine.size + 1))
    waitingLine.size
  }

}
