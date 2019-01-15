package com.newhope.moneyfeed.mq.adapter.event;

public enum EventType {
	DEFAULT("Default"),
	USER_CHECK_EVENT("userCheckEvent"),
	INT_TO_ORDER_EVENT("integrationToOrderEvent"),
    INVENTORY_CHANGE_EVENT("InventoryChangeEvent")
    ;


    //事件名称，对应事件类名
	private String event;
	
	EventType(String event) {
		this.event = event;
	}

	public String getEvent() {
		return event;
	}
}
