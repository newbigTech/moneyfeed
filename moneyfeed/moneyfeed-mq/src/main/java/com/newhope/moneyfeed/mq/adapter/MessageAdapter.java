package com.newhope.moneyfeed.mq.adapter;

import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.exception.MessageProcessException;

public interface MessageAdapter {
	public boolean support(Channel.Type channelType);

	public void handle(Message message, Channel channel, CommonEvent event, EventAdapter eventAdapter) throws MessageProcessException;
	
	public void handle(Message message, Channel channel) throws MessageProcessException;
}
