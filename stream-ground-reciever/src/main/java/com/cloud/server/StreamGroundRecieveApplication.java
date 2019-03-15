package com.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.cloud.*" })
public class StreamGroundRecieveApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamGroundRecieveApplication.class, args);
	}
}
