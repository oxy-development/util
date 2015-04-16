import sbt.Keys._

lazy val root = (project in file(".")).
  settings(
    scalaVersion := "2.11.6",
    organization := "io.cafebabe",
    name := "util",
    version := "0.0.1"
  )
