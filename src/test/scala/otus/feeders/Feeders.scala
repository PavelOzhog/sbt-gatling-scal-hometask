package otus.feeders

import io.gatling.core.Predef.{configuration, csv}
import io.gatling.core.feeder.BatchableFeederBuilder

object Feeders {

  val authFeeder: BatchableFeederBuilder[String] = csv("credentional.csv").circular
  val seatPref: BatchableFeederBuilder[String] = csv("seatPref.csv").circular
  val seatType: BatchableFeederBuilder[String] = csv("seatType.csv").circular

}
