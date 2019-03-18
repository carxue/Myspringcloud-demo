package com.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = { "com.cloud.*" })
public class SleuthTrace1Application {

	@Bean
	@LoadBalanced
	RestTemplate template(){
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SleuthTrace1Application.class, args);
	}
}
