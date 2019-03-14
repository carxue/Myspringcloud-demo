package com.cloud.sender;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SendToProcessor {
	String INPUT = "send_to_input";
	String OUTPUT = "send_to_out";

	@Output(SendToProcessor.INPUT) // Sink.INPUT为消息通道的名称
	MessageChannel input();

	@Output(SendToProcessor.OUTPUT) // Sink.INPUT为消息通道的名称
	MessageChannel output();
}
