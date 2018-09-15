package daos.template

import models.Robot

trait RobotsDAO {
  def create(robot: Robot): Long

  def find(robotSerial: String): Option[Robot]
}
