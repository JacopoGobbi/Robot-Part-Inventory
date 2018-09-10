package controllers

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.mvc.ControllerHelpers._
import play.api.mvc.{ControllerComponents, Results}
import play.api.test.{FakeHeaders, FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits
import scala.concurrent.duration.DurationDouble

class RobotsControllerTest extends PlaySpec with Results {
  implicit val timeout: Timeout = 10 seconds
  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  import Implicits.global

  "Robot creation" should {
    "succeed" in {
      val robot = Json.parse("""
          |{
          |  "serial": "Test123TEST",
          |  "name": "MyAmazingTestRobot",
          |  "number": 1234,
          |  "manufacturer": "Jac",
          |  "weight": "12 Kg"
          |}
        """.stripMargin)

      val controller: RobotsController = new RobotsController() {
        override def controllerComponents: ControllerComponents =
          Helpers.stubControllerComponents()
      }
      val req = FakeRequest(
        Helpers.POST,
        "/robots",
        FakeHeaders(List(CONTENT_TYPE -> JSON)),
        robot
      )
      val result = controller.add().apply(req)
      Helpers.status(result) mustBe NO_CONTENT
    }
  }
}