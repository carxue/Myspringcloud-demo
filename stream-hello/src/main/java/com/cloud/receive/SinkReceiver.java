package com.cloud.receive;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.cloud.bean.User;
import com.cloud.sender.SinkSender;

@EnableBinding({Sink.class,SinkSender.class})
public class SinkReceiver {

	private final Logger logger = Logger.getLogger(SinkReceiver.class);

	@StreamListener(Sink.INPUT)
	public void receive(Object payload) {
		logger.info("input=====================Receive:" + payload);
	}
	
	@StreamListener("output-1")
	public void receive1(Object payload) {
		logger.info("output-1=====================Receive1:" + payload);
	}
	
	@StreamListener("output-2")
	public void receive2(Object payload) {
		logger.info("output-2=====================Receive2:" + payload);
	}
	
	@StreamListener("output-user")
	public void receiveUser(User user) {
		logger.info("output-user=====================Receive2:" + user.toString());
	}

}
