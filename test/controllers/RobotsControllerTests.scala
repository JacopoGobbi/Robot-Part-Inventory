package controllers

import models.Robot
import org.mockito.Mockito
import org.mockito.Mockito.{verify, when}
import org.scalatest.mockito.MockitoSugar
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.ControllerComponents
import play.api.mvc.ControllerHelpers._
import play.api.test.Helpers.{GET, POST, contentAsJson, status, stubControllerComponents}
import play.api.test.{FakeHeaders, FakeRequest}
import services.template.RobotsService
import utils.BaseSuite

class RobotsControllerTests
  extends BaseSuite
    with MockitoSugar {
  val robotSerial = "Test123TEST"
  val robotName = "MyAmazingTestRobot"
  val robotNumber = 1234
  val robotManufacturer = "Jac"
  val robotWeight = "12 Kg"
  val myRobotJson: JsValue = Json.parse(
    s"""
       |{
       |  "serial": "$robotSerial",
       |  "name": "$robotName",
       |  "number": $robotNumber,
       |  "manufacturer": "$robotManufacturer",
       |  "weight": "$robotWeight"
       |}
        """.stripMargin)
  val myRobot = Robot(
    robotSerial,
    robotName,
    robotNumber,
    robotManufacturer,
    robotWeight
  )

  lazy val robotsServiceMock: RobotsService = mock[RobotsService]
  lazy val robotsController: RobotsController = new RobotsController(robotsServiceMock) {
    override def controllerComponents: ControllerComponents =
      stubControllerComponents()
  }

  "Robot controller" should {
    "Succeed on creation" in {
      when(robotsServiceMock.add(myRobot)).thenReturn(0L)
      val req = FakeRequest(
        POST,
        "/robots",
        FakeHeaders(List(CONTENT_TYPE -> JSON)),
        myRobotJson
      )
      val result = robotsController.add().apply(req)
      verify(robotsServiceMock).add(myRobot)
      status(result) mustBe NO_CONTENT
    }
    "Return robot details on retrieval" in {
      when(robotsServiceMock.read(robotSerial)).thenReturn(Some(myRobot))
      val req = FakeRequest(GET, s"/robots/$robotSerial")
      val result = robotsController.read(robotSerial).apply(req)
      verify(robotsServiceMock).read(robotSerial)
      contentAsJson(result) mustBe myRobotJson
    }
  }
}