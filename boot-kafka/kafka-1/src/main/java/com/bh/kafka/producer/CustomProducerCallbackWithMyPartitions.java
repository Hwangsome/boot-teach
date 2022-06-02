package com.bh.kafka.producer;

import com.bh.kafka.partion.MyPartitioner;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class CustomProducerCallbackWithMyPartitions {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建 kafka 生产者的配置对象
        Properties properties = new Properties();
        // 2. 给 kafka 配置对象添加配置信息:bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // key,value 序列化(必须):key.serializer，value.serializer
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        //添加自定义分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.bh.kafka.partion.MyPartitioner");
        // 3. 创建 kafka 生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 15; i++) {
            // 指定数据发送到 1 号分区，key 为空(
            kafkaProducer.send(new ProducerRecord<>("first", "hello kafka " + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println( metadata.partition());
                    if (exception ==null){
                        System.out.println(" 主 题 : " +
                                metadata.topic() + "->" + "分区:" + metadata.partition());
                    }else {
                        // 出现异常打印
                        System.out.println("exception = " + exception);
                        exception.printStackTrace();
                    }
                }
            });
            // 延迟一会会看到数据发往不同分区
            Thread.sleep(2);
        }
        // 5. 关闭资源
        kafkaProducer.close();
    }
}
