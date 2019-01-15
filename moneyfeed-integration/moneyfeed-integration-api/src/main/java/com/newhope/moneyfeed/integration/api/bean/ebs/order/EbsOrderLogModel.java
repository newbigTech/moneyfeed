package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.util.Date;

public class EbsOrderLogModel extends BaseModel {
    private Long orderId;

    private String saleOrderId;

    /** 日志类型，
            1.调用EBS创建订单
            2.调用EBS支付订单
            3.抵用EBS取消订单
            4.EBS更新订单状态
            5.通知商场-创建订单结果
            6.通知商场-支付结果
            7.通知商场-取消订单结果
            8.通知商场-更新订单状态结果 */
    private String logType;

    /** 发送时间 */
    private Date sendTime;

    /** 商场原因 */
    private String errorMsg;

    /** 发送报文JSON */
    private String msgJson;

    /** 失败次数 */
    private Integer failCount;

    /** 成功/失败 */
    private Byte operationResult;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMsgJson() {
        return msgJson;
    }

    public void setMsgJson(String msgJson) {
        this.msgJson = msgJson;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Byte getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(Byte operationResult) {
        this.operationResult = operationResult;
    }
}