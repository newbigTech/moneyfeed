package com.newhope.moneyfeed.mq.cmd.processor.template;

import java.util.List;

import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.cmd.processor.AbstractProcessor;
import com.newhope.moneyfeed.mq.exception.CommandProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.cmd.Command;


public abstract class ImmediateMessageProcessorTemplate extends AbstractProcessor {
	private static final Logger logger = LoggerFactory.getLogger(ImmediateMessageProcessorTemplate.class);

	protected abstract List<Channel> getChannels(Command command);
	
	protected abstract CommonEvent getEvent(Command command);

	@Override
	protected void processImmediateMessage(Command command) throws CommandProcessException {
		logger.info("start to process immediate message");
		List<Channel> channels = getChannels(command);
		CommonEvent event = getEvent(command);
		sendMessage(command, channels, event, null);
	}

	@Override
	protected void processScheduledMessage(Command command) {
		logger.info("ImmediateMessageProcessorTemplate: not need to process scheduled message");
	}
}
