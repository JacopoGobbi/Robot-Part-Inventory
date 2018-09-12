package utils

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Results
import play.api.test.DefaultAwaitTimeout

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global

trait BaseSuite
  extends PlaySpec
    with Results
    with DefaultAwaitTimeout {
  implicit val executionContext: ExecutionContext = global
  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
}
