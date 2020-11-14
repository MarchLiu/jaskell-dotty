val dottyVersion = "0.27.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "jaskell",
    version := "0.1.0",

    scalaVersion := dottyVersion,

//    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scalatest" % "scalatest_0.27" % "3.2.2",

    scalacOptions ++= Seq(
      "-feature",
      "-language:implicitConversions"
    )
  )
