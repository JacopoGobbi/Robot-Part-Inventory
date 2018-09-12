import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.libs.ws.WSClient
import play.api.test.Helpers.{CONTENT_TYPE, JSON, NO_CONTENT}

class RobotPartInventoryIntegrationTests
  extends PlaySpec
    with GuiceOneServerPerSuite
    with ScalaFutures
    with IntegrationPatience {

  implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
  val headers = Seq(CONTENT_TYPE -> JSON)
  val robotSerial = "Test123TEST"
  val myRobot: String =
    s"""
       |{
       |  "serial": "$robotSerial",
       |  "name": "MyAmazingTestRobot",
       |  "number": 1234,
       |  "manufacturer": "Jac",
       |  "weight": "12 Kg"
       |}
    """.stripMargin

  "Robot creation" should {
    "Create a robot" in {
      val result = wsCall(controllers.routes.RobotsController.add())
        .withHttpHeaders(headers: _*)
        .post(myRobot)
      val statusCode = result.futureValue.status
      statusCode mustBe NO_CONTENT
    }
    "Retrieve a robot" in {
      val result = wsCall(controllers.routes.RobotsController.read(robotSerial))
        .withHttpHeaders(headers: _*)
        .get()
      val robotBody = result.futureValue.body
      robotBody mustBe myRobot
    }
  }
}
