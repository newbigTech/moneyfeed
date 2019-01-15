package com.newhope.moneyfeed.mq.adapter.event;

public class UserCheckEvent extends CommonEvent {

	private final static EventType type = EventType.USER_CHECK_EVENT;
	
	public UserCheckEvent() {
		super(type);
	}
}
