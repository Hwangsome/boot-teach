package com.bh.kafka.producer;

import com.bh.common.model.R;
import com.bh.kafka.pojo.People;
import com.bh.kafka.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ProducerController {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @PostMapping("/kafka-1/people")
    public R data(@RequestBody People people){
        kafkaTemplate.send("people1",people);
        System.out.println("people = " + people);
        return R.ok().data("data","发送成功");
    }
    @PostMapping("/kafka-1/student")
    public R data(@RequestBody Student student){
        kafkaTemplate.send("student1",student);
        System.out.println("student = " + student);
        return R.ok().data("data","发送成功");
    }

}
