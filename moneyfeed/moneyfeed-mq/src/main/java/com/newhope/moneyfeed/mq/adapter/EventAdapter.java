package com.newhope.moneyfeed.mq.adapter;

import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.adapter.event.EventType;
import com.newhope.moneyfeed.mq.exception.MessageProcessException;

public interface EventAdapter {	
	public boolean match(EventType eventType);
	public void eventHandle(Message message, CommonEvent event) throws MessageProcessException;
}
