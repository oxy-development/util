import sbt.Keys._
import com.typesafe.sbt.osgi.SbtOsgi

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

lazy val root = (project in file(".")).
  settings(compileJdkSettings: _*).
  settings(osgiSettings: _*).
  settings(
    organization := "io.cafebabe",
    name := "util",
    version := "0.0.1-SNAPSHOT",

    scalaVersion := "2.11.6",

    // it doesn't work for -javadoc and -sources
//    artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
//      s"${module.organization}.${artifact.name}_${sv.binary}-${module.revision}.${artifact.extension}"
//    },

    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-native" % "3.2.11" % "provided",
      "com.typesafe" % "config" % "1.2.1" % "provided",
      "org.scalatest" %% "scalatest" % "2.2.4" % "test"
    )
  )
