package com.newhope.moneyfeed.order.message.receiver;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.mq.cmd.Command;
import com.newhope.moneyfeed.mq.cmd.processor.ExecutionResult;
import com.newhope.moneyfeed.mq.cmd.processor.Processor;
import com.newhope.moneyfeed.mq.config.ConstantConfig;
import com.newhope.moneyfeed.mq.config.IntToOrderEventConfig;
import com.newhope.moneyfeed.mq.config.UserCheckEventConfig;
import com.newhope.moneyfeed.mq.exception.CommandProcessException;
import com.newhope.moneyfeed.mq.receiver.MessageReceiver;
import com.newhope.moneyfeed.mq.sender.CommandSender;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IntToOrderMessageReceiver extends MessageReceiver implements ChannelAwareMessageListener {
	private static final Logger logger = LoggerFactory.getLogger(IntToOrderMessageReceiver.class);

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	IntToOrderEventConfig intToOrderEventConfig;

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		// 消息日志
		logMessage(message, logger);
		try {
			//将message转换为command进行后续处理
			ObjectMapper mapper = new ObjectMapper();
			Command command = mapper.readValue(message.getBody(), new TypeReference<Command>() {});
			String processorName = command.getProcessor();
			Processor processor = (Processor) AppContext.getSpringContext().getBean(processorName);
			
			if(processor == null){
				logger.info("GeneralMessageReceiver, processor为空");
				throw new CommandProcessException("GeneralMessageReceiver, processor为空");
			}
			// messsage to process
			ExecutionResult result = processor.execute(command);
			if (result.isError()) {
				String errorMsg = "failed to execute command:errorCode = " + result.getErrorCode() + ",errorMessage = " + result.getErrorMsg();
				logger.error(errorMsg);
				throw new CommandProcessException(errorMsg);
			}
			//手动应答
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch(CommandProcessException|RuntimeException e) {
			// reject message and requeued and resent to comsumer
			logger.error("消息处理异常", e);
			Map<String, Object> headers = message.getMessageProperties().getHeaders();
			int retry = ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRIES;
			if (headers != null && headers.get(ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRY_KEY) != null) {
				retry = (int) headers.get(ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRY_KEY);
				if (retry == 0) {	//消息加入死信队列
					channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
					return;
				} else {	//retry次数减1
					retry = retry - 1;
				}
			}
			// 1. 应答旧的message
			// 2. 重置MQ_PROPS_HEADER_RETRY_KEY&重发mq
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			message.getMessageProperties().setHeader(ConstantConfig.MQ_PROPS_HEADER_COMSUME_RETRY_KEY, retry);
			rabbitTemplate.convertAndSend(intToOrderEventConfig.getIntToOrderEventExchangeName(), intToOrderEventConfig.getIntToOrderEventBindingkey(), message,
					new CorrelationData(CommandSender.generateCorrelationId()));
			return;
		} catch(Exception e) {
			logger.error("消息处理异常", e);
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
			return;
		}
	}

}