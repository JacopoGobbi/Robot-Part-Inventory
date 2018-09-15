package controllers

import javax.inject.{Inject, Singleton}
import models.JsonFormats._
import models.Robot
import models.responses.RobotReadResponse
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc.{Action, AnyContent, InjectedController}
import services.template.RobotsService

import scala.concurrent.ExecutionContext

@Singleton
class RobotsController @Inject()(
  robotsService: RobotsService
)(implicit ex: ExecutionContext) extends InjectedController {

  def add(): Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Robot] match {
      case JsSuccess(robot, _) =>
        robotsService.add(robot)
        NoContent
      case JsError(_) =>
        BadRequest(Json.obj("msg" -> "Bad Json payload format"))
    }
  }

  def read(serialNumber: String): Action[AnyContent] = Action { _ =>
    robotsService.read(serialNumber) match {
      case Some(robot) =>
        Ok(Json.toJson(RobotReadResponse.fromRobot(robot)))
      case None =>
        NoContent
    }
  }
}
