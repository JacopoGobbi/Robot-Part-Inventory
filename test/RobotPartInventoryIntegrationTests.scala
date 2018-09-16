import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.db.Database
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.test.Helpers.{CONTENT_TYPE, JSON, NO_CONTENT}

class RobotPartInventoryIntegrationTests
  extends PlaySpec
    with GuiceOneServerPerSuite
    with ScalaFutures
    with IntegrationPatience {

  lazy val database: Database = app.injector.instanceOf[Database]
  implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
  val headers = Seq(CONTENT_TYPE -> JSON)
  val robotSerial = "Test123TEST"
  val myRobotPart: String =
    s"""
       |{
       |  "serial": "$robotSerial",
       |  "name": "MyAmazingTestRobotPart",
       |  "number": 1234,
       |  "manufacturer": "Jac",
       |  "weight": "12 Kg"
       |}
    """.stripMargin

  "RobotPart creation" should {
    "Save a robot's part" in {
      val result = wsCall(controllers.routes.RobotPartsController.add())
        .withHttpHeaders(headers: _*)
        .post(myRobotPart)
      val statusCode = result.futureValue.status
      statusCode mustBe NO_CONTENT
    }
    "Retrieve a robot's part" in {
      val result = wsCall(controllers.routes.RobotPartsController.read(robotSerial))
        .withHttpHeaders(headers: _*)
        .get()
      val robotJson = Json.parse(result.futureValue.body)
      robotJson mustBe Json.parse(myRobotPart)
    }
  }
}
