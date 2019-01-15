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
public class TxnAMQPConfig {
	
	/** 交换器(将消息路由到一个或多个Queue中的组件)的名称 */
	@Value("${newhope.rabbitmq.message.txn.exchange-name}")
	private String txnExchangeName;
	
	/** trade系统默认txn bindingkey */
	@Value("${newhope.rabbitmq.message.txn.root-key}")
	private String txnBindingkey;
	
	/** 服务中心自用的消息队列名字 */
	@Value("${newhope.rabbitmq.message.txn.queue-name}")
	private String txnQueueName;
	
	/** 交换器(死信队列)的名称 */
	@Value("${newhope.rabbitmq.message.txn.dead-letter.exchange}")
	private String deadLetterExchangeName;
	
	/** 死信队列名字 */
	@Value("${newhope.rabbitmq.message.txn.dead-letter.queue}")
	private String deadLetterQueueName;
	
	/** 死信队列的bindingkey */
	@Value("${newhope.rabbitmq.message.txn.dead-letter.rootkey}")
	private String deadLetterBindingkey;
	
	public String getTxnExchangeName() {
		return txnExchangeName;
	}

	public String getTxnBindingkey() {
		return txnBindingkey;
	}

	public String getTxnQueueName() {
		return txnQueueName;
	}

	/**
	 * 死信queue
	 * @return
	 */
	@Bean(name="txnDeadLetterQueue")
	Queue deadLetterQueue() {
		return new Queue(deadLetterQueueName, true);
	}
	
	/**
	 * 死信exchange
	 * @return
	 */
	@Bean(name="txnDeadLetterDirectExchange")
	DirectExchange deadLetterDirectExchange() {
		return new DirectExchange(deadLetterExchangeName);
	}
	
	/**
	 * 死信exchange死信queue绑定
	 * @param queue
	 * @param directExchange
	 * @return
	 */
	@Bean(name="txnDeadLetterBinding")
	Binding deadLetterBinding(@Qualifier("txnDeadLetterQueue") Queue queue, @Qualifier("txnDeadLetterDirectExchange")DirectExchange directExchange) {
		return BindingBuilder.bind(queue).to(directExchange).with(deadLetterBindingkey);
	}
	
	@Bean(name="txnExchange")
	DirectExchange directExchange() {
		return new DirectExchange(txnExchangeName);
	}
	
	@Bean(name="txnQueue")
	Queue txnQueue() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-dead-letter-exchange", deadLetterExchangeName);
		args.put("x-dead-letter-routing-key", deadLetterBindingkey);
		return new Queue(txnQueueName, true, false, false, args);
	}

	@Bean(name="txnBinding")
	Binding txnBinding(@Qualifier("txnQueue") Queue queue, @Qualifier("txnExchange")DirectExchange txnExchange) {
		return BindingBuilder.bind(queue).to(txnExchange).with(txnBindingkey);
	}
}