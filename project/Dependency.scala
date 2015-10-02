import sbt._

object Dependency {

  object V {
    val Scala = "2.11.7"
  }

  val json4s = "org.json4s" %% "json4s-native" % "3.2.11" % "provided"
  val typesafeConfig = "com.typesafe" % "config" % "1.3.0" % "provided"
  val scalatest = "org.scalatest" %% "scalatest" % "2.2.5" % "test"
}
