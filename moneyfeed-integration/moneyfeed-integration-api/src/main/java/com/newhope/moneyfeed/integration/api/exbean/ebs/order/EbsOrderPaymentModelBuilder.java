package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderPaymentModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yuyanlin on 2018/12/20
 */
public final class EbsOrderPaymentModelBuilder {
    private Long orderId;
    private String dataStatus;
    private String payType;
    private BigDecimal totalPayAmount;
    private BigDecimal balanceAmount;
    private BigDecimal discountAmount;
    private String currency;
    private Boolean tempOwe;
    private Date payDate;
    private String moneyfeedPayNo;
    private String accNo;
    private String ebsRealAccount;

    private EbsOrderPaymentModelBuilder() {
    }

    public static EbsOrderPaymentModelBuilder anEbsOrderPaymentModel() {
        return new EbsOrderPaymentModelBuilder();
    }

    public EbsOrderPaymentModelBuilder orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public EbsOrderPaymentModelBuilder dataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    public EbsOrderPaymentModelBuilder payType(String payType) {
        this.payType = payType;
        return this;
    }

    public EbsOrderPaymentModelBuilder totalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
        return this;
    }

    public EbsOrderPaymentModelBuilder balanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
        return this;
    }

    public EbsOrderPaymentModelBuilder discountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public EbsOrderPaymentModelBuilder currency(String currency) {
        this.currency = currency;
        return this;
    }

    public EbsOrderPaymentModelBuilder tempOwe(Boolean tempOwe) {
        this.tempOwe = tempOwe;
        return this;
    }

    public EbsOrderPaymentModelBuilder payDate(Date payDate) {
        this.payDate = payDate;
        return this;
    }

    public EbsOrderPaymentModelBuilder moneyfeedPayNo(String moneyfeedPayNo) {
        this.moneyfeedPayNo = moneyfeedPayNo;
        return this;
    }

    public EbsOrderPaymentModelBuilder accNo(String accNo) {
        this.accNo = accNo;
        return this;
    }

    public EbsOrderPaymentModelBuilder ebsRealAccount(String ebsRealAccount) {
        this.ebsRealAccount = ebsRealAccount;
        return this;
    }

    public EbsOrderPaymentModel build() {
        EbsOrderPaymentModel ebsOrderPaymentModel = new EbsOrderPaymentModel();
        ebsOrderPaymentModel.setOrderId(orderId);
        ebsOrderPaymentModel.setDataStatus(dataStatus);
        ebsOrderPaymentModel.setPayType(payType);
        ebsOrderPaymentModel.setTotalPayAmount(totalPayAmount);
        ebsOrderPaymentModel.setBalanceAmount(balanceAmount);
        ebsOrderPaymentModel.setDiscountAmount(discountAmount);
        ebsOrderPaymentModel.setCurrency(currency);
        ebsOrderPaymentModel.setTempOwe(tempOwe);
        ebsOrderPaymentModel.setPayDate(payDate);
        ebsOrderPaymentModel.setMoneyfeedPayNo(moneyfeedPayNo);
        ebsOrderPaymentModel.setAccNo(accNo);
        ebsOrderPaymentModel.setEbsRealAccount(ebsRealAccount);
        return ebsOrderPaymentModel;
    }
}
