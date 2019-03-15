//package com.stream.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.cloud.server.StreamGroundTestApplication;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = StreamGroundTestApplication.class)
//public class StreamTest {
//
//	
//	@Autowired
//	private MessageChannel input;//直接注入已经实例化的消息通道，通道名称必须与注解中配置的名称相同才可以正常引入
//	
//	@Autowired
//	@Qualifier("output-1")
//	private MessageChannel output1;//直接注入已经实例化的消息通道，通道名称必须与注解中配置的名称相同才可以正常引入
//	
//	@Autowired
//	@Qualifier("output-2")
//	private MessageChannel output2;//直接注入已经实例化的消息通道，通道名称必须与注解中配置的名称相同才可以正常引入
//	
//	@Autowired
//	@Qualifier("output-user")
//	private MessageChannel outputUser;//直接注入已经实例化的消息通道，通道名称必须与注解中配置的名称相同才可以正常引入
//
//	
//	@Test
//	public void input() throws Exception {
////		input.send(MessageBuilder.withPayload("xuekui").build());
//		output1.send(MessageBuilder.withPayload("xuekui111").build());
////		output2.send(MessageBuilder.withPayload("xuekui222").build());
//	}
//	
//	@Test
//	public void inputUser() throws Exception {
//		outputUser.send(MessageBuilder.withPayload("{\"name\":\"carlxue\",\"age\":22}").build());
//	}
//}
