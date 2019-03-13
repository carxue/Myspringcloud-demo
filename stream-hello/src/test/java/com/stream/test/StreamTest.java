package com.stream.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cloud.sender.SinkSender;
import com.cloud.server.StreamApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StreamApplication.class)
public class StreamTest {

	@Autowired
	private SinkSender sender;

	@Test
	public void sender() throws Exception {
		sender.output().send(MessageBuilder.withPayload("xuekui").build());
	}

}
