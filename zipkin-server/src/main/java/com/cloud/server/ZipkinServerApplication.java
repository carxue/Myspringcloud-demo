package com.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
@ComponentScan(basePackages = { "com.cloud.*" })
public class ZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerApplication.class, args);
	}
}
