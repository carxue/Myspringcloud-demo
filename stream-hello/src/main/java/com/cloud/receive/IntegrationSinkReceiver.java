package com.cloud.receive;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

import com.cloud.bean.User;

@EnableBinding({Sink.class})
public class IntegrationSinkReceiver {
	private final Logger logger = Logger.getLogger(IntegrationSinkReceiver.class);

	@ServiceActivator(inputChannel="integration_input")
	public void receive(Object payload) {
		//@ServiceActivator不支持json和xml转换需要提供专门的@Transformer注解的转换方法
		logger.info("Integration=====================Receive:" + payload);
	}
	
	@StreamListener("integration_input_user")
	public void receive(User user) {
		//@StreamListener可以直接支持将传入的json或xml字符串转换为对象
		logger.info("Integration=====================Receive:" + user.toString());
	}
}
