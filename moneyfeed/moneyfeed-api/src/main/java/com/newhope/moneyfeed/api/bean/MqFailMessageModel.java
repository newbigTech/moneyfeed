package com.newhope.moneyfeed.api.bean;

import java.util.Date;

public class MqFailMessageModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7266914743490063765L;

	private String trackingId;

    private String mqRetryExchange;

    private String mqRetryRootkey;

    private String mqMessageJson;

    private Date mqMessageTime;

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getMqRetryExchange() {
        return mqRetryExchange;
    }

    public void setMqRetryExchange(String mqRetryExchange) {
        this.mqRetryExchange = mqRetryExchange;
    }

    public String getMqRetryRootkey() {
        return mqRetryRootkey;
    }

    public void setMqRetryRootkey(String mqRetryRootkey) {
        this.mqRetryRootkey = mqRetryRootkey;
    }

    public String getMqMessageJson() {
        return mqMessageJson;
    }

    public void setMqMessageJson(String mqMessageJson) {
        this.mqMessageJson = mqMessageJson;
    }

    public Date getMqMessageTime() {
        return mqMessageTime;
    }

    public void setMqMessageTime(Date mqMessageTime) {
        this.mqMessageTime = mqMessageTime;
    }
}