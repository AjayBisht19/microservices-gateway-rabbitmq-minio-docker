package com.filepost.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

public class MQConfig2 {
	

	public static final String Queue_2="File";
	public static final String EXCHANGE_2="FileExchange";
	public static final String ROUTING_KEY_2="File_Routing_Key";
	
	@Bean
	public Queue queue2() {
		return new Queue(Queue_2);
	}
	
	@Bean
	public TopicExchange exchange2() {
		return new TopicExchange(EXCHANGE_2);
	}
	
	@Bean
	public Binding binding2(Queue queue,TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_2);
	}

}
