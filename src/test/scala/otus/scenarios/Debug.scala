package otus.scenarios

import io.gatling.core.Predef.{Simulation, atOnceUsers}
import otus.otus

object Debug extends Simulation {

  setUp(
    CommonScenario()
      .inject(atOnceUsers(10)))
    .protocols(otus.httpProtocol)
}
