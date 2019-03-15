package com.cloud.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.sender.MySink;

@EnableBinding(MySink.class)
@RestController
public class SenderController {
    @Autowired
    private MySink registerProcessor;

    @RequestMapping(method = RequestMethod.POST, path = "/send")
    public void write (@RequestBody Map<String, Object> msg){
        registerProcessor.replayOutput().send(MessageBuilder.withPayload(msg).build());
    }
}
