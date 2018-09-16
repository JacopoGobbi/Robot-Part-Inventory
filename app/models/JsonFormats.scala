package models

import models.responses.RobotPartReadResponse
import play.api.libs.json.Json.format
import play.api.libs.json.Format

object JsonFormats {
  implicit lazy val robotPartFormat: Format[RobotPart] = format[RobotPart]
  implicit lazy val robotPartReadResponseFormat: Format[RobotPartReadResponse] = format[RobotPartReadResponse]
}
