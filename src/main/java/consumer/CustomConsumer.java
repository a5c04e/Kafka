package consumer;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.*;

public class CustomConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.10.102:9092");
        //设置消费者组test
        props.put("group.id", "test");
        //开启自动提交
        props.put("enable.auto.commit", "true");
        //设置自动提交时间间隔为1秒
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String,  String>  consumer=new KafkaConsumer<> (props);
        //消费者订阅first主题
        consumer.subscribe(Arrays.asList("first"));
        while (true) {
            ConsumerRecords<String,  String>  records=consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
