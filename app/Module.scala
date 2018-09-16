import com.google.inject.AbstractModule
import daos.{DbContext, DbContextProvider}
import daos.h2.RobotPartsDAOImpl
import daos.template.RobotPartsDAO
import services.h2.RobotPartsServiceImpl
import services.template.RobotPartsService

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[DbContext]).toProvider(classOf[DbContextProvider])
    bind(classOf[RobotPartsService]).to(classOf[RobotPartsServiceImpl]).asEagerSingleton()
    bind(classOf[RobotPartsDAO]).to(classOf[RobotPartsDAOImpl])
  }
}
