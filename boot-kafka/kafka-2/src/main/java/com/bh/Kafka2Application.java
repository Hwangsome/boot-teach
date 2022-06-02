package com.bh;

import com.bh.kafka.pojo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kafka2Application {
    public static void main(String[] args) {
        SpringApplication.run(Kafka2Application.class,args);
    }
}
