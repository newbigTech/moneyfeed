package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderLogModel;

import java.util.Date;

/**
 * Created by yuyanlin on 2018/11/29
 */
public final class EbsOrderLogModelBuilder {
    private Long orderId;
    private String saleOrderId;
    private String logType;
    private Date sendTime;
    private String errorMsg;
    private String msgJson;
    private Integer failCount;
    private Byte operationResult;

    private EbsOrderLogModelBuilder() {
    }

    public static EbsOrderLogModelBuilder anEbsOrderLogModel() {
        return new EbsOrderLogModelBuilder();
    }

    public EbsOrderLogModelBuilder orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public EbsOrderLogModelBuilder saleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
        return this;
    }

    public EbsOrderLogModelBuilder logType(String logType) {
        this.logType = logType;
        return this;
    }

    public EbsOrderLogModelBuilder sendTime(Date sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    public EbsOrderLogModelBuilder errorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public EbsOrderLogModelBuilder msgJson(String msgJson) {
        this.msgJson = msgJson;
        return this;
    }

    public EbsOrderLogModelBuilder failCount(Integer failCount) {
        this.failCount = failCount;
        return this;
    }

    public EbsOrderLogModelBuilder operationResult(Byte operationResult) {
        this.operationResult = operationResult;
        return this;
    }

    public EbsOrderLogModel build() {
        EbsOrderLogModel ebsOrderLogModel = new EbsOrderLogModel();
        ebsOrderLogModel.setOrderId(orderId);
        ebsOrderLogModel.setSaleOrderId(saleOrderId);
        ebsOrderLogModel.setLogType(logType);
        ebsOrderLogModel.setSendTime(sendTime);
        ebsOrderLogModel.setErrorMsg(errorMsg);
        ebsOrderLogModel.setMsgJson(msgJson);
        ebsOrderLogModel.setFailCount(failCount);
        ebsOrderLogModel.setOperationResult(operationResult);
        return ebsOrderLogModel;
    }
}
