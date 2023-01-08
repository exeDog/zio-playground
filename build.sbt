ThisBuild / name := "zio-example"
ThisBuild /version := "0.1"
ThisBuild / scalaVersion := "2.13.8"
ThisBuild / organization := "org.priyank"
ThisBuild / idePackagePrefix := Some("org.priyank")

ThisBuild / scalacOptions ++=
  Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Ysafe-init",
    "-language:implicitConversions"
  ) ++ Seq("-rewrite", "-indent") ++ Seq("-source", "-future")

lazy val dependencies = Seq(
  "dev.zio" %% "zio" % "1.0.13"
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "zio",
    libraryDependencies ++= dependencies
  )
