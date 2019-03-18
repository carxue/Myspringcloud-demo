package com.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.bean.User;
import com.cloud.sender.MySink;

@EnableBinding(MySink.class)
@RestController
public class SenderController {
    @Autowired
    private MySink registerProcessor;

    @RequestMapping(method = RequestMethod.POST, path = "/send")
    public void write (@RequestBody User user){
    	//添加Header测试分区
        registerProcessor.replayOutput().send(MessageBuilder.withPayload(user).setHeader("router", user.getAge()).build());
    }
}
