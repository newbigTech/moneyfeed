package com.newhope.moneyfeed.user.message.config;


import com.newhope.moneyfeed.mq.config.AMQPConfig;
import com.newhope.moneyfeed.user.message.receiver.UserCheckMessageReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
public class UserCheckAMQPConfig {

	@Bean(name="userCheckMessageListener")
	MessageListenerAdapter eventMessageListener(UserCheckMessageReceiver userCheckMessageReceiver) throws Exception {
		return new MessageListenerAdapter(userCheckMessageReceiver) {
			{
				setDefaultListenerMethod("onMessage");
			}
		};
	}
	
	@Bean(name="userCheckMessageListenerContainer")
	SimpleMessageListenerContainer userCheckMessageListenerContainer(@Qualifier("usercheckQueue") Queue queue,
																	 AMQPConfig amqpConfig, @Qualifier("userCheckMessageListener") MessageListenerAdapter userCheckMessageListener) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(amqpConfig.getConnectionFactory());
        container.setQueues(queue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(20);
        container.setConcurrentConsumers(10);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(userCheckMessageListener);
		return container;
	}
}