package com.cloud.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cloud.service.HelloService;

@RestController
public class ConsumerController {
	private final Logger logger = Logger.getLogger(ConsumerController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HelloService helloService;
	
	
//	@RequestMapping(value="/ribbon/consumer")
//	public String index(){
//		logger.info("----------------ribbon请求---------------------");
//		return restTemplate.getForEntity("http://HELLO-SERVICE/client/hello", String.class).getBody();
//	}
	
	@RequestMapping(value="/ribbon-consumer/hystrix")
	public String indexHystrix(@RequestParam Long id){
		return helloService.helloService(id);
	}
	
	
//	@RequestMapping(value="/ribbon-consumer/asyn/hystrix")
//	public String asynIndexHystrix() throws Exception{//异步执行每次返回error
//		Future<String> future = helloService.asynHelloService();
//		return future.get();//10, TimeUnit.SECONDS
//	}
	
	@RequestMapping(value="/hystrix/collapser")
	public String singleHelloService(@RequestParam Long id){
		return helloService.singleHelloService(id);
	}
}
