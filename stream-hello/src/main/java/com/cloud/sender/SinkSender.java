package com.cloud.sender;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

public interface SinkSender {
	
	@Output(Sink.INPUT)//Sink.INPUT为消息通道的名称
	public MessageChannel output();

}
