ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.9"

lazy val root = (project in file("."))
  .settings(name := "kafka-example")
  .aggregate(producer)

lazy val producer = (project in file("kafka-producer"))
lazy val consumer = (project in file("kafka-consumer"))
