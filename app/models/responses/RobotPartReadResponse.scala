package models.responses

import models.RobotPart

case class RobotPartReadResponse(
  serial: String,
  name: String,
  number: Int,
  manufacturer: String,
  weight: String
)

object RobotPartReadResponse {
  def fromRobotPart(robotPart: RobotPart): RobotPartReadResponse = {
    RobotPartReadResponse(
      robotPart.serial,
      robotPart.name,
      robotPart.number,
      robotPart.manufacturer,
      robotPart.weight
    )
  }
}
