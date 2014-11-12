// Basic project settings

name := "props-and-fuzzes"

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.10.4", "2.11.4")

// set the version in version.sbt. This is necessary for compatibility with sbt-release plugin.

// add java options such as heapspace etc here
javaOptions in run ++= Seq()

// public dependencies
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test", // testing framework
  "org.scalacheck" %% "scalacheck" % "1.11.6" % "test", // property test engine
  "org.rogach" %% "scallop" % "0.9.5", // command line parser
  // "com.typesafe" %% "scalalogging-slf4j" % "1.0.1", // logging wrapper for scala, via slf4j
  "ch.qos.logback" % "logback-classic" % "1.1.2" // actual logger implementation
  // "org.scalaz" %% "scalaz-core" % "7.0.6" // useful types and tools
)

// clueda project dependencies
libraryDependencies ++= Seq(
)
