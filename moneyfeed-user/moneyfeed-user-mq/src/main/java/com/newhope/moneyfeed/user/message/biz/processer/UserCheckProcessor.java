package com.newhope.moneyfeed.user.message.biz.processer;

import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.adapter.Message;
import com.newhope.moneyfeed.mq.adapter.MessageHandler;
import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.cmd.Command;
import com.newhope.moneyfeed.mq.cmd.processor.template.ImmediateMessageProcessorTemplate;
import com.newhope.moneyfeed.mq.exception.CommandProcessException;
import com.newhope.moneyfeed.mq.exception.MessageProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component("userChekcEventProcessor")
public class UserCheckProcessor extends ImmediateMessageProcessorTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(UserCheckProcessor.class);

	@Autowired
	@Lazy
	protected MessageHandler messageHandler;
	
	@Override
	protected List<Channel> getChannels(Command command) {
		Channel channel = new Channel();
		List<Channel> channels = new ArrayList<Channel>(1);
		channels.add(channel);
		return channels;
	}
	
	@Override
	protected CommonEvent getEvent(Command command) {
		return command.getEvent();
	}
	
	@Override
	protected Message createMessage(Command command) throws CommandProcessException {
		Message message = command.getMessage();
		//TODO: 构造message
		return message;
	}

	@Override
	protected void sendMessage(Command command, List<Channel> channels, CommonEvent event,
			Timestamp scheduledTime) throws CommandProcessException {
		Message message = createMessage(command);
		try {
			messageHandler.process(message, channels, event);
		} catch (MessageProcessException e) {
			logger.error("[PigOperationProcessor.sendMessage]消息处理异常", e);
			throw new CommandProcessException("消息处理异常");
		}
	}

}
