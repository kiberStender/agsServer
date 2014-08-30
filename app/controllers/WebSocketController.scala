package controllers

/**
 * Created by sirkleber on 30/08/14.
 */

import akka.util.Timeout
import play.api.mvc.Controller
import scala.concurrent.duration._

class WebSocketController extends Controller {
  def jsonApp = "application/json"
  implicit def timeout = Timeout(5 second)
}
