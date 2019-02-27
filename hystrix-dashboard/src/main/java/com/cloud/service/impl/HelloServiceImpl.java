package com.cloud.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cloud.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

@Service
public class HelloServiceImpl implements HelloService {

	private final Logger logger = Logger.getLogger(HelloServiceImpl.class);

	private static AtomicInteger count = new AtomicInteger(0);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@CacheResult(cacheKeyMethod = "helloService")
	@HystrixCommand(fallbackMethod = "firstHelloFallback", commandProperties = {
			@HystrixProperty(name = "requestCache.enabled", value = "true") })
	public String helloService(Long id) {// @CacheResult中的cacheKeyMethod会使@cacheKey配置失效
		// helloFallback服务降级执行的方法名称，访问权限无限制
		// 异常处理:忽略异常不降级@HystrixCommand(fallbackMethod="firstHelloFallback",ignoreExceptions={XXXexception.class})
		logger.info("----------------ribbon请求---------------------");
		return restTemplate.getForEntity("http://HELLO-SERVICE/client/hello", String.class).getBody();
	}
}
