package com.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;
import feign.Logger;

/**
 * @author wjg
 * @date 2017/12/8 15:39
 */
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
    
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder(){//指定不需要hystrix的服务
//    	return Feign.builder();
//    }
}
