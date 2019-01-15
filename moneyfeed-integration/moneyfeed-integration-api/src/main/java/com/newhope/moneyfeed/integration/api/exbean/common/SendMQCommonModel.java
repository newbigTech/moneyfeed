package com.newhope.moneyfeed.integration.api.exbean.common;

import com.newhope.moneyfeed.integration.api.enums.common.MQMsgKindEnum;
import com.newhope.moneyfeed.integration.api.enums.common.MQSendToClientEnum;

/**
 * 发送MQ消息的MODEL
 * Created by Dave Chen on 2018/12/17.
 */
public class SendMQCommonModel {

    /**
     *主要发送内容
     */
    private  Object Data;

    /**
     * MQ消息类型
     */
    private MQMsgKindEnum mqMsgKind;


    /**
     * 接收MQ的消费方
     */
    private MQSendToClientEnum mqSendToClient;

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public MQMsgKindEnum getMqMsgKind() {
        return mqMsgKind;
    }

    public void setMqMsgKind(MQMsgKindEnum mqMsgKind) {
        this.mqMsgKind = mqMsgKind;
    }

    public MQSendToClientEnum getMqSendToClient() {
        return mqSendToClient;
    }

    public void setMqSendToClient(MQSendToClientEnum mqSendToClient) {
        this.mqSendToClient = mqSendToClient;
    }
}
