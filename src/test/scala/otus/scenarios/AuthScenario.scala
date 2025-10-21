package otus.scenarios

import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import otus.actions.Actions


object AuthScenario {
    def apply() = new CommonScenario().scn
  }

  class AuthScenario {
    val authScn: ScenarioBuilder = scenario("Имя сценария для репорта")
      .exec(authScn)
      .exec()
}
