package daos.template

import models.RobotPart

trait RobotPartsDAO {
  def create(robotPart: RobotPart): Long

  def find(robotPartSerial: String): Option[RobotPart]
}
