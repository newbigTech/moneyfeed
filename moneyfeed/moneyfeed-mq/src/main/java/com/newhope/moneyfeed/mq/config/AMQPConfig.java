package com.newhope.moneyfeed.mq.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
public class AMQPConfig {
	/** 交换器(将消息路由到一个或多个Queue中的组件)的名称 */
	@Value("${newhope.rabbitmq.message.exchange-name}")
	private String exchangeName;
	
	/** 服务中心自用的消息队列名字 */
	@Value("${newhope.rabbitmq.message.queue-name}")
	private String defaultQueueName;
	
	/** 服务中心自用的消息队列与交换器绑定的bindingkey */
	@Value("${newhope.rabbitmq.message.root-key}")
	private String defaultBindingkey;
	
	/** 交换器(死信队列)的名称 */
	@Value("${newhope.rabbitmq.message.dead-letter.exchange}")
	private String deadLetterExchangeName;
	
	/** 死信队列名字 */
	@Value("${newhope.rabbitmq.message.dead-letter.queue}")
	private String deadLetterQueueName;
	
	/** 死信队列的bindingkey */
	@Value("${newhope.rabbitmq.message.dead-letter.rootkey}")
	private String deadLetterBindingkey;

    @Value("${newhope.rabbitmq.address}")
    private String address;

    @Value("${newhope.rabbitmq.port}")
    private int port;

    @Value("${newhope.rabbitmq.username}")
    private String username;

    @Value("${newhope.rabbitmq.password}")
    private String password;

    private CachingConnectionFactory connectionFactory;
    
    @PostConstruct
    void init() {
    	connectionFactory = new CachingConnectionFactory();
    	connectionFactory.setAddresses(address);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(true);
        //connectionFactory.setPublisherReturns(true);
        connectionFactory.setChannelCacheSize(100);
        connectionFactory.setVirtualHost("/");
    }
    
	public String getExchangeName() {
		return exchangeName;
	}

	public String getDefaultQueueName() {
		return defaultQueueName;
	}

	public String getDefaultBindingkey() {
		return defaultBindingkey;
	}

    public CachingConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
	 * 死信queue
	 * @return
	 */
	@Bean(name="deadLetterQueue")
	Queue deadLetterQueue() {
		return new Queue(deadLetterQueueName, true);
	}
	
	/**
	 * 死信exchange
	 * @return
	 */
	@Bean(name="deadLetterDirectExchange")
	DirectExchange deadLetterDirectExchange() {
		return new DirectExchange(deadLetterExchangeName);
	}
	
	/**
	 * 死信exchange死信queue绑定
	 * @param queue
	 * @param directExchange
	 * @return
	 */
	@Bean(name="deadLetterBinding")
	Binding deadLetterBinding(@Qualifier("deadLetterQueue") Queue queue, @Qualifier("deadLetterDirectExchange")DirectExchange directExchange) {
		return BindingBuilder.bind(queue).to(directExchange).with(deadLetterBindingkey);
	}
	
	@Bean(name="directExchange")
	DirectExchange directExchange() {
		return new DirectExchange(exchangeName);
	}

	@Bean
    public RabbitAdmin admin(@Qualifier("directExchange") DirectExchange exchange) {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		rabbitAdmin.declareExchange(exchange);
		rabbitAdmin.setIgnoreDeclarationExceptions(true);
        return rabbitAdmin;
	}

    @Bean(name = "tradeRabbitTemplate")
	//@Scope("prototype")
    RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory);
	}

	@Bean(name="defaultQueue")
	Queue defaultQueue() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-dead-letter-exchange", deadLetterExchangeName);
		args.put("x-dead-letter-routing-key", deadLetterBindingkey);
		return new Queue(defaultQueueName, true, false, false, args);
	}

	@Bean(name="defaultBinding")
	Binding defaultBinding(@Qualifier("defaultQueue") Queue queue, @Qualifier("directExchange")DirectExchange directExchange) {
		return BindingBuilder.bind(queue).to(directExchange).with(defaultBindingkey);
	}

}