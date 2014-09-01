package ags.actors

/**
 * Created by sirkleber on 30/08/14.
 */

import ags.messages._
import akka.actor.Actor
import models.Player
import scala.language.postfixOps
import scala.collection.mutable.Map
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class PlayerActor extends Actor {
  def receive = {
    case PlayerConnect(ip) => sender ! (waitingLine get ip match {
      case None => (for {
        (_, Player(id, ip_, _)) <- players
        if ip_ == ip
      } yield id).headOption match {
        case None => add(ip)
        case Some(id) => ((), id)
      }
      case Some(id) => ((), id)
    })

    case PlayerDisconnect(ip) => sender ! disconnect(ip)

    case PlayerMessage(id) => sender ! (players get id match {
      case None => PlayerNotConnected
      case Some(_) => PlayerNotConnected
    })

    case AddPLayer(id, ip, channel) => sender ! (waitingLine get ip match {
      case None => ((), s"Player ID: $id, not previously connected")
      case Some(id_) => (players += (id_ -> Player(id_, ip, channel)), s"Player ID: $id_, connected")
    })

    case Command(id, keys, commands) => sender ! (players get id match {
      case None => (Future(""), true)
      case Some(player) => ((for {
        clog <- player sendCmd commands
      } yield (player press keys)._2 + " " + clog), false)
    })

    case GetPlayerData(id, data) => sender !  (players get id match {
      case None => ((), "Data sent to non existent player")
      case Some(Player(_, _, channel)) => (channel push data, "")
    })

    case Quit(ip) => sender ! (getPlayerByIp(ip) match {
      case None => ((), "Unknown Player")
      case Some((Player(id, _, _))) => ((add andThen remove)(ip), s"ID -> $id <--> IP -> $ip")
    })
  }

  private lazy val players: Map[Int, Player] = Map()

  private lazy val waitingLine: Map[String, Int] = Map()

  def add: String => (Unit, Int) = ip => {
    waitingLine += (ip -> (waitingLine.size + 1))
    ((), waitingLine.size)
  }

  def remove: ((Unit, Int)) => Unit = p => players -= p._2

  def disconnect: String => (Unit, String) = ip => waitingLine get ip match {
    case None => ((), "No IP related")
    case Some(id) => ((waitingLine -= ip), s"ID -> $id <--> IP -> $ip")
  }

  private def getPlayerByIp: String => Option[Player] = ip => (for {
    (_, Player(id, ip_, channel)) <- players
    if ip == ip_
  } yield Player(id, ip_, channel)).headOption

}
