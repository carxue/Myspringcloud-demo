package com.cloud.rabbit.send;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String text = "hello" + new Date();
		System.out.println("Send ：" + text);
		this.rabbitTemplate.convertAndSend("my_hello", text);
	}
}
