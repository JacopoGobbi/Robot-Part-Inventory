package daos.h2

import com.google.inject.Provider
import daos.DbContext
import daos.template.RobotsDAO
import javax.inject.Inject
import models.Robot

class RobotsDAOImpl @Inject() (
  dbContextProvider: Provider[DbContext]
) extends RobotsDAO {
  val db: DbContext = dbContextProvider.get

  import db._

  val robots: Quoted[EntityQuery[Robot]] = quote(querySchema[Robot]("Robots"))

  override def find(robotSerial: String): Option[Robot] =
    run(robots.filter(r => r.serial == lift(robotSerial))).headOption

  override def create(robot: Robot): Long =
    run(robots.insert(lift(robot)))
}
