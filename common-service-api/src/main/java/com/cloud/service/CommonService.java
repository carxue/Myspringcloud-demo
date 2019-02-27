package com.cloud.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.bean.User;

@RequestMapping("/common")
public interface CommonService {

	@RequestMapping(value = "/client/hello4", method = RequestMethod.GET)
	public String hello4(@RequestParam("name") String name);

	@RequestMapping(value = "/client/hello5", method = RequestMethod.GET)
	public User hello5(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

	@RequestMapping(value = "/client/hello6", method = RequestMethod.POST)
	public String hello6(@RequestBody User user);
}
