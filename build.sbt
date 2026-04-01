val scala3Version = "3.8.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-algorithms",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test,
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.13.0",
    libraryDependencies += "org.typelevel" %% "cats-free" % "2.13.0",
      scalacOptions ++= Seq (
        "-deprecation",
        "-feature",
        "-Yexplicit-nulls"
      )
  )
