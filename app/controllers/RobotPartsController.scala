package controllers

import javax.inject.{Inject, Singleton}
import models.JsonFormats._
import models.RobotPart
import models.responses.RobotPartReadResponse
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc.{Action, AnyContent, InjectedController}
import services.template.RobotPartsService

import scala.concurrent.ExecutionContext

@Singleton
class RobotPartsController @Inject()(
  robotPartsService: RobotPartsService
)(implicit ex: ExecutionContext) extends InjectedController {

  def add(): Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[RobotPart] match {
      case JsSuccess(robotPart, _) =>
        robotPartsService.add(robotPart)
        NoContent
      case JsError(_) =>
        BadRequest(Json.obj("msg" -> "Bad Json payload format"))
    }
  }

  def read(serialNumber: String): Action[AnyContent] = Action { _ =>
    robotPartsService.read(serialNumber) match {
      case Some(robot) =>
        Ok(Json.toJson(RobotPartReadResponse.fromRobotPart(robot)))
      case None =>
        NoContent
    }
  }
}
