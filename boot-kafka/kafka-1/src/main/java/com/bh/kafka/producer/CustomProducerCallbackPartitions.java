package com.bh.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * (1)便于合理使用存储资源，每个Partition在一个Broker上存储，可以把海量的数据按照分区切割成一
 * 块一块数据存储在多台Broker上。合理控制分区的任务，可以实现负载均衡的效果。
 * (2)提高并行度，生产者可以以分区为单位发送数据;消费者可以以分区为单位进行消费数据。
 *
 *
 * (1)指明partition的情况下，直 接将指明的值作为partition值; 例如partition=0，所有数据写入 分区0
 *
 * (2)没有指明partition值但有key的情况下，将key的hash值与topic的 partition数进行取余得到partition值;
 * 例如:key1的hash值=5， key2的hash值=6 ，topic的partition数=2，那 么key1 对应的value1写入1号分区，key2对应的value2写入0号分区。
 *
 * (3)既没有partition值又没有key值的情况下，Kafka采用Sticky Partition(黏性分区器)，会随机选择一个分区，并尽可能一直 使用该分区，待该分区的batch已满或者已完成，Kafka再随机一个分区进行使用(和上一次的分区不同)。
 * 例如:第一次随机选择0号分区，等0号分区当前批次满了(默认16k)或者linger.ms设置的时间到， Kafka再随机一个分区进 行使用(如果还是0会继续随机)。
 */
public class CustomProducerCallbackPartitions {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建 kafka 生产者的配置对象
        Properties properties = new Properties();
        // 2. 给 kafka 配置对象添加配置信息:bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // key,value 序列化(必须):key.serializer，value.serializer
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
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
