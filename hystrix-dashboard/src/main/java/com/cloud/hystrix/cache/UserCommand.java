package com.cloud.hystrix.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

public class UserCommand extends HystrixCommand<String>{
	
	private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");
	
	private Long id;
	
	@Autowired
	private RestTemplate restTemplate;

	protected UserCommand(RestTemplate restTemplate,Long id) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup")));
		this.restTemplate = restTemplate;
		this.id = id;
	}

	@Override
	protected String run() throws Exception {
		return restTemplate.getForEntity("http://HELLO-SERVICE/client/hello", String.class).getBody();
	}
	
	@Override
	protected String getCacheKey() {
		return id.toString();
	}
	
	public static void flushCache(Long id){
		HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(id.toString());
	}
	
}
