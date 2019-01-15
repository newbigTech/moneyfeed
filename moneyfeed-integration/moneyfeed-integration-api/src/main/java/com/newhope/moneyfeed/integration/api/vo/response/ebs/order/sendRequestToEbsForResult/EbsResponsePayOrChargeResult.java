package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yuyanlin on 2018/12/18
 */
@XmlRootElement(name = "HEADER")
public class EbsResponsePayOrChargeResult {

    // EBS订单编码
    private String ebsOrderNo;

    // 客户编码
    private String customerNo;

    // 支付方式
    private String payOrChargeType;

    // 商城支付流水号
    private String moneyfeedPayNo;

    // 银行支付流水号
    private String accNo;
    
    // orgId
    private String orgId;

    // 返回结果，成功返回S，失败返回E
    private String returnCode;

    // 返回信息
    private String returnMsg;

    @XmlElement(name = "EBS_ORDER_NO")
    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    @XmlElement(name = "CUSTOMER_NO")
    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    @XmlElement(name = "PAY_OR_CHARGE_TYPE")
    public String getPayOrChargeType() {
        return payOrChargeType;
    }

    public void setPayOrChargeType(String payOrChargeType) {
        this.payOrChargeType = payOrChargeType;
    }

    @XmlElement(name = "ACC_NO")
    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

	@XmlElement(name = "RETURN_MSG")
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    @XmlElement(name = "RETURN_CODE")
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @XmlElement(name = "MONEYFEED_PAY_NO")
    public String getMoneyfeedPayNo() {
        return moneyfeedPayNo;
    }

    public void setMoneyfeedPayNo(String moneyfeedPayNo) {
        this.moneyfeedPayNo = moneyfeedPayNo;
    }

    @XmlElement(name = "ORG_ID")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
