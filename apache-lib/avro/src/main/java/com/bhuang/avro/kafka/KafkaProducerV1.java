package com.bhuang.avro.kafka;

import com.bhuang.avro.model.Person;
import com.bh.common.model.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerV1 {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaProperties kafkaProperties;

    @PostMapping("/kafka-1/avro/person")
    public R data(){
        Person person = Person.newBuilder().setName("Baeldung").setAge(1).build();
        System.out.println("person = " + person);

//        final Properties properties = new Properties();
//        properties.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
//        properties.put("key.serializer", kafkaProperties.getProducer().getKeySerializer());
//        properties.put("value.serializer", kafkaProperties.getProducer().getValueSerializer());
//        properties.put("acks", "1");
//        properties.put("retries", "3");
//        properties.put("schema.registry.url", "http://localhost:8081");
//
//        final KafkaProducer<String, Person> kafkaProducer = new KafkaProducer<String, Person>(properties);
        String topic = "avro-kafka-1";


        kafkaTemplate.send(topic,person);
        return R.ok().data("data","发送成功");
    }


}
