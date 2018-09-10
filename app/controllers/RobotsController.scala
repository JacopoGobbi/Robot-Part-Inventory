package controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.JsValue
import play.api.mvc.{Action, ControllerComponents, InjectedController}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RobotsController @Inject()(implicit exec: ExecutionContext) extends InjectedController {
  def add(): Action[JsValue] = Action.async(parse.json) { _ =>
    Future.successful(NoContent)
  }
}
