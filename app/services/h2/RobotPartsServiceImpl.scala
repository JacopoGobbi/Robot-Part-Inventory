package services.h2

import daos.template.RobotPartsDAO
import javax.inject.Inject
import models.RobotPart
import services.template.RobotPartsService

class RobotPartsServiceImpl @Inject()(
  robotPartsDAO: RobotPartsDAO
) extends RobotPartsService {
  override def add(robotPart: RobotPart): Long = {
    robotPartsDAO.create(robotPart)
  }

  override def read(robotPartSerial: String): Option[RobotPart] = {
    robotPartsDAO.find(robotPartSerial)
  }
}
