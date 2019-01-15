package com.newhope.moneyfeed.pay.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   支付订单表
 */
public class PayOrderLogModel extends BaseModel {
 
	private static final long serialVersionUID = 554267491103521455L;

	/** 商城订单ID */
    private Long payOrderId;

    /** 商城订单号 */
    private String payOrderNo;

    /** 支付订单状态-前 */
    private String beforeStatus;

    /** 支付订单状态-后 */
    private String afterStatus;

    /** ebs对应状态-前 */
    private String beforeEbsStatus;

    /** ebs对应状态-后 */
    private String afterEbsStatus;

    /** 对账前 */
    private String beforeCheckStatus;

    /** 对账后 */
    private String afterCheckStatus;

    /** 备注 */
    private String remark;

    public Long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(Long payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(String beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public String getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(String afterStatus) {
        this.afterStatus = afterStatus;
    }

    public String getBeforeEbsStatus() {
        return beforeEbsStatus;
    }

    public void setBeforeEbsStatus(String beforeEbsStatus) {
        this.beforeEbsStatus = beforeEbsStatus;
    }

    public String getAfterEbsStatus() {
        return afterEbsStatus;
    }

    public void setAfterEbsStatus(String afterEbsStatus) {
        this.afterEbsStatus = afterEbsStatus;
    }

    public String getBeforeCheckStatus() {
        return beforeCheckStatus;
    }

    public void setBeforeCheckStatus(String beforeCheckStatus) {
        this.beforeCheckStatus = beforeCheckStatus;
    }

    public String getAfterCheckStatus() {
        return afterCheckStatus;
    }

    public void setAfterCheckStatus(String afterCheckStatus) {
        this.afterCheckStatus = afterCheckStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}