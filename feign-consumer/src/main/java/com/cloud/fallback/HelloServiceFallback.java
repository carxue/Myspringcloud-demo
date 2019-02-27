package com.cloud.fallback;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.bean.User;
import com.cloud.service.HelloService;

/**
 * feign定义服务降级——即hystrix容错调用方法
 *
 */
@Component
public class HelloServiceFallback implements HelloService {

	@Override
	public String helloService(@RequestParam("id") Long id) {
		return "error";
	}

	@Override
	public String hello1(@RequestParam("name") String name) {
		return "error";
	}

	@Override
	public User hello2(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		return new User("error",0);
	}

	@Override
	public String hello3(@RequestBody User user) {
		return "error";
	}

}
