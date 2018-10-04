package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static void main(String args[]) {

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9094"); // communicates for metadata (knowing the topology).
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(props);
        
        try {
            for (int i = 0; i < 150; i++) {

                producer.send(new ProducerRecord<String, String>("apache-kafka-1", Integer.toString(i), "My message: " + Integer.toString(i)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }
}
