package com.bh.Qualifier;

import com.bh.config.SpringConfig;
import com.bh.pojo.Profile;
import com.bh.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QualifierTest {
    @Autowired
    @Qualifier("student1")
    Student student;

    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Profile profile = (Profile) context.getBean("profile");
        profile.printAge();
        profile.printName();
    }

    @Test
    public void test02(){
        ApplicationContext context = new  AnnotationConfigApplicationContext(SpringConfig.class);

    }
}
