package com.bh;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@ConfigurationPropertiesScan("com.bh.properties")
@EnableConfigurationProperties
public class Boot04Application {

	public static void main(String[] args) {
		BeanDefinitionRegistry
		SpringApplication.run(Boot04Application.class, args);
	}

}
