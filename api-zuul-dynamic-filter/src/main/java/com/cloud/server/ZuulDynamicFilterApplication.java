package com.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cloud.config.FilterConfiguration;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;

@EnableZuulProxy
@EnableConfigurationProperties({ FilterConfiguration.class })
@SpringBootApplication
@ComponentScan(basePackages = { "com.cloud.*" })
public class ZuulDynamicFilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulDynamicFilterApplication.class, args);
	}

	@Bean
	public FilterLoader zuulProperties(FilterConfiguration filterConfig) {
		FilterLoader filterLoader = FilterLoader.getInstance();
		filterLoader.setCompiler(new GroovyCompiler());
		try {
			FilterFileManager.setFilenameFilter(new GroovyFileFilter());
			//api网关服务每隔5秒钟从配置目录下filter/pre;filter/post获取groovy定义的过滤器，并对其进行编译和动态加载
			FilterFileManager.init(filterConfig.getInterval(), filterConfig.getRoot() + "/pre",filterConfig.getRoot() + "/post");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return filterLoader;
	}
}
