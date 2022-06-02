package com.bh.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * ACK应答级别 acks:
 * 0:生产者发送过来的数据，不需要等数据落盘应答 ,数据可靠性分析:丢数
 * 1:生产者发送过来的数据，Leader收到数据后应答。数据可靠性分析:丢数。应答完成后，还没开始同 步副本，Leader挂了，新的Leader不会 收到Hello的信息， 因为生产者已经 认为发送成功了。
 * -1(all):生产者发送过来的数据，Leader和ISR队列里面 的所有节点收齐数据后应答。
 *
 * Leader收到数据，所有Follower都开始同步数据，但有一 个Follower，因为某种故障，迟迟不能与Leader进行同步，那这个问 题怎么解决呢?
 * Leader维护了一个动态的in-sync replica set(ISR)，意为和 Leader保持同步的Follower+Leader集合(leader:0，isr:0,1,2)。
 * 如果Follower长时间未向Leader发送通信请求或同步数据，则 该Follower将被踢出ISR。该时间阈值由replica.lag.time.max.ms参 数设定，默认30s。例如2超时，(leader:0, isr:0,1)。
 *  这样就不用等长期联系不上或者已经故障的节点。
 *
 *  数据可靠性分析:
 * 如果分区副本设置为1个，或者ISR里应答的最小副本数量 ( min.insync.replicas 默认为1)设置为1，和ack=1的效果是一 样的，仍然有丢数的风险(leader:0，isr:0)。
 * 数据完全可靠条件 = ACK级别设置为-1 + 分区副本大于等于2 + ISR里应答的最小副本数量大于等于2
 *
 *  可靠性总结:
 * acks=0，生产者发送过来数据就不管了，可靠性差，效率高;
 * acks=1，生产者发送过来数据Leader应答，可靠性中等，效率中等;
 * acks=-1，生产者发送过来数据Leader和ISR队列里面所有Follwer应答，可靠性高，效率低;
 * 在生产环境中，acks=0很少使用;acks=1，一般用于传输普通日志，允许丢个别数据;acks=-1，一般用于传输和钱相关的数据， 对可靠性要求比较高的场景。
 */
public class CustomProducerAck {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建 kafka 生产者的配置对象
        Properties properties = new Properties();
        // 2. 给 kafka 配置对象添加配置信息:bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // key,value 序列化(必须):key.serializer，value.serializer
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // 设置acks
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        // 重试次数retries，默认是int最大值，2147483647
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
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
