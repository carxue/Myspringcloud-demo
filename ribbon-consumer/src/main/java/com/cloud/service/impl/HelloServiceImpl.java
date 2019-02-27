package com.cloud.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cloud.service.HelloService;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
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

	@Override
	@HystrixCommand(fallbackMethod = "helloFallback")
	public Future<String> asynHelloService() {// 异步执行每次返回error
		return new AsyncResult<String>(null) {
			@SuppressWarnings("unused")
			public String invoke() {
				String result = restTemplate.getForEntity("http://HELLO-SERVICE/client/hello", String.class).getBody();
				return result;
			}
		};
	}

	@HystrixCommand(fallbackMethod = "secondHelloFallback")
	public String firstHelloFallback(Long id, Throwable e) {
		logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>firstHelloFallback.ribbon请求的异常信息为:" + e.getMessage());
		if (count.getAndAdd(1) % 2 == 0) {
			int a = 1 / 0;
			System.out.println(a);
		}
		return "fisrt error";
	}

	public String secondHelloFallback(Long id, Throwable e) {
		logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>secondHelloFallback.ribbon请求的异常信息为:" + e.getMessage());
		return "second error";
	}

	@Override
	@HystrixCollapser(scope = com.netflix.hystrix.HystrixCollapser.Scope.REQUEST, batchMethod = "moreHelloService", collapserProperties = {
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "3000"),
			@HystrixProperty(name = "maxRequestsInBatch", value = "5") })
	public String singleHelloService(Long id) {
		logger.info("=================================Collapser.singleHelloService合并请求前单独请求id:" + id);
		return null;
	}

	@HystrixCommand(fallbackMethod = "moreListFallback")
	public List<String> moreHelloService(List<Long> ids) {
		Gson gson = new Gson();
		logger.info("=================================Collapser.moreHelloService合并请求后的list:" + gson.toJson(ids));
		List<String> retList = new ArrayList<String>();
		String retStr = restTemplate.getForObject("http://HELLO-SERVICE/client/more/keys?keys={1}", String.class,StringUtils.join(ids, ","));
		logger.info("=================================Collapser.moreHelloService合并请求响应的结果为:" + retStr);
		if (StringUtils.isNotBlank(retStr)) {
			String[] arr = retStr.split(",");
			for (String a : arr) {
				retList.add(a);
			}
		}
		return retList;
	}

	public List<String> moreListFallback(List<Long> ids, Throwable e) {
		List<String> retList = new ArrayList<String>();
		for (long id : ids) {
			retList.add(id + "降级服务");
		}
		logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>hystrix.moreListFallback请求的异常信息为:" + e.getMessage());
		return retList;
	}

}
