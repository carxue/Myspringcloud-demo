package com.cloud.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.bean.User;
import com.cloud.service.FeignCommonService;
import com.cloud.service.HelloService;

@RestController
public class ConsumerController {
	
	private final Logger logger = Logger.getLogger(ConsumerController.class);
	
	@Autowired
	private HelloService helloService;
	
	@Autowired
	private FeignCommonService feignCommonService;
	
	
	
	@RequestMapping(value="/feign-consumer",method=RequestMethod.GET)
	public String indexHystrix(@RequestParam Long id){
		return helloService.helloService(id);
	}
	
	@RequestMapping(value="/feign-consumer/paramtype/test",method=RequestMethod.GET)
	public String helloParamType(@RequestParam String name){
		StringBuilder sb = new StringBuilder();
		sb.append(helloService.hello1(name+"1")).append("\n");
		sb.append(helloService.hello2(name+"2",22)).append("\n");
		sb.append(helloService.hello3(new User(name+"3",23))).append("\n");
		return sb.toString();
	}
	
	@RequestMapping(value="/feign-consumer/common/api",method=RequestMethod.GET)
	public String commonApi(@RequestParam String name){
		StringBuilder sb = new StringBuilder();
		sb.append(feignCommonService.hello4(name+"1")).append("\n");
		sb.append(feignCommonService.hello5(name+"2",22)).append("\n");
		sb.append(feignCommonService.hello6(new User(name+"3",23))).append("\n");
		return sb.toString();
	}
}
