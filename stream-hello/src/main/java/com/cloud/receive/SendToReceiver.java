//package com.cloud.receive;
//
//import org.apache.log4j.Logger;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.messaging.handler.annotation.SendTo;
//
//import com.cloud.sender.SendToProcessor;
//
//@EnableBinding({SendToProcessor.class})
//public class SendToReceiver {
//	private final Logger logger = Logger.getLogger(SendToReceiver.class);
//
//	@StreamListener(SendToProcessor.INPUT)
//	@SendTo(SendToProcessor.OUTPUT)
//	public Object receiveFromInput(Object payload) {
//		//@ServiceActivator不支持json和xml转换需要提供专门的@Transformer注解的转换方法
//		logger.info("@sendto=====================Receive:" + payload);
//		return "From sendto_input Channel Return - "+payload;
//	}
//	
//}
