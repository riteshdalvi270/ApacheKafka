package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static void main(String args[]) {

        Properties props = new Properties();

        props.put("bootstrap.servers", "Broker-1:9090, Broker-2:9091");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer(props);

        ProducerRecord myMessage = new ProducerRecord("my_topic", "My message 1");


    }
}
