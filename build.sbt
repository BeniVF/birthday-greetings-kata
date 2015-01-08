// For Scala 2.11.0
scalaVersion := "2.11.0"

scalacOptions ++= Seq("-feature")

libraryDependencies ++= Seq(
  "javax.mail" % "mail" % "1.4.4",
  "javax.activation" % "activation" % "1.1",
  "org.subethamail" % "subethasmtp" % "3.1.7" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test",
  "org.slf4j" % "slf4j-api" % "1.7.7" % "test",
  "org.slf4j" % "slf4j-simple" % "1.7.7" % "test",
  "org.slf4j" % "slf4j-log4j12" % "1.7.7" % "test"
)