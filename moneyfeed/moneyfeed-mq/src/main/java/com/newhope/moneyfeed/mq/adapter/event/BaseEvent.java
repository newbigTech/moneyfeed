package com.newhope.moneyfeed.mq.adapter.event;

import com.newhope.moneyfeed.common.helper.DateUtils;

import java.util.Random;
import java.util.UUID;


public class BaseEvent {
	
	private String id;
	
	private String createTime;
	
	private EventStatus eventStatus;
	
	public BaseEvent() {
		createTime = DateUtils.getDateString(DateUtils.yyyymmddhhmmss);
		this.id = UUID.randomUUID().toString().replaceAll("-", "").concat(String.valueOf(new Random().nextInt(10)));
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public EventStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}
}
