package otus.actions


import io.gatling.core.Predef._
import io.gatling.core.session
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.util.Random


object Actions {

  //  val dataMap: Map[String, String] = Map("key1" -> "value1", "key2" -> "value2")
  //  val mainPage: HttpRequestBuilder = http("UC01_GetMainPage").get("/")
  //
  //  val failedStaff: HttpRequestBuilder = http("__forDelete").get("/")
  //    .body(StringBody(dataMap.toString()));


  //  val mainRootPage: Http = http("Корневая страница Webtours")
  //    .get("/webtours/").check(status is 200)
  //
  //  val loginPage: HttpRequestActionBuilder = http("Корневая страница Webtours")
  //    .get("/webtours/")
  //
  //  val auth: HttpRequestActionBuilder = http("Логин")
  //    .post("/cgi-bin/login.pl")
  //    .formParam("login","#{login}")
  //    .check()


  //  val scn = scenario("Test with random dates")
  //    .exec(session => {
  //      val randomDate = DateGenerator.randomDate()
  //      session.set("randomDate", randomDate)
  //    })
  //    .exec(http("request_with_random_date")
  //      .get("/some-endpoint")
  //      .queryParam("date", "${randomDate}")
  //    )


  val scn = scenario("Dynamic String Concatenation")
    .exec(
      http("Dynamic Request")
        .get("/api/users/${userId}")
        .check(
          jsonPath("$.firstName").saveAs("firstName"),
          jsonPath("$.lastName").saveAs("lastName")
        )
    )
    .exec { session =>
      val firstName = session("firstName").as[String]
      val lastName = session("lastName").as[String]
      val fullName = s"$firstName $lastName"

      session.set("fullName", fullName)
    }
    .exec(
      http("Update User")
        .put("/api/users/${userId}")
        .body(StringBody(
          """{"fullName": "${fullName}"}"""
        )).asJson
    )


  def randomFutureDate(): String = {
    val today = LocalDate.now()
    val futureDate = today.plusDays(Random.nextInt(4 * 5))
    futureDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
  }

  def todayDate(): String = {
    val today = LocalDate.now().plusDays(1)
    today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
  }


  val welcome: HttpRequestBuilder = http("UC_01_Root_page_Welcome")
    .get("/cgi-bin/welcome.pl")
    .queryParam("signOf", "true")
    .check(status.is(200))


  val navPl: HttpRequestBuilder = http("UC_02_Root_page_Nav ")
    .get("/cgi-bin/nav.pl")
    .queryParam("in", "home")
    .check(status.is(200))
    .check(regex("value=\"(\\d{6}[^\"]*)\"")
      .exists.
      saveAs("userSession"))


  val loginPage: HttpRequestBuilder = http("UC_03_Auth_page")
    .post("/cgi-bin/login.pl")
    .body(StringBody("userSession=#{userSession}&username=#{login}&password=#{password}&login.x=49&login.y=11&JSFormSubmit=off"))
    .queryParam("userSession", "#{userSession}")
    .check(status.is(200))

  val navPlPage: HttpRequestBuilder = http("UC_04_NavPLPage")
    .get("/cgi-bin/nav.pl")
    .queryParam("page", "menu")
    .queryParam("in", "home")
    .check(status.is(200))

  val loginPlInto: HttpRequestBuilder = http("UC_05_Login_Pl_Into")
    .get("/cgi-bin/login.pl")
    .queryParam("intro", "true")
    .check(status.is(200))

  val welcomePageSearch: HttpRequestBuilder = http("UC_06_Welcome_Page_Search")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page", "search")
    .check(status.is(200))

  val reservationPlWelcome: HttpRequestBuilder = http("UC_07_Reservation_Pl_Welcome")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page", "welcome")
    .check(regex("option value=\"(\\D[^\"]*)\"").findRandom.saveAs("departCity"))
    .check(regex("option value=\"(\\D[^\"]*)\"").findRandom.saveAs("arriveCity"))

  val reservationPlSearch: HttpRequestBuilder = http("UC_07.1_Reservation_Pl_Search")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page", "search")
  //    .check(regex("option value=\"(\\D[^\"]*)\"").findRandom.saveAs("departCity"))
  //    .check(regex("option value=\"(\\D[^\"]*)\"").findRandom.saveAs("arriveCity"))

  val reservationChoosingFlights: HttpRequestBuilder = http("UC_08_Reservation_Pl_Choosing_flights")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount", "0")
    .formParam("depart", "#{departCity}")
    .formParam("departDate", "#{today}")
    .formParam("arrive", "#{arriveCity}")
    .formParam("returnDate", "#{randomDate}")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "#{seatPref}")
    .formParam("seatType", "#{seatType}")
    .formParam("findFlights.x", "46")
    .formParam("findFlights.y", "14")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")
    .check(regex("name=\"outboundFlight\" value=\"(\\d+;\\d+);").findRandom.saveAs("airAndCost"))
    .check(status.is(200))

  val reservationPlAirAndCost: HttpRequestBuilder = http("UC_09_Reservation_Pl_Choosing_air_and_cost")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "#{outboundFlight}")
    .formParam("outboundFlight", "123456")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "0")
    .formParam("seatType", "#{seatType}")
    .formParam("seatPref", "#{seatPref}")
    .formParam("reserveFlights.x", "44")
    .formParam("reserveFlights.y", "13")

  val reservationPlConfirmData: HttpRequestBuilder = http("UC_10_Reservation_Pl_Confirm_Data")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName", "#{firstName}")
    .formParam("lastName", "#{lastName}")
    .formParam("address1", "#{address1}")
    .formParam("address2", "#{address2}")
    .formParam("pass1", "#{pass1}")
    .formParam("creditCard", "#{creditCard}")
    .formParam("expDate", "#{expDate}")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "1")
    .formParam("seatType", "#{seatType}")
    .formParam("seatPref", "#{seatPref}")
    .formParam("outboundFlight", "#{outboundFlight}")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "21")
    .formParam("buyFlights.y", "8")
    .formParam(".cgifields", "saveCC")
    .check(status.is(200))

  val reservationPlBookAnother: HttpRequestBuilder = http("UC_11_Reservation_Pl_BookAnother")
    .post("/cgi-bin/reservations.pl")
    .formParam("Book Another.x", "30")
    .formParam("Book Another.y", "8")
    .check(status.is(200))

  val singOff: HttpRequestBuilder = http("UC_12_Sing_Off")
    .get("/cgi-bin/welcome.pl")
    .queryParam("signOff", "true")
    .check(status.is(200))

}
