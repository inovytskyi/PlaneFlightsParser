name := "PlaneFlightsParser"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies += "org.specs2" %% "specs2-core" % "4.6.0" % "test"

scalacOptions in Test ++= Seq("-Yrangepos")
