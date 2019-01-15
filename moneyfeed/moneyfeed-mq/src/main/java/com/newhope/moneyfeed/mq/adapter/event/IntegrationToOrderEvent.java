package com.newhope.moneyfeed.mq.adapter.event;

/**
 * Created by liming on 2018/12/18.
 */
public class IntegrationToOrderEvent extends CommonEvent  {

    private final static EventType type = EventType.INT_TO_ORDER_EVENT;

    public IntegrationToOrderEvent() {
        super(type);
    }
}