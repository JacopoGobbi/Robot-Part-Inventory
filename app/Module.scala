import com.google.inject.AbstractModule
import daos.{DbContext, DbContextProvider}
import daos.h2.RobotsDAOImpl
import daos.template.RobotsDAO
import services.h2.RobotsServiceImpl
import services.template.RobotsService

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[DbContext]).toProvider(classOf[DbContextProvider])
    bind(classOf[RobotsService]).to(classOf[RobotsServiceImpl]).asEagerSingleton()
    bind(classOf[RobotsDAO]).to(classOf[RobotsDAOImpl])
  }
}
