package com.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = { "com.cloud.*" })
public class SleuthTrace2Application {

	public static void main(String[] args) {
		SpringApplication.run(SleuthTrace2Application.class, args);
	}
}
