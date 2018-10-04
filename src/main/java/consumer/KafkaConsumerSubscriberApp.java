package consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Properties;

public class KafkaConsumerSubscriberApp {

    public static void main(String args[]) {

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9094");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "test");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);

        ArrayList<String> topics = new ArrayList<String>();

        topics.add("apache-kafka-1");
        topics.add("apache-kafka-2");

        consumer.subscribe(topics);

        try {
            while(true) {

                ConsumerRecords<String, String> records = consumer.poll(10);

                for(ConsumerRecord<String,String> record : records) {

                    System.out.println(String.format("Partition: %s, Topic : %s, Offset: %s, key: %s, value: %s", record.partition(), record.topic(), record.offset(),
                            record.key(), record.value()));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            consumer.close();
        }
    }
}
