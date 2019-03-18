package com.cloud.sender;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import com.cloud.bean.User;

@EnableBinding({MySink.class})
public class UserSourceSender {
	private final Logger logger = Logger.getLogger(UserSourceSender.class);
	
//	@Bean
//	@InboundChannelAdapter(value = MySink.REPLAY_SOURCE_CHANNEL, poller = @Poller(fixedDelay = "4000"))
	public MessageSource<User> timerMessageSource1() {
		logger.info("11111111111111111发送消息给消费者:"+MySink.REPLAY_SOURCE_CHANNEL);
//		return () -> new GenericMessage<>("{\"name\":\"carlxue\",\"age\":22}");
		return () -> {
			Double value = Math.random() * 10 % 2;
            int age = value.intValue();
            logger.info("current age : "+age);
            Map<String, Object> headers = new HashMap<>();
            headers.put("router", age);
			return new GenericMessage<>(new User("carlxue",age),headers);};
	}
	
	@StreamListener(MySink.REPLAY_SINK_CHANNEL)
	public void receive(Object payload) {
		logger.info("4444444@sendto返回的回执为:=====================Receive:" + payload);
	}

}
