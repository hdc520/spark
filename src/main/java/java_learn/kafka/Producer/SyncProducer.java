package java_learn.kafka.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class SyncProducer {
    public static void main(String[] args) throws Exception{
        //创建一个生产者对象
        Properties properties=new Properties();
        //配置生产者信息
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        //调用send方法
        for(int i=0;i<100;i++){
            Thread.sleep(500);
            RecordMetadata metadata = producer.send(
                    new ProducerRecord<String, String>("firstTopic", i + " ", "Message " + i)
            ).get();//利用future中的get方法来阻塞
            System.out.println("meta："+metadata.offset());
        }

        //关闭生产者
        producer.close();
    }
}
