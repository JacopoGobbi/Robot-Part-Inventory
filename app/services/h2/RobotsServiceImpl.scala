package services.h2

import daos.template.RobotsDAO
import javax.inject.Inject
import models.Robot
import services.template.RobotsService

class RobotsServiceImpl @Inject()(
  robotsDAO: RobotsDAO
) extends RobotsService {
  override def add(robot: Robot): Long = {
    robotsDAO.create(robot)
  }

  override def read(robotSerial: String): Option[Robot] = {
    robotsDAO.find(robotSerial)
  }
}
