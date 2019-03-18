package com.cloud.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
	private final Logger logger = Logger.getLogger(HelloController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/trace-1",method=RequestMethod.GET)
	@ResponseBody
	public String trace() throws InterruptedException{
		logger.info("==============call trace-1=============");
		return restTemplate.getForEntity("http://sleuth-trace-2/trace-2", String.class).getBody();
	}
}
