import sbt._

object Dependencies {

  object Logback {
    val `logback-classic` = "ch.qos.logback" % "logback-classic" % "1.2.10"
  }

  object ScalaTest {
    val `scalatest` = "org.scalatest" %% "scalatest" % "3.2.11"
  }

  object Refined {
    private val RefinedVersion = "0.9.28"

    val `refined`            = "eu.timepit" %% "refined"            % RefinedVersion
    val `refined-cats`       = "eu.timepit" %% "refined-cats"       % RefinedVersion // optional
    val `refined-eval`       = "eu.timepit" %% "refined-eval"       % RefinedVersion // optional, JVM-only
    val `refined-jsonpath`   = "eu.timepit" %% "refined-jsonpath"   % RefinedVersion // optional, JVM-only
    val `refined-pureconfig` = "eu.timepit" %% "refined-pureconfig" % RefinedVersion // optional, JVM-only
    val `refined-scalacheck` = "eu.timepit" %% "refined-scalacheck" % RefinedVersion // optional
    val `refined-scalaz`     = "eu.timepit" %% "refined-scalaz"     % RefinedVersion // optional
    val `refined-scodec`     = "eu.timepit" %% "refined-scodec"     % RefinedVersion // optional
    val `refined-scopt`      = "eu.timepit" %% "refined-scopt"      % RefinedVersion // optional
    val `refined-shapeless`  = "eu.timepit" %% "refined-shapeless"  % RefinedVersion // optional
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
