/*package com.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	private CounterService counterService;
	
	@RequestMapping(name="/server/hello")
	public String index(){
		//jar中自带,可以实现用户自定义统计信息
		counterService.increment("didispace.hello.count");
		return "Server Hello World";
	}
}
*/