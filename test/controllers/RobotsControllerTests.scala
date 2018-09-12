package controllers

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.ControllerHelpers._
import play.api.test.Helpers.{GET, POST, contentAsJson, status}
import play.api.test.{FakeHeaders, FakeRequest}
import utils.ControllerSuite

class RobotsControllerTests
  extends ControllerSuite {
  val robotSerial = "Test123TEST"
  val myRobot: JsValue = Json.parse(
    s"""
       |{
       |  "serial": "$robotSerial",
       |  "name": "MyAmazingTestRobot",
       |  "number": 1234,
       |  "manufacturer": "Jac",
       |  "weight": "12 Kg"
       |}
        """.stripMargin)

  "Robot controller" should {
    "succeed on creation" in {
      val req = FakeRequest(
        POST,
        "/robots",
        FakeHeaders(List(CONTENT_TYPE -> JSON)),
        myRobot
      )
      val result = controllers.robotsController.add().apply(req)
      status(result) mustBe NO_CONTENT
    }
    "succeed on retrieval" in {
      val req = FakeRequest(GET, s"/robots/$robotSerial")
      val result = controllers.robotsController.read().apply(req)
      contentAsJson(result) mustBe myRobot
    }
  }
}