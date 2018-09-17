package consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerAssignApp {

    public static void main(String args[]) {

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put("group.id", "test");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        List<TopicPartition> partition = new ArrayList<TopicPartition>();

        TopicPartition partition1Topic0 = new TopicPartition("test-consumer",1);
        TopicPartition partition2Topic1 = new TopicPartition("test-consumer-1",2);

        partition.add(partition1Topic0);
        partition.add(partition2Topic1);

        consumer.assign(partition);


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
