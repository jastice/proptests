// Basic project settings

name := "proptests"

scalaVersion := "2.11.4"

// add java options such as heapspace etc here
javaOptions in run ++= Seq()

// public dependencies
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test", // testing framework
  "org.scalacheck" %% "scalacheck" % "1.11.6" % "test", // property test engine
  "org.rogach" %% "scallop" % "0.9.5", // command line parser
  "ch.qos.logback" % "logback-classic" % "1.1.2" // actual logger implementation
)
