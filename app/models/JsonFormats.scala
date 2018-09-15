package models

import models.responses.RobotReadResponse
import play.api.libs.json.Json.format
import play.api.libs.json.Format

object JsonFormats {
  implicit lazy val robotFormat: Format[Robot] = format[Robot]
  implicit lazy val robotReadResponseFormat: Format[RobotReadResponse] = format[RobotReadResponse]
}
