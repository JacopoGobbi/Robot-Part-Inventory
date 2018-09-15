package services.template

import models.Robot

trait RobotsService {
  def add(robot: Robot): Long
  def read(robotSerial: String): Option[Robot]
}
