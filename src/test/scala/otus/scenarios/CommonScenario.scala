package otus.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import otus.actions.Actions
import otus.actions.Actions.{loginPage, loginPlInto, navPl, navPlPage, randomFutureDate, reservationChoosingFlights, reservationPlAirAndCost, reservationPlBookAnother, reservationPlConfirmData, reservationPlSearch, reservationPlWelcome, singOff, todayDate, welcome, welcomePageSearch}
import otus.feeders.Feeders.{authFeeder, seatPref, seatType}

import java.time.LocalDate


object CommonScenario {
  def apply() = new CommonScenario().scn
}

class CommonScenario {
  val scn: ScenarioBuilder = scenario("Debug scenario")
    .feed(authFeeder)
    .feed(seatPref)
    .feed(seatType)
    .exec(welcome)
    .exec(navPl)
    .exec(session => {
      val randomDate = randomFutureDate()
      session.set("randomDate", randomDate)
    })
    .exec(session => {
      val today = todayDate()
      session.set("today", today)
    })
    .exec { session =>
      println(s"THIS IS USERSESSION: ${session("userSession").asOption[String].getOrElse("NOT FOUND")}")
      println(s"THIS IS LOGIN: ${session("login").asOption[String].getOrElse("NOT FOUND")}")
      println(s"THIS IS PASSWORD: ${session("password").asOption[String].getOrElse("NOT FOUND")}")
      println(s"THIS IS TODAY: ${session("today").asOption[String].getOrElse("NOT FOUND")}")
      println(s"THIS IS RANDOMDATE: ${session("randomDate").asOption[String].getOrElse("NOT FOUND")}")
      session
    }
    .exec(loginPage)
    .exec(navPlPage)
    .exec(loginPlInto)
    .exec(welcomePageSearch)
    .exec(reservationPlWelcome)
    .exec(reservationChoosingFlights)
    .exec { session =>
      val airAndCost = session("airAndCost").as[String]
      val today = session("today").as[String]
      val outboundFlight = s"$airAndCost;$today"

      session.set("outboundFlight", outboundFlight)
    }
    .exec(reservationPlAirAndCost)
    .exec { session =>
      println(s"THIS IS airAndCost: ${session("airAndCost").asOption[String].getOrElse("NOT FOUND")}")
      println(s"THIS IS outboundFlight: ${session("outboundFlight").asOption[String].getOrElse("NOT FOUND")}")
      session
    }
    .exec(reservationPlConfirmData)
    .exec(reservationPlBookAnother)
    .exec(singOff)
}

