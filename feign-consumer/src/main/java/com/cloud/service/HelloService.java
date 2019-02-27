package com.cloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.bean.User;
import com.cloud.config.FeignConfiguration;
import com.cloud.fallback.HelloServiceFallback;

/**
 * @author 43874
 * feign声明式接口服务不区分服务名大小写
 * hello-service或HELLO-SERVICE都可以
 *
 */
@FeignClient(name = "hello-service",configuration = FeignConfiguration.class,fallback=HelloServiceFallback.class)
public interface HelloService {
	/**
	 * 同步方法
	 * @return
	 */
	@RequestMapping(value="/client/hello",method=RequestMethod.GET)
	public String helloService(@RequestParam("id") Long id);
	
	@RequestMapping(value="/client/hello1",method=RequestMethod.GET)
	public String hello1(@RequestParam("name") String name);
	
	@RequestMapping(value="/client/hello2",method=RequestMethod.GET)
	public User hello2(@RequestHeader("name") String name,@RequestHeader("age") Integer  age);
	
	@RequestMapping(value="/client/hello3",method=RequestMethod.POST)
	public String hello3(@RequestBody User user);
}
