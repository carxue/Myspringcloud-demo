package com.cloud.rabbit.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "my_hello")
public class Receiver {

	@RabbitHandler
	public void process(String hello) {
		System.out.println("Reveiver:" + hello);
	}

}
 