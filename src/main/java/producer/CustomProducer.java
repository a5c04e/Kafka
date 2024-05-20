package producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import java.util.concurrent.ExecutionException;

public class CustomProducer {
    public static void main(String[] args) throws ExecutionException,InterruptedException {
        Properties props = new Properties();
        Producer<String, String> producer = new KafkaProducer<> (props);
        for(int i=0;i<100;i++) {
            producer.send(new ProducerRecord<String, String>("first", Integer.toString(i), Integer.toString(i))).get();
        }
        producer.close();
    }
}
