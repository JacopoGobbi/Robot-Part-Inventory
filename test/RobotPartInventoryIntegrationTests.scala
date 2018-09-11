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

  "Robot creation" should {
    "create a robot" in {
      val headers = Seq(CONTENT_TYPE -> JSON)
      val result = wsCall(controllers.routes.RobotsController.add())
        .withHttpHeaders(headers :_*)
        .post("""
          |{
          |  "serial": "Test123TEST",
          |  "name": "MyAmazingTestRobot",
          |  "number": 1234,
          |  "manufacturer": "Jac",
          |  "weight": "12 Kg"
          |}
        """.stripMargin)
      val statusCode = result.futureValue.status
      statusCode mustBe NO_CONTENT
    }
  }
}
