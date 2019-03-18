package com.cloud.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private final Logger logger = Logger.getLogger(HelloController.class);
	
	
	@RequestMapping(value="/trace-2",method=RequestMethod.GET)
	@ResponseBody
	public String trace() throws InterruptedException{
		logger.info("==============<call trace-2>=============");
		return "trace2";
	}
}
