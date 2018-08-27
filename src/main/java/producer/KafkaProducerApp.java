package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static void main(String args[]) {

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9093, localhost:9094, localhost:9095");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(props);


        try {
            for (int i = 0; i < 150; i++) {

                producer.send(new ProducerRecord<String, String>("kaafka-distributed", Integer.toString(i), "My message: " + Integer.toString(i)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }
}
