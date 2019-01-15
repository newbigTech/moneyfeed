package com.newhope.moneyfeed.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liming on 2018/11/23.
 */
@Configuration
@EnableAutoConfiguration
public class UserCheckEventConfig {


    /** 用户账单事件交换器 */
    @Value("${newhope.rabbitmq.message.usercheck.exchange-name}")
    private String userCheckEventExchangeName;
    /** operation bindingkey */
    @Value("${newhope.rabbitmq.message.usercheck.root-key}")
    private String userCheckEventBindingkey;
    /** 消息队列名字operation,用于smart-operation同步业务事件数据 */
    @Value("${newhope.rabbitmq.message.usercheck.queue-name}")
    private String userCheckEventQueueName;

    /** 交换器(死信队列)的名称 */
    @Value("${newhope.rabbitmq.message.dead-letter.exchange}")
    private String deadLetterExchangeName;

    /** 死信队列名字 */
    @Value("${newhope.rabbitmq.message.dead-letter.queue}")
    private String deadLetterQueueName;

    /** 死信队列的bindingkey */
    @Value("${newhope.rabbitmq.message.dead-letter.rootkey}")
    private String deadLetterBindingkey;

    public String getUserCheckEventExchangeName() {
        return userCheckEventExchangeName;
    }

    public void setUserCheckEventExchangeName(String userCheckEventExchangeName) {
        this.userCheckEventExchangeName = userCheckEventExchangeName;
    }

    public String getUserCheckEventBindingkey() {
        return userCheckEventBindingkey;
    }

    public void setUserCheckEventBindingkey(String userCheckEventBindingkey) {
        this.userCheckEventBindingkey = userCheckEventBindingkey;
    }

    public String getUserCheckEventQueueName() {
        return userCheckEventQueueName;
    }

    public void setUserCheckEventQueueName(String userCheckEventQueueName) {
        this.userCheckEventQueueName = userCheckEventQueueName;
    }

    @Bean(name="usercheckExchange")
    DirectExchange usercheckExchange() {
        return new DirectExchange(userCheckEventExchangeName);
    }
    @Bean(name="usercheckQueue")
    Queue usercheckQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", deadLetterExchangeName);
        args.put("x-dead-letter-routing-key", deadLetterBindingkey);
        return new Queue(userCheckEventQueueName, true, false, false, args);
    }
    @Bean(name="usercheckBinding")
    Binding usercheckBinding(@Qualifier("usercheckQueue") Queue usercheckQueue, @Qualifier("usercheckExchange")DirectExchange usercheckExchange) {
        return BindingBuilder.bind(usercheckQueue).to(usercheckExchange).with(userCheckEventBindingkey);
    }
}