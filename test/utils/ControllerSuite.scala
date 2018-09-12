package utils

import controllers.RobotsController
import play.api.mvc.ControllerComponents
import play.api.test.Helpers

class ControllerSuite
  extends BaseSuite {
  object controllers {
    lazy val robotsController: RobotsController = new RobotsController() {
      override def controllerComponents: ControllerComponents =
        Helpers.stubControllerComponents()
    }
  }
}
