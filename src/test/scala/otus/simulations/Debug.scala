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
}
