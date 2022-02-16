ThisBuild / scalaVersion := "2.13.6"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

lazy val `root` = (project in file("."))
  .settings(
    name := "practice-iconix",
    version := "1.0",
    libraryDependencies ++= Seq()
  )
