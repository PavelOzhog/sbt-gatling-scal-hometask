package otus.simulations


import io.gatling.http.Predef._
import io.gatling.core.Predef._
import _root_.otus.scenarios.CommonScenario
import otus.otus.httpProtocol

class StabClosedModel extends Simulation{

  setUp(
    CommonScenario().inject(
      rampConcurrentUsers(0).to(1).during(5),
      constantConcurrentUsers(1).during(15),
      rampConcurrentUsers(10).to(0).during(5)))
  .protocols(httpProtocol).maxDuration(5000)

}
