package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbsForResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yuyanlin on 2018/12/18
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class EbsOrderForPayOrChargeForResultReq {

    // EBS订单编码
    private String ebsOrderNo;

    // 客户编码
    private String customerNo;

    // 支付方式
    private String payOrChargeType;

    // 银行支付流水号
    private String accNo;
    
    //OU编码
    private String orgId;

    @XmlElement(name = "VALUE1")
    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    @XmlElement(name = "VALUE2")
    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    @XmlElement(name = "VALUE3")
    public String getPayOrChargeType() {
        return payOrChargeType;
    }

    public void setPayOrChargeType(String payOrChargeType) {
        this.payOrChargeType = payOrChargeType;
    }

    @XmlElement(name = "VALUE4")
    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    @XmlElement(name = "VALUE5")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
    
	
    
}
