package otus.simulations

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import otus.otus.httpProtocol
import otus.scenarios.CommonScenario

class StabOpenModel extends Simulation{

  setUp(CommonScenario()
    .inject(
      nothingFor(3),
      rampUsers(1).during(5),
      constantUsersPerSec(1).during(15).randomized)
    .protocols(httpProtocol))
    .maxDuration(3100)
}
