package consumer

import org.apache.kafka.clients.consumer.ConsumerConfig.{BOOTSTRAP_SERVERS_CONFIG, GROUP_ID_CONFIG, KEY_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER_CLASS_CONFIG}
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.{Calendar, Properties}
import scala.jdk.CollectionConverters.{IterableHasAsScala, SeqHasAsJava}
import scala.language.reflectiveCalls
import scala.util.Using

object Main extends App {

  val topic: String = "calls"

  val kafkaConsumerProps: Properties = {
    val props = new Properties()
    props.put(GROUP_ID_CONFIG, "consumer")
    props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    props.put(VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    props
  }

  val consumer = new KafkaConsumer[String, String](kafkaConsumerProps)

  Using.resource(consumer) { c =>
    while (true) {
      c.subscribe(Seq(topic).asJava)
      val records: Seq[ConsumerRecord[String, String]] = {
        println(s"[${Calendar.getInstance().getTimeInMillis}] polled")
        c.poll(Duration.ofSeconds(5)).asScala.toSeq
      }
      records.foreach { record =>
        println(s"[${record.key()}] ${record.value()} RECEIVED")
      }
    }
  }

}
