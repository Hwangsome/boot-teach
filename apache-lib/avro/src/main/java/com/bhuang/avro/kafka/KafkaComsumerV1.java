package com.bhuang.avro.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaComsumerV1 {

    @KafkaListener(topics = "avro-kafka-1")
    public void consumeTopicStudent(ConsumerRecord record) { // 参数: 收到的 value
        System.out.println("收到的信息: " + record);
    }
}
