package com.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.cloud.service"})//如果不指定接口路径将无法实现依赖实例化
@ComponentScan(basePackages = { "com.cloud.*" })
public class SpringCloudFeignApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFeignApplication.class, args);
	}
}
