package models.responses

import models.Robot

case class RobotReadResponse(
  serial: String,
  name: String,
  number: Int,
  manufacturer: String,
  weight: String
)

object RobotReadResponse {
  def fromRobot(robot: Robot): RobotReadResponse = {
    RobotReadResponse(
      robot.serial,
      robot.name,
      robot.number,
      robot.manufacturer,
      robot.weight
    )
  }
}
