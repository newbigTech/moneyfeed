package com.newhope.moneyfeed.mq.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class EventAMQPConfig {
	
	/** 交换器(将消息路由到一个或多个Queue中的组件)的名称 */
	@Value("${newhope.rabbitmq.message.event.exchange-name}")
	private String eventExchangeName;
	
	/** trade系统默认event bindingkey */
	@Value("${newhope.rabbitmq.message.event.root-key}")
	private String eventBindingkey;
	
	/** trade系统默认event bindingkey */
	@Value("${newhope.rabbitmq.message.event.third.root-key}")
	private String thirdEventBindingkey;
	
	/** 服务中心自用的消息队列名字 */
	@Value("${newhope.rabbitmq.message.event.queue-name}")
	private String eventQueueName;
	/** 服务中心自用的消息队列名字 */
	@Value("${newhope.rabbitmq.message.event.bi.queue-name}")
	private String biQueueName;
	
	/** 死信队列的bindingkey */
	@Value("${newhope.rabbitmq.message.dead-letter.rootkey}")
	private String deadLetterBindingkey;
	/** 交换器(死信队列)的名称 */
	@Value("${newhope.rabbitmq.message.dead-letter.exchange}")
	private String deadLetterExchangeName;
	
	public String getThirdEventBindingkey() {
		return thirdEventBindingkey;
	}

	public String getEventExchangeName() {
		return eventExchangeName;
	}

	public String getEventBindingkey() {
		return eventBindingkey;
	}

	public String getEventQueueName() {
		return eventQueueName;
	}

	public String getBiQueueName() {
		return biQueueName;
	}

	@Bean(name="eventExchange")
	DirectExchange directExchange() {
		return new DirectExchange(eventExchangeName);
	}
	
	@Bean(name="biQueue")
	Queue biQueue() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-dead-letter-exchange", deadLetterExchangeName);
		args.put("x-dead-letter-routing-key", deadLetterBindingkey);
		return new Queue(biQueueName, true, false, false, args);
	}
	
	@Bean(name="eventQueue")
	Queue eventQueue() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-dead-letter-exchange", deadLetterExchangeName);
		args.put("x-dead-letter-routing-key", deadLetterBindingkey);
		return new Queue(eventQueueName, true, false, false, args);
	}
	
	@Bean(name="thirdEventBinding")
	Binding thirdEventBinding(@Qualifier("biQueue") Queue queue, @Qualifier("eventExchange")DirectExchange eventExchange) {
		return BindingBuilder.bind(queue).to(eventExchange).with(thirdEventBindingkey);
	}

	@Bean(name="eventBinding")
	Binding eventBinding(@Qualifier("eventQueue") Queue queue, @Qualifier("eventExchange")DirectExchange eventExchange) {
		return BindingBuilder.bind(queue).to(eventExchange).with(eventBindingkey);
	}
}