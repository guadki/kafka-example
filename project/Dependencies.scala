import sbt._

object Dependencies {

  val kafkaVersion = "3.3.1"

  val kafkaClient = "org.apache.kafka" % "kafka-clients" % kafkaVersion
  val kafkaStreams = "org.apache.kafka" % "kafka-streams" % kafkaVersion
  val kafkaStreamsScala = "org.apache.kafka" %% "kafka-streams-scala" % kafkaVersion


}
