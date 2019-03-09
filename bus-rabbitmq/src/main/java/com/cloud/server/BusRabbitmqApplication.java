package com.cloud.server;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@SpringBootApplication
@ComponentScan(basePackages = { "com.cloud.*" })
public class BusRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusRabbitmqApplication.class, args);
	}
}
