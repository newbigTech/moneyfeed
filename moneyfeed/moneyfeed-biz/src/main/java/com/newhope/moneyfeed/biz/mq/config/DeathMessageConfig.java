package com.newhope.moneyfeed.biz.mq.config;

import com.newhope.moneyfeed.biz.mq.receiver.DeathMessageReceiver;
import com.newhope.moneyfeed.mq.config.AMQPConfig;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liming on 2018/12/21.
 */
@Configuration
@EnableAutoConfiguration
public class DeathMessageConfig {


    @Bean(name="deathMessageListener")
    MessageListenerAdapter eventMessageListener(DeathMessageReceiver deathMessageReceiver) throws Exception {
        return new MessageListenerAdapter(deathMessageReceiver) {
            {
                setDefaultListenerMethod("onMessage");
            }
        };
    }

    @Bean(name="userCheckMessageListenerContainer")
    SimpleMessageListenerContainer userCheckMessageListenerContainer(@Qualifier("deadLetterQueue") Queue queue,
                                                                     AMQPConfig amqpConfig, @Qualifier("deathMessageListener") MessageListenerAdapter deathMessageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(amqpConfig.getConnectionFactory());
        container.setQueues(queue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(20);
        container.setConcurrentConsumers(10);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(deathMessageListener);
        return container;
    }

}