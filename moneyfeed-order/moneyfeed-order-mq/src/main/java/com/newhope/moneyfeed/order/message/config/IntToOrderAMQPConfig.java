package com.newhope.moneyfeed.order.message.config;


import com.newhope.moneyfeed.mq.config.AMQPConfig;
import com.newhope.moneyfeed.order.message.receiver.IntToOrderMessageReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
public class IntToOrderAMQPConfig {

	@Bean(name="intToOrderMessageListener")
	MessageListenerAdapter eventMessageListener(IntToOrderMessageReceiver intToOrderMessageReceiver) throws Exception {
		return new MessageListenerAdapter(intToOrderMessageReceiver) {
			{
				setDefaultListenerMethod("onMessage");
			}
		};
	}
	
	@Bean(name="userCheckMessageListenerContainer")
	SimpleMessageListenerContainer userCheckMessageListenerContainer(@Qualifier("intToOrderQueue") Queue queue,
																	 AMQPConfig amqpConfig, @Qualifier("intToOrderMessageListener") MessageListenerAdapter intToOrderMessageListener) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(amqpConfig.getConnectionFactory());
        container.setQueues(queue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(20);
        container.setConcurrentConsumers(10);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(intToOrderMessageListener);
		return container;
	}
}