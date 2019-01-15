package com.newhope.moneyfeed.mq.sender;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.newhope.moneyfeed.mq.cmd.Command;
import com.newhope.moneyfeed.mq.config.ConstantConfig;
import com.newhope.moneyfeed.mq.config.EventAMQPConfig;
import com.newhope.moneyfeed.mq.receiver.MessageReceiver;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newhope.cache.core.Cache;
import com.newhope.cache.core.RedisCache;
import com.newhope.moneyfeed.mq.config.AMQPConfig;
import com.newhope.moneyfeed.mq.config.ScheduledTaskAMQPConfig;

@Component
public class CommandSender implements RabbitTemplate.ConfirmCallback {
	
	private final Logger logger = LoggerFactory.getLogger(CommandSender.class);
	
	@Autowired
    @Qualifier("tradeRabbitTemplate")
	RabbitTemplate rabbitTemplate;
	@Autowired
	AMQPConfig aMQPConfig;
	@Autowired
	ScheduledTaskAMQPConfig scheduledTaskAMQPConfig;
	@Autowired
    EventAMQPConfig eventAMQPConfig;
	
	/** 消息缓存*/
	@Autowired
	@Qualifier("mqRedisCache")
	Cache cache;
	
	private RedisCache mqRedisCache;
	
	@PostConstruct
	private void init() {
		// cache初始化设置
		this.mqRedisCache = (RedisCache) this.cache;
		// rabbitTemplate设置
		rabbitTemplate.setConfirmCallback(this);
	}
	
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			// 消息缓存删除
			try {
				mqRedisCache.hDel(ConstantConfig.MQ_CACHE_RETRY_KEY, correlationData.getId());
			} catch (Exception e) {
				logger.error("[CommandSender.rabbitTemplate.setConfirmCallback.confirm]:redis删除缓存异常!", e);
				throw new RuntimeException("[CommandSender.rabbitTemplate.setConfirmCallback.confirm]:redis删除缓存异常!", e);
			}
		} else {
			logger.info("[CommandSender.rabbitTemplate.setConfirmCallback.confirm]:MQ nack! correlationId:" 
					+ correlationData.getId() + " Cause:" + cause);
		}
	}
	
	public void send(Command command, String exchangeName, String rootKey) throws Exception {
		String correlationId = generateCorrelationId();
		ObjectMapper mapper = new ObjectMapper();
		String trackerId = correlationId;
		CorrelationData correlationData = new CorrelationData(correlationId);
		Message message = MessageBuilder.withBody(mapper.writeValueAsBytes(command))
				.setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding(StandardCharsets.UTF_8.name())
				.setHeader(MessageReceiver.TRACKING_ID, trackerId)
				.setHeader(ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRY_KEY, ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRIES)
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_TIME_KEY, System.currentTimeMillis())
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_EXCHANGE, exchangeName)
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_ROOTKEY, rootKey)
				.build();
		//缓存message到redis,comfirm模式使用
		mqRedisCache.hSet(ConstantConfig.MQ_CACHE_RETRY_KEY, correlationId, message);
		//发送message
		rabbitTemplate.convertAndSend(exchangeName, rootKey, message, correlationData);
	}
	
	public void send(Message message, String exchangeName, String rootKey, String correlationId) throws Exception {
		CorrelationData correlationData = new CorrelationData(correlationId);
		//发送message
		rabbitTemplate.convertAndSend(exchangeName, rootKey, message, correlationData);
	}
	
	public void sendDefaultQueue(Command command) throws Exception {
		String correlationId = generateCorrelationId();
		ObjectMapper mapper = new ObjectMapper();
		String trackerId = correlationId;
		CorrelationData correlationData = new CorrelationData(correlationId);
		Message message = MessageBuilder.withBody(mapper.writeValueAsBytes(command))
				.setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding(StandardCharsets.UTF_8.name())
				.setHeader(MessageReceiver.TRACKING_ID, trackerId)
				.setHeader(ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRY_KEY, ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRIES)
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_TIME_KEY, System.currentTimeMillis())
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_EXCHANGE, aMQPConfig.getExchangeName())
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_ROOTKEY, aMQPConfig.getDefaultBindingkey())
				.build();
		//缓存message到redis,comfirm模式使用
		mqRedisCache.hSet(ConstantConfig.MQ_CACHE_RETRY_KEY, correlationId, message);
		rabbitTemplate.convertAndSend(aMQPConfig.getExchangeName(), aMQPConfig.getDefaultBindingkey(), message, correlationData);
	}
	 
	/**
	 * 发送定时任务队列
	 * @param command
	 * @param ttl	消息过期时间，过期后则扔到死队
	 * @throws Exception
	 */
	public void sendTaskQueue(Command command, Long ttl) throws Exception {
		String correlationId = CommandSender.generateCorrelationId();
		ObjectMapper mapper = new ObjectMapper();
		String trackerId = correlationId;
		CorrelationData correlationData = new CorrelationData(correlationId);
		Message message = MessageBuilder.withBody(mapper.writeValueAsBytes(command))
				.setExpiration(String.valueOf(ttl))
				.setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding(StandardCharsets.UTF_8.name())
				.setHeader(MessageReceiver.TRACKING_ID, trackerId)
				.setHeader(ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRY_KEY, ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRIES)
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_TIME_KEY, System.currentTimeMillis())
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_EXCHANGE, scheduledTaskAMQPConfig.getExchangeName())
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_ROOTKEY, scheduledTaskAMQPConfig.getBindingkey())
				.build();
		//缓存message到redis,comfirm模式使用
		mqRedisCache.hSet(ConstantConfig.MQ_CACHE_RETRY_KEY, correlationId, message);
		rabbitTemplate.convertAndSend(scheduledTaskAMQPConfig.getExchangeName(), scheduledTaskAMQPConfig.getBindingkey(), 
				message, correlationData);
	}
	
	public void sendEventQueue(Command command) throws Exception {
		String correlationId = generateCorrelationId();
		ObjectMapper mapper = new ObjectMapper();
		String trackerId = correlationId;
		CorrelationData correlationData = new CorrelationData(correlationId);
		Message message = MessageBuilder.withBody(mapper.writeValueAsBytes(command))
				.setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding(StandardCharsets.UTF_8.name())
				.setHeader(MessageReceiver.TRACKING_ID, trackerId)
				.setHeader(ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRY_KEY, ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRIES)
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_TIME_KEY, System.currentTimeMillis())
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_EXCHANGE, eventAMQPConfig.getEventExchangeName())
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_ROOTKEY, eventAMQPConfig.getEventBindingkey())
				.build();
		//缓存message到redis,comfirm模式使用
		mqRedisCache.hSet(ConstantConfig.MQ_CACHE_RETRY_KEY, correlationId, message);
		rabbitTemplate.convertAndSend(eventAMQPConfig.getEventExchangeName(), eventAMQPConfig.getEventBindingkey(), message, correlationData);
	}
	
	public void sendEventQueue(Command command, String exchangeName, String rootKey) throws Exception {
		String correlationId = generateCorrelationId();
		ObjectMapper mapper = new ObjectMapper();
		String trackerId = correlationId;
		CorrelationData correlationData = new CorrelationData(correlationId);
		Message message = MessageBuilder.withBody(mapper.writeValueAsBytes(command))
				.setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding(StandardCharsets.UTF_8.name())
				.setHeader(MessageReceiver.TRACKING_ID, trackerId)
				.setHeader(ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRY_KEY, ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRIES)
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_TIME_KEY, System.currentTimeMillis())
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_EXCHANGE, exchangeName)
				.setHeader(ConstantConfig.MQ_CACHE_RETRY_ROOTKEY, rootKey)
				.build();
		//缓存message到redis,comfirm模式使用
		mqRedisCache.hSet(ConstantConfig.MQ_CACHE_RETRY_KEY, correlationId, message);
		rabbitTemplate.convertAndSend(exchangeName, rootKey, message, correlationData);
	}
	
	public static String generateCorrelationId() {
		return UUID.randomUUID().toString().replaceAll("-", "") + RandomUtils.nextInt(10000);
	}
	
}
