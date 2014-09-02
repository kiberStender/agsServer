package ags.actors

import ags.messages._
import akka.actor.Actor
import models.Game
import play.api.libs.json.Json
import scala.collection.mutable.Map
import scala.language.postfixOps

/**
 * Created by sirkleber on 31/08/14.
 */

class GameActor extends Actor{

  def receive = {
    case GameMessage(nome) => sender ! gameStatus(nome)

    case GameConnect(ip, nome, channel) => sender ! (add(Game(ip, nome, channel)) , s"$nome has connected")


    case GameDisconnect => sender ! (game get 1 match {
      case None => ((), "No game to disconnect!!!")
      case Some(Game(nome, _, _)) => ((remove), s"$nome has disconnected")
    })

    case GameData(data) => sender ! (game get 1 match {
      case None => ((), "No game to accomplish this task!!!")
      case Some(Game(_, _, channel)) => sender ! (channel push data, "Data pushed Ok!")
    })
  }

  private lazy val game: Map[Int, Game] = Map()

  private def add: Game => Unit = g => game += 1 -> g

  private def remove: Unit = game -= 1

  private def gameStatus: Option[String] => GameStatus = name => game get 1 match {
    case None => GameCannotConnect()
    case Some(Game(_, nm, _)) => name match {
      case None => GameConnected()
      case Some(str) => if(nm != str) GameCannotConnect() else GameConnected()
    }
  }
}