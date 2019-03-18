package com.cloud.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.bean.User;
import com.cloud.service.CommonService;
import com.google.gson.Gson;

/**
 * @author 43874
 * 这种共用性的接口不再定义接口映射，而是在接口中直接定义会自动带过来
 * controller中执行定义控制器注解扫描
 *
 */
@RestController
public class CommonCotroller implements CommonService {

	private final Logger logger = Logger.getLogger(CommonCotroller.class);

	@Override
	public String hello4(@RequestParam("name") String name) {
		logger.info("Client 111-4 Hello " + name);
		return "Client 111-4 Hello " + name;
	}

	@Override
	public User hello5(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		// feign多种参数类型测试
		logger.info("Client 111-5 Hello " + name);
		User user = new User(name, age);
		return user;
	}

	@Override
	public String hello6(@RequestBody User user) {
		// feign多种参数类型测试
		Gson gson = new Gson();
		logger.info("Client 111-6 Hello " + gson.toJson(user));
		return "Client 111-6 Hello " + user.getName() + " , " + user.getAge();
	}
}
