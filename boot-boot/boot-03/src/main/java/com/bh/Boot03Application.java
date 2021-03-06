package com.bh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class Boot03Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot03Application.class, args);
	}

}
