package java_learn.kafka.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
//异步发送
public class CustomProducer {
    public static void main(String[] args) throws InterruptedException {
        List<String>InterceptorList=new ArrayList<>();
        InterceptorList.add("java_learn.kafka.interceptor.CounterInterceptor");
        InterceptorList.add("java_learn.kafka.interceptor.TimeInterceptor");
        //创建一个生产者对象
        Properties p=new Properties();
        p.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,InterceptorList);
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        p.put(ProducerConfig.LINGER_MS_CONFIG,1);
        p.put(ProducerConfig.ACKS_CONFIG,"all");
        p.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);


        KafkaProducer<String, String> producer = new KafkaProducer<>(p);
        //调用send方法
        for(int i=0;i<100;i++){
            Thread.sleep(100);
            producer.send(
                    new ProducerRecord<String, String>("firstTopic",i+" ","Message "+i)
            );
        }

        //关闭生产者
        producer.close();
    }
}
