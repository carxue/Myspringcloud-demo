package com.cloud.sender;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding({ IntegrationSinkSender.SinkOutput.class })
public class IntegrationSinkSender {
	private final Logger logger = Logger.getLogger(IntegrationSinkSender.class);

	@Bean
	@InboundChannelAdapter(value = SinkOutput.OUTPUT, poller = @Poller(fixedDelay = "2000"))
	public MessageSource<Date> timerMessageSource() {
		return () -> new GenericMessage<>(new Date());
	}

	@Bean
	@InboundChannelAdapter(value = SinkOutput.OUTPUT_USER, poller = @Poller(fixedDelay = "2000"))
	public MessageSource<String> userMessageSource() {
		//测试@StreamListener支持接收并转换json或xml为对象
		return () -> new GenericMessage<>("{\"name\":\"carlxue\",\"age\":22}");
		// return () -> new GenericMessage<>(new User("snow",22));//直接传对象也可以
	}

	public interface SinkOutput {
		String OUTPUT = "integration_input";
		String OUTPUT_USER = "integration_input_user";

		@Output(SinkOutput.OUTPUT) // Sink.INPUT为消息通道的名称
		MessageChannel output();

		@Output(SinkOutput.OUTPUT_USER) // Sink.INPUT为消息通道的名称
		MessageChannel outputUser();
	}

}
