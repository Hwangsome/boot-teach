package com.bh.kafka.comsumer;

import com.bh.kafka.pojo.People;
import com.bh.kafka.pojo.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration

@Slf4j
public class KafkaConsumer {
    // 指定要监听的 topic

//    @KafkaListener(topics = "people")
//    public void consumeTopic(String record) { // 参数: 收到的 value
//        System.out.println("收到的信息: " + record.toString());
//
//        ObjectMapper mapper = new ObjectMapper();
//        People people = null;
//        try {
//            people= mapper.readValue(record, People.class);
//            log.info(people.toString());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @KafkaListener(topics = "student")
//    public void consumeTopicStudent(String record) { // 参数: 收到的 value
//        System.out.println("收到的信息: " + record.toString());
//
//        ObjectMapper mapper = new ObjectMapper();
//            Student student = null;
//        try {
//            student= mapper.readValue(record, Student.class);
//            log.info(student.toString());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }



    /**
     * Student 可以替换为ConsumerRecord
     * @param record
     */
    @KafkaListener(topics = "student1")
    public void consumeTopicStudent(Student record) { // 参数: 收到的 value
        System.out.println("收到的信息: " + record);
    }

    @KafkaListener(topics = "people1")
    public void consumeTopicPeople(People record) { // 参数: 收到的 value
        System.out.println("收到的信息: " + record);
    }



}
