package com.newhope.moneyfeed.mq.adapter.processor;



/**
 * message processor枚举，根据消费者端定义进行定义
 * @author RDC-201
 *
 */
public enum Processor {


	USER_CHEKC_EVENT_PROCESSOR("userChekcEventProcessor"),
	INT_TO_ORDER_PROCESSOR("integrationToOrderProcesser");

	private String value;

	private Processor(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
