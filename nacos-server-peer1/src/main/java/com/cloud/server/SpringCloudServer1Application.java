package com.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaServer
@SpringBootApplication
@ComponentScan(basePackages = { "com.cloud.*" })
public class SpringCloudServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServer1Application.class, args);
	}
}
