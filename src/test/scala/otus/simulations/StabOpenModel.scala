package otus.simulations


import io.gatling.core.Predef._
import otus.otus.httpProtocol
import otus.scenarios.CommonScenario

import scala.concurrent.duration.DurationInt

class StabOpenModel extends Simulation{

  setUp(CommonScenario()
    .inject(
      rampConcurrentUsers(0).to(5).during(60),
      constantConcurrentUsers(5).during(3600),
      rampConcurrentUsers(5).to(0).during(30)))
    .protocols(httpProtocol)
    .maxDuration(7200)
    .throttle(
      reachRps(45).in(60.second),
      holdFor(60.minutes))

}
