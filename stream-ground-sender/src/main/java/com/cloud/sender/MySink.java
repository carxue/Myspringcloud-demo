package com.cloud.sender;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
	
	String REPLAY_SINK_CHANNEL = "replay_sink_channel";
	String REPLAY_SOURCE_CHANNEL = "replay_source_channel";
	
	@Input(REPLAY_SINK_CHANNEL)//监听输入通道
	public SubscribableChannel replayInput();
	
	@Output(REPLAY_SOURCE_CHANNEL)//消息输出通道
	public MessageChannel replayOutput();

}
