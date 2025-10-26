package otus.simulations

import io.gatling.core.scenario.Simulation
import otus.scenarios.CommonScenario
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import otus.otus.httpProtocol


class FMaxOpenModel extends Simulation{

  setUp(CommonScenario()
    .inject(incrementUsersPerSec(1.0) //20% - 40% - 60%
      .times(8)
      .eachLevelLasting(300)
      .separatedByRampsLasting(10)
      .startingFrom(0)
  )).protocols(httpProtocol)
    .maxDuration(5000)

}
