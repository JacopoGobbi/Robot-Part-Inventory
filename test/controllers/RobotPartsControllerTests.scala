package controllers

import models.RobotPart
import org.mockito.Mockito.{verify, when}
import org.scalatest.mockito.MockitoSugar
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.ControllerComponents
import play.api.mvc.ControllerHelpers._
import play.api.test.Helpers.{GET, POST, contentAsJson, status, stubControllerComponents}
import play.api.test.{FakeHeaders, FakeRequest}
import services.template.RobotPartsService
import utils.BaseSuite

class RobotPartsControllerTests
  extends BaseSuite
    with MockitoSugar {
  val robotPartSerial = "Test123TEST"
  val robotPartName = "MyAmazingTestRobotPart"
  val robotPartNumber = 1234
  val robotPartManufacturer = "Jac"
  val robotPartWeight = "12 Kg"
  val myRobotPartJson: JsValue = Json.parse(
    s"""
       |{
       |  "serial": "$robotPartSerial",
       |  "name": "$robotPartName",
       |  "number": $robotPartNumber,
       |  "manufacturer": "$robotPartManufacturer",
       |  "weight": "$robotPartWeight"
       |}
        """.stripMargin)
  val myRobotPart = RobotPart(
    robotPartSerial,
    robotPartName,
    robotPartNumber,
    robotPartManufacturer,
    robotPartWeight
  )

  lazy val robotPartsServiceMock: RobotPartsService = mock[RobotPartsService]
  lazy val robotPartsController: RobotPartsController = new RobotPartsController(robotPartsServiceMock) {
    override def controllerComponents: ControllerComponents =
      stubControllerComponents()
  }

  "Robot Parts controller" should {
    "Succeed on creation" in {
      when(robotPartsServiceMock.add(myRobotPart)).thenReturn(0L)
      val req = FakeRequest(
        POST,
        "/robotParts",
        FakeHeaders(List(CONTENT_TYPE -> JSON)),
        myRobotPartJson
      )
      val result = robotPartsController.add().apply(req)
      verify(robotPartsServiceMock).add(myRobotPart)
      status(result) mustBe NO_CONTENT
    }
    "Return robot details on retrieval" in {
      when(robotPartsServiceMock.read(robotPartSerial)).thenReturn(Some(myRobotPart))
      val req = FakeRequest(GET, s"/robotParts/$robotPartSerial")
      val result = robotPartsController.read(robotPartSerial).apply(req)
      verify(robotPartsServiceMock).read(robotPartSerial)
      contentAsJson(result) mustBe myRobotPartJson
    }
  }
}