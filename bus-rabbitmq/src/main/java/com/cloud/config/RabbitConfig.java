package com.cloud.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	
	@Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
	
	@Bean
    @Qualifier("my_hello")
    Queue lineQueue(RabbitAdmin rabbitAdmin) {
		System.out.println("==============================my_hello");
        Queue queue = new Queue("my_hello", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
	
	@Bean
    TopicExchange exchange(RabbitAdmin rabbitAdmin) {
		System.out.println("++++++++++++++++++++++++++exchange_my_hello");
        TopicExchange topicExchange = new TopicExchange("exchange_my_hello");
        rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }

//	@Bean
//	public Queue helloQueue() {
//		return new Queue("hello");
//	}
}
