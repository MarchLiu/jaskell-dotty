import sbt.url

val dottyVersion = "3.0.0-RC1"

ThisBuild / organization := "io.github.marchliu"
ThisBuild / organizationName := "Mars Liu"
ThisBuild / organizationHomepage := Some(url("https://marchliu.github.io/"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/MarchLiu/jaskell-dotty"),
    "scm:git@github.com:MarchLiu/jaskell-dotty.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "Mars Liu",
    name  = "Liu Xin",
    email = "mars.liu@outlook.com",
    url   = url("https://marchliu.github.io/")
  )
)

ThisBuild / description := "Jaskell Core components Library, written by scala 3 (AKA Dotty)"
ThisBuild / licenses := List("MIT" -> new URL("https://github.com/MarchLiu/jaskell-dotty/blob/master/LICENSE"))
ThisBuild / homepage := Some(url("https://github.com/MarchLiu/jaskell-dotty"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true

lazy val root = project
  .in(file("."))
  .settings(
    name := "jaskell-dotty",
    version := "0.2.6",

    scalaVersion := dottyVersion,
    libraryDependencies += "org.scalatest" % s"scalatest_${dottyVersion}" % "3.2.4",

    scalacOptions ++= Seq(
      "-feature",
      "-language:implicitConversions"
    )
  )

