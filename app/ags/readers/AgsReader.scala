package ags.readers

import play.api.libs.json._
import play.api.libs.json.Reads
import play.api.libs.functional.syntax._
import models.Key
import scala.language.postfixOps

/**
 * Created by sirkleber on 03/09/14.
 */
object AgsReader {
  implicit val keyReader: Reads[Key] = (
    (__ \ "key").read[String] and
    (__ \ "action").read[String]
   )(Key)

}
