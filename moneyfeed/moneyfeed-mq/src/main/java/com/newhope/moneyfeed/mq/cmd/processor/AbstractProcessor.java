package com.newhope.moneyfeed.mq.cmd.processor;

import java.sql.Timestamp;
import java.util.List;

import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.exception.CommandProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.adapter.Message;
import com.newhope.moneyfeed.mq.cmd.Command;

public abstract class AbstractProcessor implements Processor {
	private static final Logger logger = LoggerFactory.getLogger(AbstractProcessor.class);

	public ExecutionResult execute(Command command) throws CommandProcessException {
		processImmediateMessage(command);
		processScheduledMessage(command);
		ExecutionResult result = new ExecutionResult();
		return result;
	}

	/**
	 * 根据command内容创建消息
	 * 
	 * @param command
	 * @return
	 * @throws CommandProcessException
	 */
	protected abstract Message createMessage(Command command) throws CommandProcessException;

	/**
	 * 封装并发送消息到handler进行处理
	 * 
	 * @param command
	 * @param event
	 * @param channels
	 * @param scheduledTime
	 * @throws CommandProcessException
	 */
	protected abstract void sendMessage(Command command, List<Channel> channels, CommonEvent event,
			Timestamp scheduledTime) throws CommandProcessException; 
	
	/**
	 * execute方法执行调用,模板方法,具体实现见{@ImmediateMessageProcessorTemplate}
	 * 
	 * @param command
	 * @throws CommandProcessException
	 */
	protected abstract void processImmediateMessage(Command command) throws CommandProcessException;

	/**
	 * execute方法执行调用,模板方法,具体实现见{@ScheduledMessageProcessorTemplate}
	 * 
	 * @param command
	 * @throws CommandProcessException
	 */
	protected abstract void processScheduledMessage(Command command) throws CommandProcessException;

}
