package com.cloud.sender;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

public interface SinkSender {
	
	@Output(Sink.INPUT)//Sink.INPUT为消息通道的名称
	public MessageChannel output();
	
	@Output("output-1")//output-1为消息通道的名称
	public MessageChannel output1();
	
	@Output("output-2")//output-2为消息通道的名称
	public MessageChannel output2();
	
	@Output("output-user")
	//测试直接注入通道传说对象，而不是字符串是否接收端可以自动转换
	public MessageChannel outputUser();

}
