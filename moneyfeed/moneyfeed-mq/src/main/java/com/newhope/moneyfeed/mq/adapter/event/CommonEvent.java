package com.newhope.moneyfeed.mq.adapter.event;

import com.alibaba.fastjson.JSON;

public class CommonEvent extends BaseEvent implements Event {

	private EventType type = EventType.DEFAULT;
	
	private Object data;
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public CommonEvent() {
	}
	
	protected CommonEvent(EventType type) {
		this.type = type;
	}
	
	@Override
	public EventType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "CommonEvent [getType()=" + getType() + ", getId()=" + getId() + ", getCreateTime()=" + getCreateTime()
				+ ", getEventStatus()=" + getEventStatus() + "]";
	}

	public static void main(String[] args) {
		CommonEvent event = new CommonEvent();
		System.out.println(JSON.toJSONString(event));
	}
}
