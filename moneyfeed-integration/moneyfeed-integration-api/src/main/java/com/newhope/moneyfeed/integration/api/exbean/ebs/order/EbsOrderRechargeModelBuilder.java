package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsRechargeModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yuyanlin on 2018/12/18
 */
public final class EbsOrderRechargeModelBuilder {
    private Long orderId;
    private String dataStatus;
    private String payType;
    private BigDecimal totalPayAmount;
    private String currency;
    private Boolean tempOwe;
    private Date payDate;
    private String moneyfeedPayNo;
    private String accNo;
    private String customerNo;
    private String payStatus;
    private String orgId;
    private String ebsRealAccount;

    private EbsOrderRechargeModelBuilder() {
    }

    public static EbsOrderRechargeModelBuilder anEbsOrderRechargeModel() {
        return new EbsOrderRechargeModelBuilder();
    }

    public EbsOrderRechargeModelBuilder orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public EbsOrderRechargeModelBuilder dataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    public EbsOrderRechargeModelBuilder payType(String payType) {
        this.payType = payType;
        return this;
    }

    public EbsOrderRechargeModelBuilder totalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
        return this;
    }

    public EbsOrderRechargeModelBuilder currency(String currency) {
        this.currency = currency;
        return this;
    }

    public EbsOrderRechargeModelBuilder tempOwe(Boolean tempOwe) {
        this.tempOwe = tempOwe;
        return this;
    }

    public EbsOrderRechargeModelBuilder payDate(Date payDate) {
        this.payDate = payDate;
        return this;
    }

    public EbsOrderRechargeModelBuilder moneyfeedPayNo(String moneyfeedPayNo) {
        this.moneyfeedPayNo = moneyfeedPayNo;
        return this;
    }

    public EbsOrderRechargeModelBuilder accNo(String accNo) {
        this.accNo = accNo;
        return this;
    }
    
    public EbsOrderRechargeModelBuilder customerNo(String customerNo) {
        this.customerNo = customerNo;
        return this;
    }

    public EbsOrderRechargeModelBuilder payStatus(String payStatus) {
        this.payStatus = payStatus;
        return this;
    }
    
    public EbsOrderRechargeModelBuilder orgId(String orgId) {
    	this.orgId = orgId;
    	return this;
    }
    
    public EbsOrderRechargeModelBuilder ebsRealAccount(String ebsRealAccount) {
    	this.ebsRealAccount = ebsRealAccount;
    	return this;
    }

    public EbsRechargeModel build() {
        EbsRechargeModel ebsRechargeModel = new EbsRechargeModel();
        ebsRechargeModel.setOrderId(orderId);
        ebsRechargeModel.setDataStatus(dataStatus);
        ebsRechargeModel.setPayType(payType);
        ebsRechargeModel.setTotalPayAmount(totalPayAmount);
        ebsRechargeModel.setCurrency(currency);
        ebsRechargeModel.setTempOwe(tempOwe);
        ebsRechargeModel.setPayDate(payDate);
        ebsRechargeModel.setMoneyfeedPayNo(moneyfeedPayNo);
        ebsRechargeModel.setAccNo(accNo);
        ebsRechargeModel.setCustomerNo(customerNo);
        ebsRechargeModel.setPayStatus(payStatus);
        ebsRechargeModel.setOrgId(orgId);
        ebsRechargeModel.setEbsRealAccount(ebsRealAccount);
        
        return ebsRechargeModel;
    }
}
