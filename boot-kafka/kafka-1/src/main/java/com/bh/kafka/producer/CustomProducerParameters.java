package com.bh.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * 生产者如何提高吞吐量：
 * • batch.size:批次大小，默认16k
 * • linger.ms:等待时间，修改为5-100ms
 * • compression.type:压缩snappy
 * • RecordAccumulator:缓冲区大小，修改为64m
 */
public class CustomProducerParameters {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建 kafka 生产者的配置对象
        Properties properties = new Properties();
        // 2. 给 kafka 配置对象添加配置信息:bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // key,value 序列化(必须):key.serializer，value.serializer
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        // compression.type:压缩，默认 none，可配置值 gzip、snappy、 lz4 和 zstd
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);



        // 3. 创建 kafka 生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 50; i++) {
            // 指定数据发送到 1 号分区，key 为空(
            kafkaProducer.send(new ProducerRecord<>("first", 0,"","hello kafka " + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception ==null){
                        System.out.println(" 主 题 : " +
                                metadata.topic() + "->" + "分区:" + metadata.partition());
                    }else {
                        // 出现异常打印
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
