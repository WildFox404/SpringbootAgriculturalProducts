package com.cow.spring_vue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;


@MapperScan("com.cow.spring_vue.mapper")
@SpringBootApplication(exclude = JacksonAutoConfiguration.class)
public class SpringVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringVueApplication.class, args);
	}
}
