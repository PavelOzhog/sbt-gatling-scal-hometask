enablePlugins(GatlingPlugin)

scalaVersion := "2.13.16"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-release:8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

val gatlingVersion = "3.14.5"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % "test,it"

libraryDependencies += "io.gatling"            % "gatling-test-framework"    % gatlingVersion % "test,it"


val latestVersion = "2.11"

//libraryDependencies += "org.json4s" %% "json4s-native" % "4.0.7"
//
//libraryDependencies += "org.json4s" %% "json4s-jackson" % "4.0.7"

//GatlingIt / javaOptions += "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005"

// Enterprise Cloud (https://cloud.gatling.io/) configuration reference: https://docs.gatling.io/reference/integrations/build-tools/sbt-plugin/#running-your-simulations-on-gatling-enterprise-cloud

//libraryDependencies += "io.gatling" % "gatling-app" % "3.10.0" % Test