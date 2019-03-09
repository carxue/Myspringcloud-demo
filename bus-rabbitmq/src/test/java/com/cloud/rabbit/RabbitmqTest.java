package com.cloud.rabbit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cloud.rabbit.send.Sender;
import com.cloud.server.BusRabbitmqApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BusRabbitmqApplication.class)
public class RabbitmqTest {

	@Autowired
	private Sender sender;

	@Test
	// 出发城市查询
	public void rabbitSender() throws Exception {
		sender.send();
	}

}
