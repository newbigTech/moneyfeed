package com.newhope.moneyfeed.mq.adapter;

import java.util.List;

import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.exception.MessageProcessException;


public interface MessageHandler {
	public void process(Message message, Channel channel, CommonEvent event) throws MessageProcessException;

	public void process(List<Message> messages, Channel channel, CommonEvent event) throws MessageProcessException;

	public void process(Message message, List<Channel> channels, CommonEvent event) throws MessageProcessException;
}
