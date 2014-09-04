package controllers

/**
 * Created by sirkleber on 30/08/14.
 */

import akka.util.Timeout
import play.api.mvc.Controller
import scala.concurrent.duration._
import scala.language.postfixOps

abstract class WebSocketController extends Controller {
  protected def jsonApp = "application/json"
  protected implicit def timeout = Timeout(5 second)
}
