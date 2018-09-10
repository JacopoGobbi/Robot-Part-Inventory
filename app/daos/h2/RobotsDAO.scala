package daos.h2

import daos.DbContext
import models.Robot

class RobotsDAO(val db: DbContext) {
  import db._

  val robots: Quoted[EntityQuery[Robot]] = quote(querySchema[Robot]("Users"))

  def create(robot: Robot): Long =
    run(robots.insert(lift(robot)))
}
