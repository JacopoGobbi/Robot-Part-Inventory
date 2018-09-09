package controllers

import javax.inject.Singleton
import play.api.mvc._

@Singleton
class ApplicationController {
  def index = Action {
    Assets.NoContent
  }
}
