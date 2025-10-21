package otus.actions

import io.gatling.core.Predef.find2Validate
import io.gatling.http.Predef.status
import io.gatling.http.request.builder.Http
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpRequestActionBuilder
import org.json4s.NoTypeHints
import org.json4s.native.Serialization

object Actions {
  implicit val formats = Serialization.formats(NoTypeHints)

  //  val dataMap: Map[String, String] = Map("key1" -> "value1", "key2" -> "value2")
  //  val mainPage: HttpRequestBuilder = http("UC01_GetMainPage").get("/")
  //
  //  val failedStaff: HttpRequestBuilder = http("__forDelete").get("/")
  //    .body(StringBody(dataMap.toString()));



  val mainRootPage: Http = http("Корневая страница Webtours")
    .get("/webtours/").check(status is 200)

  val loginPage: HttpRequestActionBuilder = http("Корневая страница Webtours")
    .get("/webtours/")

  val auth: HttpRequestActionBuilder = http("Логин")
    .post("/cgi-bin/login.pl")
    .queryParam("login","#{login}")
    .check(status is 200)


  //  feed(csv("credentials.csv"))


}
