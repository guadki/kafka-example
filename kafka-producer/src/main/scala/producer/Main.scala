package producer

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.clients.producer.ProducerConfig.{
  BOOTSTRAP_SERVERS_CONFIG,
  CLIENT_ID_CONFIG,
  KEY_SERIALIZER_CLASS_CONFIG,
  VALUE_SERIALIZER_CLASS_CONFIG
}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties
import scala.util.Random

object Main extends App {

  val topic: String = "calls"

  val kafkaProducerProps: Properties = {
    val props = new Properties()
    props.put(CLIENT_ID_CONFIG, "producer")
    props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    props.put(VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    props
  }

  val producer = new KafkaProducer[String, String](kafkaProducerProps)

  while (true) {
    val record = new ProducerRecord[String, String](
      topic,
      Random.nextInt(40).toString,
      Random.nextInt(600).toString
    )

    producer.send(record)
    producer.flush()
    println(s"[${record.key()}] ${record.value()} SENT")
    Thread.sleep(5000)
  }

}
