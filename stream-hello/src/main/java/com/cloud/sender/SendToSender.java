//package com.cloud.sender;
//
//import java.util.Date;
//
//import org.apache.log4j.Logger;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.integration.annotation.InboundChannelAdapter;
//import org.springframework.integration.annotation.Poller;
//import org.springframework.integration.core.MessageSource;
//import org.springframework.messaging.support.GenericMessage;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
//@EnableBinding({ SendToProcessor.class })
//@EnableScheduling
//public class SendToSender {
//	private final Logger logger = Logger.getLogger(SendToSender.class);
//
//	@Bean
//	@InboundChannelAdapter(value = SendToProcessor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
//	public MessageSource<Date> timerSendToMessageSource() {
//		return () -> new GenericMessage<>(new Date());
//	}
//
//	@StreamListener(SendToProcessor.INPUT)
//	public void receiveFromOutput(Object payload) {
//		logger.info("@sendto=====================Received:" + payload);
//	}
//
//}
