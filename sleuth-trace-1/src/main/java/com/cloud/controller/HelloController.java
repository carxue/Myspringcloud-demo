package com.cloud.controller;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.bean.User;
import com.google.gson.Gson;
import com.netflix.hystrix.HystrixCommandProperties;

@RestController
public class HelloController {
	private final Logger logger = Logger.getLogger(HelloController.class);
	
	private final String[] keysArray = {"a","b","c","d","e"};
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/client/hello",method=RequestMethod.GET)
	@ResponseBody
	public String index() throws InterruptedException{
		Random random = new Random();
		int sleep = random.nextInt(3000);
		logger.info("----------------服务提供者1将要休眠{"+sleep+"}S中模拟服务故障测试断路由--------------");
		Thread.sleep(sleep);
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("hello,host:"+instance.getHost()+",port:"+instance.getPort()+",server_id:"+instance.getServiceId());
		return "Client 111 Hello World";
	}
	
	@RequestMapping(value="/client/more/keys",method=RequestMethod.GET)
	@ResponseBody
	public String indexBatchKeys(@RequestParam String keys){
		StringBuilder sb = new StringBuilder();
		String[] keyChar = keys.split(",");
		for(String a :keyChar){
			int index = Integer.parseInt(a);
			if(index>=keysArray.length||index<0){
				sb.append("error");
			}else{
				sb.append(keysArray[index]);
			}
		}
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("hello,host:"+instance.getHost()+",port:"+instance.getPort()+",server_id:"+instance.getServiceId());
		return keys+" :Client 111 Hello World Match Parameter: "+sb.toString();
	}
	
	@RequestMapping(value="/client/hello1",method=RequestMethod.GET)
	@ResponseBody
	public String hello1(@RequestParam String name){
		//feign多种参数类型测试
		logger.info("Client 111-1 Hello "+name);
		return "Client 111-1 Hello "+name;
	}
	
	@RequestMapping(value="/client/hello2",method=RequestMethod.GET)
	@ResponseBody
	public User hello2(@RequestHeader String name,@RequestHeader Integer  age){
		//feign多种参数类型测试
		logger.info("Client 111-2 Hello "+name);
		User user = new User(name,age);
		return user;
	}
	
	@RequestMapping(value="/client/hello3",method=RequestMethod.POST)
	@ResponseBody
	public String hello3(@RequestBody User user){
		//feign多种参数类型测试
		Gson gson = new Gson();
		logger.info("Client 111-3 Hello "+gson.toJson(user));
		return "Client 111-3 Hello "+user.getName()+" , "+user.getAge();
	}
}
