package otus.simulations

import otus.otus.httpProtocol
import otus.scenarios.CommonScenario
import otus.simulations.Auth.setUp

class FMaxClosedModel {
  import io.gatling.http.Predef._
  import io.gatling.core.Predef._
  setUp(
    CommonScenario().inject(
      incrementConcurrentUsers(5)
        .times(5)
        .eachLevelLasting(10)
        .separatedByRampsLasting(10)
        .startingFrom(10) // Int
    )
  ).protocols(httpProtocol)
    .maxDuration(1000)
    .throttle(
      reachRps(5).in(10),



    )

}
