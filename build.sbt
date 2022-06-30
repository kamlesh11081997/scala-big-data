name := "hello"

version := "1.0"

scalaVersion := "2.11.8"

val sparkVersion = "1.6.1"

lazy val root = (project in file("."))
  .settings(
    name := "apach-spark-tutorial"
  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-twitter" % sparkVersion
)