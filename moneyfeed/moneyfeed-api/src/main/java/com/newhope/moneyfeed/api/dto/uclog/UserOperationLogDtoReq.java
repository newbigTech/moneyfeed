package com.newhope.moneyfeed.api.dto.uclog;

import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liming on 2018/11/29.
 */
public class UserOperationLogDtoReq {


    @ApiModelProperty(name = "userOperationEnums", value = "操作类型", notes = "操作类型")
    private UserOperEventType userOperEventType;

    /** 事件ID */
    @ApiModelProperty(name = "eventId", value = "事件ID", notes = "事件ID")
    private Long eventId;

    @ApiModelProperty(name = "ebsOrderId", value = "ebsOrderId", notes = "ebsOrderId")
    private String ebsOrderId;

    /** 订单id */
    @ApiModelProperty(name = "orderId", value = "订单id", notes = "订单id")
    private Long orderId;

    /** 银行流水号 */
    @ApiModelProperty(name = "payCode", value = "银行流水号", notes = "银行流水号")
    private String payCode;

    /** 事件发生日期 */
    @ApiModelProperty(name = "eventDate", value = "事件发生日期", notes = "事件发生日期")
    private Date eventDate;

    /** 事件发生前的金额 */
    @ApiModelProperty(name = "beforeEventAmount", value = "事件发生前的金额", notes = "事件发生前的金额")
    private BigDecimal beforeEventAmount;

    /** 事件发生后的金额 */
    @ApiModelProperty(name = "afterEventAmount", value = "事件发生后的金额", notes = "事件发生后的金额")
    private BigDecimal afterEventAmount;

    /** 事件发生前状态 */
    @ApiModelProperty(name = "beforeEventStatus", value = "事件发生前状态", notes = "事件发生前状态")
    private String beforeEventStatus;

    /** 事件发生后的状态 */
    @ApiModelProperty(name = "afterEventStatus", value = "事件发生后的状态", notes = "事件发生后的状态")
    private String afterEventStatus;

    @ApiModelProperty(name = "eventOperationType", value = "事件操作类型 ", notes = "事件操作类型")
    private UserOperationEnums userOperationEnums;

    /** 事件发生前对象JSON字符串 */
    @ApiModelProperty(name = "beforeEventModel", value = "事件发生前对象JSON字符串 ", notes = "事件发生前对象JSON字符串")
    private String beforeEventModel;

    /** 事件发生后对象JSON字符串 */
    @ApiModelProperty(name = "afterEventModel", value = "事件发生后对象JSON字符串 ", notes = "事件发生后对象JSON字符串")
    private String afterEventModel;

    private String dataStatus;

    /** 备注 */
    @ApiModelProperty(name = "comment", value = "备注 ", notes = "备注")
    private String comment;

    /** 来源（订单中心，支付中心，ebs中台） */
    @ApiModelProperty(name = "source", value = "来源（订单中心，支付中心，ebs中台） ", notes = "来源（订单中心，支付中心，ebs中台）")
    private UserOperSourceType source;



    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEbsOrderId() {
        return ebsOrderId;
    }

    public void setEbsOrderId(String ebsOrderId) {
        this.ebsOrderId = ebsOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public BigDecimal getBeforeEventAmount() {
        return beforeEventAmount;
    }

    public void setBeforeEventAmount(BigDecimal beforeEventAmount) {
        this.beforeEventAmount = beforeEventAmount;
    }

    public BigDecimal getAfterEventAmount() {
        return afterEventAmount;
    }

    public void setAfterEventAmount(BigDecimal afterEventAmount) {
        this.afterEventAmount = afterEventAmount;
    }

    public String getBeforeEventStatus() {
        return beforeEventStatus;
    }

    public void setBeforeEventStatus(String beforeEventStatus) {
        this.beforeEventStatus = beforeEventStatus;
    }

    public String getAfterEventStatus() {
        return afterEventStatus;
    }

    public void setAfterEventStatus(String afterEventStatus) {
        this.afterEventStatus = afterEventStatus;
    }


    public String getBeforeEventModel() {
        return beforeEventModel;
    }

    public void setBeforeEventModel(String beforeEventModel) {
        this.beforeEventModel = beforeEventModel;
    }

    public String getAfterEventModel() {
        return afterEventModel;
    }

    public void setAfterEventModel(String afterEventModel) {
        this.afterEventModel = afterEventModel;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserOperEventType getUserOperEventType() {
        return userOperEventType;
    }

    public void setUserOperEventType(UserOperEventType userOperEventType) {
        this.userOperEventType = userOperEventType;
    }

    public UserOperationEnums getUserOperationEnums() {
        return userOperationEnums;
    }

    public void setUserOperationEnums(UserOperationEnums userOperationEnums) {
        this.userOperationEnums = userOperationEnums;
    }

    public UserOperSourceType getSource() {
        return source;
    }

    public void setSource(UserOperSourceType source) {
        this.source = source;
    }
}