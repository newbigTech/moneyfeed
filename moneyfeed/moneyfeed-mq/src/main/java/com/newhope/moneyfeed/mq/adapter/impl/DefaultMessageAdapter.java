package com.newhope.moneyfeed.mq.adapter.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.adapter.EventAdapter;
import com.newhope.moneyfeed.mq.adapter.Message;
import com.newhope.moneyfeed.mq.adapter.MessageAdapter;
import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.adapter.event.EventType;
import com.newhope.moneyfeed.mq.exception.MessageProcessException;

public class DefaultMessageAdapter implements MessageAdapter, EventAdapter {
	private static final Logger logger = LoggerFactory.getLogger(DefaultMessageAdapter.class);
	protected Channel.Type type;

	public DefaultMessageAdapter(Channel.Type channelType) {
		this.type = channelType;
	}

	public DefaultMessageAdapter() {
	}

	@Override
	public boolean support(Channel.Type channelType) {
		return this.type == channelType;
	}
	
	@Override
	public boolean match(EventType eventType) {
		for (EventType e : EventType.values()) {
			if (e.equals(eventType)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void handle(Message message, Channel channel) throws MessageProcessException {
		// 业务实现
	}
	
	@Override
	public void handle(Message message, Channel channel, CommonEvent event, EventAdapter eventAdapter) 
			throws MessageProcessException {
		eventAdapter.eventHandle(message, event);
	}

	@Override
	public void eventHandle(Message message, CommonEvent event) throws MessageProcessException {
		// 业务实现
	}
	
}
