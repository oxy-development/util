import Dependency._
import com.typesafe.sbt.osgi.{OsgiKeys, SbtOsgi}
import sbt.Keys._
import sbt._

class UtilBuild extends Build {

  lazy val basicSettings = Seq(
    organization := "io.cafebabe",
    name := "cafebabe-util",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := V.Scala
  )

  lazy val compileJdkSettings = Seq(
    javacOptions ++= Seq(
      "-Xlint:unchecked", "-Xlint:deprecation", "-source", "1.8", "-target", "1.8"
    ),
    scalacOptions ++= Seq(
      "-encoding", "UTF-8", "-deprecation", "-unchecked", "-optimize", "-feature",
      "-language:implicitConversions", "-language:postfixOps", "-target:jvm-1.8"
    )
  )

  lazy val osgiSettings = SbtOsgi.osgiSettings ++ Seq(
    OsgiKeys.exportPackage := Seq("io.cafebabe.util.*"),
    OsgiKeys.privatePackage := Seq.empty,
    OsgiKeys.additionalHeaders := Map("Bundle-Name" -> "Cafebabe Utilities")
  )

  lazy val dependencies = Seq(libraryDependencies ++= Seq(typesafeConfig, json4s, scalatest))

  lazy val root = Project(
    id = "util",
    base = file("."),
    settings = basicSettings ++ compileJdkSettings ++ osgiSettings ++ dependencies
  )
}
