package com.cloud.receive;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import com.cloud.bean.User;
import com.cloud.sender.MySink;

@EnableBinding({MySink.class})
public class SinkReceiver {

	private final Logger logger = Logger.getLogger(SinkReceiver.class);

	@StreamListener(MySink.REPLAY_SINK_CHANNEL)
//	@SendTo(MySink.REPLAY_SINK_CHANNEL)
	public void receive(User user) {
		logger.info("22222222222=====================Receive:" + user);
//		return "33333333 receive smg :"+user.toString();
	}
}
