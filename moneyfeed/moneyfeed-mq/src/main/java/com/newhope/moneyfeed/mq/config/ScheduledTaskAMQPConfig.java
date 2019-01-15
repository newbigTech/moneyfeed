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
public class ScheduledTaskAMQPConfig {
	/** 交换器(将消息路由到一个或多个Queue中的组件)的名称 */
	@Value("${newhope.rabbitmq.message.task.exchange-name}")
	private String exchangeName;
	
	/** 服务中心自用的消息队列名字 */
	@Value("${newhope.rabbitmq.message.task.queue-name}")
	private String queueName;
	
	/** 服务中心自用的消息队列与交换器绑定的bindingkey */
	@Value("${newhope.rabbitmq.message.task.root-key}")
	private String bindingkey;
	
	/** 交换器(死信队列)的名称 */
	@Value("${newhope.rabbitmq.message.task.dead-letter.exchange}")
	private String deadLetterExchangeName;
	
	/** 死信队列名字 */
	@Value("${newhope.rabbitmq.message.task.dead-letter.queue}")
	private String deadLetterQueueName;
	
	/** 死信队列的bindingkey */
	@Value("${newhope.rabbitmq.message.task.dead-letter.rootkey}")
	private String deadLetterBindingkey;
	
	public String getExchangeName() {
		return exchangeName;
	}

	public String getBindingkey() {
		return bindingkey;
	}

	/**
	 * 死信queue
	 * @return
	 */
	@Bean(name="scheduledTaskDeadLetterQueue")
	Queue deadLetterQueue() {
		return new Queue(deadLetterQueueName, true);
	}
	
	/**
	 * 死信exchange
	 * @return
	 */
	@Bean(name="scheduledTaskDeadLetterDirectExchange")
	DirectExchange deadLetterDirectExchange() {
		return new DirectExchange(deadLetterExchangeName);
	}
	
	/**
	 * 死信exchange死信queue绑定
	 * @param queue
	 * @param directExchange
	 * @return
	 */
	@Bean(name="scheduledTaskDeadLetterBinding")
	Binding deadLetterBinding(@Qualifier("scheduledTaskDeadLetterQueue") Queue queue, @Qualifier("scheduledTaskDeadLetterDirectExchange")DirectExchange directExchange) {
		return BindingBuilder.bind(queue).to(directExchange).with(deadLetterBindingkey);
	}
	
	@Bean(name="scheduledTaskDirectExchange")
	DirectExchange directExchange() {
		return new DirectExchange(exchangeName);
	}

	@Bean(name="scheduledTaskQueue")
	Queue defaultQueue() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-dead-letter-exchange", deadLetterExchangeName);
		args.put("x-dead-letter-routing-key", deadLetterBindingkey);
		return new Queue(queueName, true, false, false, args);
	}

	@Bean(name="scheduledTaskBinding")
	Binding ordersBinding(@Qualifier("scheduledTaskQueue") Queue queue, @Qualifier("scheduledTaskDirectExchange")DirectExchange directExchange) {
		return BindingBuilder.bind(queue).to(directExchange).with(bindingkey);
	}

}