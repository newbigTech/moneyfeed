package com.newhope.moneyfeed.mq.cmd.processor.template;

import java.sql.Timestamp;
import java.util.List;

import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.cmd.Command;
import com.newhope.moneyfeed.mq.cmd.processor.AbstractProcessor;
import com.newhope.moneyfeed.mq.exception.CommandProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class ScheduledMessageProcessorTemplate extends AbstractProcessor {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledMessageProcessorTemplate.class);

	protected abstract List<Channel> getChannels(Command command);
    
	protected abstract Timestamp getScheduledTime(Command command);
	
	protected abstract CommonEvent getEvent(Command command);
	
	@Override
	protected void processImmediateMessage(Command command) throws CommandProcessException {
		logger.info("No need to process immediate message");		
	}

	@Override
	protected void processScheduledMessage(Command command) throws CommandProcessException {
		logger.info("start to process scheduled message");
		List<Channel> channels = getChannels(command);
		CommonEvent event = getEvent(command);
		sendMessage(command, channels, event, getScheduledTime(command));
	}
}
