package daos.h2

import com.google.inject.Provider
import daos.DbContext
import daos.template.RobotPartsDAO
import javax.inject.Inject
import models.RobotPart

class RobotPartsDAOImpl @Inject() (
  dbContextProvider: Provider[DbContext]
) extends RobotPartsDAO {
  val db: DbContext = dbContextProvider.get

  import db._

  val robotParts: Quoted[EntityQuery[RobotPart]] = quote(querySchema[RobotPart]("RobotParts"))

  override def find(robotPartSerial: String): Option[RobotPart] =
    run(robotParts.filter(p => p.serial == lift(robotPartSerial))).headOption

  override def create(robotPart: RobotPart): Long =
    run(robotParts.insert(lift(robotPart)))
}
