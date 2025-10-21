package otus.scenarios

import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder


object CommonScenario {
  def apply() = new CommonScenario().scn
}

class CommonScenario {
  val scn: ScenarioBuilder = scenario("Имя сценария для репорта")
    .exec(Actions.loginPage)
    .exec(Actions.loginPage)
    .exec(Actions.auth)

}

