package com.cloud.controller;

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

@RestController
public class HelloController {
	private final Logger logger = Logger.getLogger(HelloController.class);
	
	private final String[] keysArray = {"a","b","c","d","e"};
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/client/hello")
	@ResponseBody
	public String index(){
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("hello,host:"+instance.getHost()+",port:"+instance.getPort()+",server_id:"+instance.getServiceId());
		return "Client 222 Hello World";
	}
	
	@RequestMapping(value="/client/more/keys")
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
		return keys+" :Client 222 Hello World Match Parameter: "+sb.toString();
	}
	
	@RequestMapping(value="/client/hello1",method=RequestMethod.GET)
	@ResponseBody
	public String hello1(@RequestParam String name){
		//feign多种参数类型测试
		logger.info("Client 222-1 Hello "+name);
		return "Client 222-1 Hello "+name;
	}
	
	@RequestMapping(value="/client/hello2",method=RequestMethod.GET)
	@ResponseBody
	public User hello2(@RequestHeader String name,@RequestHeader Integer  age){
		//feign多种参数类型测试
		logger.info("Client 222-2 Hello "+name);
		User user = new User(name,age);
		return user;
	}
	
	@RequestMapping(value="/client/hello3",method=RequestMethod.POST)
	@ResponseBody
	public String hello3(@RequestBody User user){
		//feign多种参数类型测试
		Gson gson = new Gson();
		logger.info("Client 222-3 Hello "+gson.toJson(user));
		return "Client 222-3 Hello "+user.getName()+" , "+user.getAge();
	}
}
