import sbt._

object Dependencies {

  object Logback {
    val `logback-classic` = "ch.qos.logback" % "logback-classic" % "1.2.10"
  }

  object ScalaTest {
    val `scalatest` = "org.scalatest" %% "scalatest" % "3.2.11"
  }

  object TypeSafe {

    val config = "com.typesafe" % "config" % "1.4.2"

    object Akka {
      private val AkkaVersion     = "2.6.8"
      private val AkkaHttpVersion = "10.2.8"

      val `akka-actor-typed`     = "com.typesafe.akka" %% "akka-actor-typed"     % AkkaVersion
      val `akka-stream`          = "com.typesafe.akka" %% "akka-stream"          % AkkaVersion
      val `akka-slf4j`           = "com.typesafe.akka" %% "akka-slf4j"           % AkkaVersion
      val `akka-http`            = "com.typesafe.akka" %% "akka-http"            % AkkaHttpVersion
      val `akka-http-spray-json` = "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion
    }
  }
}
