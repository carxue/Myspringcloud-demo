package com.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cloud.filter.AccessFilter;

@EnableZuulProxy
@SpringBootApplication
@ComponentScan(basePackages = { "com.cloud.*" })
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {//定义自定义解析并创建路由规则，没有匹配则使用zuul默认路由规则
		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+s)", "${version}/${name}");
	}
}
