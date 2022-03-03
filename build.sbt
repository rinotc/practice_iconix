import Dependencies._

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

lazy val `ddd-base` = (project in file("ddd-base"))
  .settings(
    name := "ddd-base",
    libraryDependencies ++= Seq(
      ScalaTest.`scalatest` % Test
    )
  )

lazy val `online-bookstore-domain` = (project in file("online-bookstore-domain"))
  .dependsOn(`ddd-base`)
  .settings(
    name := "online-bookstore-domain",
    libraryDependencies ++= Seq(
      ScalaTest.`scalatest` % Test,
      Refined.`refined`
    )
  )

lazy val `practice-akka` = (project in file("practice-akka"))
  .settings(
    name := "practice-akka",
    libraryDependencies ++= Seq(
      TypeSafe.Akka.`akka-actor-typed`,
      TypeSafe.Akka.`akka-stream`,
      TypeSafe.Akka.`akka-slf4j`,
      TypeSafe.Akka.`akka-http`,
      TypeSafe.Akka.`akka-http-spray-json`,
      TypeSafe.config,
      Logback.`logback-classic`
    )
  )
