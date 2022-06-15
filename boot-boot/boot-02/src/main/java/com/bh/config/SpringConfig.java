package com.bh.config;

import com.bh.pojo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SpringConfig {


    @Bean("student1")
    public Student student(){
        return  new Student();
    }
}
