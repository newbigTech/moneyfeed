package com.newhope.moneyfeed.mq.service;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.common.cache.MqCache;
import com.newhope.moneyfeed.common.util.PackageUtil;
import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.adapter.Message;
import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.adapter.event.IntegrationToOrderEvent;
import com.newhope.moneyfeed.mq.adapter.event.UserCheckEvent;
import com.newhope.moneyfeed.mq.adapter.processor.Processor;
import com.newhope.moneyfeed.mq.cmd.Command;
import com.newhope.moneyfeed.mq.common.Parameter;
import com.newhope.moneyfeed.mq.config.EventAMQPConfig;
import com.newhope.moneyfeed.mq.config.IntToOrderEventConfig;
import com.newhope.moneyfeed.mq.config.UserCheckEventConfig;
import com.newhope.moneyfeed.mq.sender.CommandSender;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
* @ClassName: SendMessageImpl  
* @Description: 消息发送业务类
*    
*/
@Service
public class MessageService {
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	//枚举类包路径
	@Value("${message.event.package}")
	private String eventPackage;
	//枚举类名匹配字符串
	private final String eventNamePattern = "^[a-zA-Z.]+Event$";
		
	@Autowired
    CommandSender sender;
	@Autowired
    EventAMQPConfig eventAMQPConfig;
	@Autowired
	MqCache mqCache;
	@Autowired
	UserCheckEventConfig userCheckEventConfig;
	@Autowired
	IntToOrderEventConfig intToOrderEventConfig;
	
	public void sendEventsMessage(Message message, String exchangeName, String rootKey, String processor, CommonEvent event)
			throws BizException {
		Command command = new Command();
		command.setProcessor(processor);
		command.setEvent(event);
		command.setMessage(message);
		try {
			sender.sendEventQueue(command, exchangeName, rootKey);
		} catch (Exception e) {
			logger.error("[MessageService.sendMessage]:发送mq异常", e);
			throw new BizException(ResultCode.BASE_MQ_SEND_FAILED);
		}
	}
	
	public void sendMessage(Message message, List<Parameter> parameters, Channel channel, String processor) throws BizException {
		Command command = new Command();
		command.setProcessor(processor);
		command.setParameters(parameters);
		command.setMessage(message);
		command.setChannel(channel);
		try {
			sender.sendDefaultQueue(command);
		} catch (Exception e) {
			logger.error("[MessageService.sendMessage]:发送mq异常", e);
			throw new BizException(ResultCode.BASE_MQ_SEND_FAILED);
		}
	}
	
	public void sendMessage(Message message, List<Parameter> parameters, String processor, CommonEvent event) throws BizException {
		Command command = new Command();
		command.setProcessor(processor);
		command.setEvent(event);
		command.setParameters(parameters);
		command.setMessage(message);
		try {
			sender.sendDefaultQueue(command);
		} catch (Exception e) {
			logger.error("[MessageService.sendMessage]:发送mq异常", e);
			throw new BizException(ResultCode.BASE_MQ_SEND_FAILED);
		}
	}
	
	/**
	 * 发送定时任务消息到MQ
	 * @param message
	 * @param parameters
	 * @param processor
	 * @param event
	 * @param ttl	定时时间(毫秒)
	 * @throws BizException
	 */
	public void sendTaskMessage(Message message, List<Parameter> parameters, String processor, CommonEvent event, Long ttl) throws BizException {
		Command command = new Command();
		command.setProcessor(processor);
		command.setEvent(event);
		command.setParameters(parameters);
		command.setMessage(message);
		try {
			sender.sendTaskQueue(command, ttl);
		} catch (Exception e) {
			logger.error("[MessageService.sendMessage]:发送mq异常", e);
			throw new BizException(ResultCode.BASE_MQ_SEND_FAILED);
		}
	}
	
	public void sendEventMessage(Message message, String exchangeName, String rootKey, String processor, CommonEvent event) 
			throws BizException {
		Command command = new Command();
		command.setProcessor(processor);
		command.setEvent(event);
		command.setMessage(message);
		try {
			sender.sendEventQueue(command, exchangeName, rootKey);
		} catch (Exception e) {
			logger.error("[MessageService.sendMessage]:发送mq异常", e);
			throw new BizException(ResultCode.BASE_MQ_SEND_FAILED);
		}
	}
	
	/**
	 * 获取所有消息事件类
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Map<String, Class<?>> getAllEvent() throws BizException {
		List<Class<?>> clazzList = null;
		try {
			clazzList = PackageUtil.getClass(eventPackage, eventNamePattern);
		} catch (ClassNotFoundException e) {
			logger.error("[MessageService.getAllEvent]:获取事件类异常", e);
			throw new BizException(ResultCode.DATA_ERROR);
		}
		if (CollectionUtils.isNotEmpty(clazzList)) {
			Map<String, Class<?>> eventMap = new HashMap<>();
			for (Class<?> clazz : clazzList) {
				eventMap.put(clazz.getSimpleName(), clazz);
			}
			return eventMap;
		}
		return null;
	}



	/**
	 * 发送userCheck事件消息数据到order
	 *
	 * @param data: json格式数据
	 * @throws BizException
	 */
	public void sendUserCheckEventsMessage(String data) throws BizException {
		Message message = new Message();
		message.setContent(data);
		Command command = new Command();
		command.setProcessor(Processor.USER_CHEKC_EVENT_PROCESSOR.getValue());
		command.setEvent(new UserCheckEvent());
		command.setMessage(message);
		try {
			sender.sendEventQueue(command, userCheckEventConfig.getUserCheckEventExchangeName(),
					userCheckEventConfig.getUserCheckEventBindingkey());
		} catch (Exception e) {
			logger.error("[MessageService.sendUserCheckEventMessage]:发送mq异常", e);
			throw new BizException("发送userCheck事件MQ异常");
		}
	}
	/**
	 * 发送业务事件消息数据Integration到Order
	 *
	 * @param data: json格式数据
	 * @throws BizException
	 */
	public void sendMessageFromIntToOrder(String data){
		Message message = new Message();
		message.setContent(data);
		Command command = new Command();
		command.setProcessor(Processor.INT_TO_ORDER_PROCESSOR.getValue());
		command.setEvent(new IntegrationToOrderEvent());
		command.setMessage(message);
		try {
			sender.sendEventQueue(command, intToOrderEventConfig.getIntToOrderEventExchangeName(),
					intToOrderEventConfig.getIntToOrderEventBindingkey());
		} catch (Exception e) {
			logger.error("[MessageService.sendMessageFromIntToOrder]:发送mq异常", e);
			throw new BizException("发送sendMessageFromIntToOrder事件MQ异常");
		}

	}

	
}
