package services.template

import models.RobotPart

trait RobotPartsService {
  def add(robotPart: RobotPart): Long
  def read(robotSerial: String): Option[RobotPart]
}
