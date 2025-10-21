package otus.feeders

import io.gatling.core.Predef.{configuration, csv}
import io.gatling.core.feeder.BatchableFeederBuilder

object Feeders {

  val authFeeder :BatchableFeederBuilder [String] = csv("credentional.csv").queue
}
