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
public class IntToOrderEventConfig {


    /** 用户账单事件交换器 */
    @Value("${newhope.rabbitmq.message.intToOrder.exchange-name}")
    private String intToOrderEventExchangeName;
    /** operation bindingkey */
    @Value("${newhope.rabbitmq.message.intToOrder.root-key}")
    private String intToOrderEventBindingkey;
    /** 消息队列名字operation,用于smart-operation同步业务事件数据 */
    @Value("${newhope.rabbitmq.message.intToOrder.queue-name}")
    private String intToOrderEventQueueName;

    /** 交换器(死信队列)的名称 */
    @Value("${newhope.rabbitmq.message.dead-letter.exchange}")
    private String deadLetterExchangeName;

    /** 死信队列名字 */
    @Value("${newhope.rabbitmq.message.dead-letter.queue}")
    private String deadLetterQueueName;

    /** 死信队列的bindingkey */
    @Value("${newhope.rabbitmq.message.dead-letter.rootkey}")
    private String deadLetterBindingkey;


    public String getIntToOrderEventExchangeName() {
        return intToOrderEventExchangeName;
    }

    public void setIntToOrderEventExchangeName(String intToOrderEventExchangeName) {
        this.intToOrderEventExchangeName = intToOrderEventExchangeName;
    }

    public String getIntToOrderEventBindingkey() {
        return intToOrderEventBindingkey;
    }

    public void setIntToOrderEventBindingkey(String intToOrderEventBindingkey) {
        this.intToOrderEventBindingkey = intToOrderEventBindingkey;
    }

    public String getIntToOrderEventQueueName() {
        return intToOrderEventQueueName;
    }

    public void setIntToOrderEventQueueName(String intToOrderEventQueueName) {
        this.intToOrderEventQueueName = intToOrderEventQueueName;
    }

    public String getDeadLetterExchangeName() {
        return deadLetterExchangeName;
    }

    public void setDeadLetterExchangeName(String deadLetterExchangeName) {
        this.deadLetterExchangeName = deadLetterExchangeName;
    }

    public String getDeadLetterQueueName() {
        return deadLetterQueueName;
    }

    public void setDeadLetterQueueName(String deadLetterQueueName) {
        this.deadLetterQueueName = deadLetterQueueName;
    }

    public String getDeadLetterBindingkey() {
        return deadLetterBindingkey;
    }

    public void setDeadLetterBindingkey(String deadLetterBindingkey) {
        this.deadLetterBindingkey = deadLetterBindingkey;
    }

    @Bean(name="intToOrderExchange")
    DirectExchange intToOrderExchange() {
        return new DirectExchange(intToOrderEventExchangeName);
    }
    @Bean(name="intToOrderQueue")
    Queue intToOrderQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", deadLetterExchangeName);
        args.put("x-dead-letter-routing-key", deadLetterBindingkey);
        return new Queue(intToOrderEventQueueName, true, false, false, args);
    }
    @Bean(name="intToOrderBinding")
    Binding intToOrderBinding(@Qualifier("intToOrderQueue") Queue intToOrderQueue, @Qualifier("intToOrderExchange")DirectExchange intToOrderExchange) {
        return BindingBuilder.bind(intToOrderQueue).to(intToOrderExchange).with(intToOrderEventBindingkey);
    }
}