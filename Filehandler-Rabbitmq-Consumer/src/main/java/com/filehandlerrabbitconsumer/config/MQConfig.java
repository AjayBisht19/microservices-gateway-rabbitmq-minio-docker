package com.filehandlerrabbitconsumer.config;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class MQConfig {
	
	public static final String Queue="FileHandler";
	public static final String EXCHANGE="FileHandler_Exchange";
	public static final String ROUTING_KEY="FileHandler_Key";

	@Bean
	public Queue queue() {
		return new Queue(Queue);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	
//	@Bean
//	public Queue queue2() {
//		return new Queue(Queue_2);
//	}
//	
//	@Bean
//	public TopicExchange exchange2() {
//		return new TopicExchange(EXCHANGE_2);
//	}
//	
//	@Bean
//	public Binding binding2(Queue queue,TopicExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_2);
//	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}
	

}
