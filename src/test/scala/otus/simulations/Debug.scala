package otus.simulations

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.Predef.{Simulation, atOnceUsers}
import _root_.otus.scenarios.CommonScenario
import otus.otus.httpProtocol

class Debug extends Simulation {

  setUp(CommonScenario()
    .inject(atOnceUsers(1))
    .protocols(httpProtocol))



//
//  setUp(CommonScenario()
//    .inject(
//      nothingFor(3),
//      rampUsers(5).during(5),
//      constantUsersPerSec(20).during(15))
//    .protocols(httpProtocol))
//    .maxDuration(1000)
}
