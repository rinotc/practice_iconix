ThisBuild / organization := "dev.tchiba"
ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "1.0"
ThisBuild / scalacOptions := Seq(
  "-deprecation",
  "-feature",
  "-Xlint",
  "-Ywarn-dead-code"
)

val AkkaVersion     = "2.6.8"
val AkkaHttpVersion = "10.2.8"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

lazy val `root` = (project in file("."))
  .settings(
    name := "practice-iconix",
    libraryDependencies ++= Seq()
  )

lazy val `online-bookstore-domain` = (project in file("online-bookstore-domain"))
  .settings(
    name := "online-bookstore-domain",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.11" % Test
    )
  )

lazy val `practice-akka` = (project in file("practice-akka"))
  .settings(
    name := "practice-akka",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed"     % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream"          % AkkaVersion,
      "com.typesafe.akka" %% "akka-slf4j"           % AkkaVersion,
      "com.typesafe.akka" %% "akka-http"            % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
      "ch.qos.logback"     % "logback-classic"      % "1.2.10",
      "com.typesafe"       % "config"               % "1.4.2"
    )
  )
