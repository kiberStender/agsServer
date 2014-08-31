package ags.actors

/**
 * Created by sirkleber on 30/08/14.
 */

import ags.messages._
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

    case PlayerDisconnect(ip) => disconnect(ip)

    case PlayerMessage(id) => sender ! (players get id match {
      case None => PlayerNotConnected
      case Some(_) => PlayerNotConnected
    })

    case AddPLayer(id, ip, channel) => sender ! (waitingLine get ip match {
      case None => ((), s"Player ID: $id, not previously connected")
      case Some(id_) =>
        (players += (id_ -> Player(id_, ip, channel)), s"Player ID: $id_, connected")
    })
  }

  private lazy val players: Map[Int, Player] = Map()

  private lazy val waitingLine: Map[String, Int] = Map()

  def add(ip: String): Int = {
    waitingLine += (ip -> (waitingLine.size + 1))
    waitingLine.size
  }

  def disconnect(ip: String) = waitingLine get ip match {
    case None => "No IP related"
    case Some(id) =>
      waitingLine -= ip
      s"ID -> $id <--> IP -> $ip"
  }

}
