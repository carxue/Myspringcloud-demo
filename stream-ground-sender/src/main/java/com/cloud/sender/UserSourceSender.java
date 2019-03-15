package com.cloud.sender;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding({MySink.class})
public class UserSourceSender {
	private final Logger logger = Logger.getLogger(UserSourceSender.class);
	
	@Bean
	@InboundChannelAdapter(value = MySink.REPLAY_SOURCE_CHANNEL, poller = @Poller(fixedDelay = "2000"))
	public MessageSource<String> timerMessageSource1() {
		logger.info("11111111111111111发送消息给消费者:"+MySink.REPLAY_SOURCE_CHANNEL);
		return () -> new GenericMessage<>("{\"name\":\"carlxue\",\"age\":22}");
	}
	
//	@StreamListener(MySink.REPLAY_SINK_CHANNEL)
//	public void receive(Object payload) {
//		logger.info("4444444@sendto返回的回执为:=====================Receive:" + payload);
//	}

}
