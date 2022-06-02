package com.bh.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.MessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

//    @Bean
//    public DefaultKafkaHeaderMapper defaultKafkaHeaderMapper() {
//        DefaultKafkaHeaderMapper defaultKafkaHeaderMapper = new DefaultKafkaHeaderMapper();
//        defaultKafkaHeaderMapper.addTrustedPackages("com.bh.kafka.pojo");
//
//        return defaultKafkaHeaderMapper;
//    }
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return props;
//    }
//
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG,"test-consumer-group");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(JsonDeserializer.TRUSTED_PACKAGES,"com.bh.kafka.pojo");
//
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public ConsumerFactory<?,?> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() {
//        return new KafkaTemplate<String, Object>(producerFactory(),true);
//    }


//    @Bean
//    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setConcurrency(3);
//        factory.getContainerProperties().setPollTimeout(3000);
//        factory.setMessageConverter(new StringJsonMessageConverter());//2.关键点，从消息队列中取出string数据，然后按照json格式解析，并放入到对象中。这里，spring-kafka会拿到消费者参数里的类型信息，然后，根据这个类型信息转换json数据。
//        return factory;
//
//    }
//    @Bean
//    public ConsumerFactory<Integer, String> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<Integer,String>(consumerConfigs());
//    }
//
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        //3. 要配置这些信息，貌似因为自定义了这写配置，就把application.yml中的相关配置给架空了。
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        return props;
//    }
}