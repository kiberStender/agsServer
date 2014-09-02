package controllers

import java.net.InetAddress

import play.api.mvc._
import scala.util.Try
import scala.language.postfixOps
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._

object Application extends WebSocketController {

  private def getHost = Try(InetAddress.getLocalHost.getHostName) getOrElse "Unknown"

  def index = Action.async { request =>
    Future[JsValue]{
      Json.obj(
        "application" -> true,
        "name" -> getHost,
        "os" -> System.getProperty("os.name")
      )
    }.map{ res => Ok(res) as jsonApp }
  }

}