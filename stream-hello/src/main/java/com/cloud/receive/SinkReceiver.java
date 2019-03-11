package com.cloud.receive;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class SinkReceiver {

	private final Logger logger = Logger.getLogger(SinkReceiver.class);

	@StreamListener(Sink.INPUT)
	public void receive(Object payload) {
		logger.info("Receive:" + payload);
	}

}
