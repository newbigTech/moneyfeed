package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yuyanlin on 2018/12/17
 */
public class MoneyfeedToEbsOrderPayInBalanceTypeDtoReq implements Serializable {

    private static final long serialVersionUID = 2395847690950458525L;

    @NotBlank(message = "ebsOrderNo不能为空")
    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;

    @NotBlank(message = "cusNo不能为空")
    @ApiModelProperty(name = "cusNo", value = "客户编号")
    private String cusNo;

    @NotNull(message = "totalPayAmount不能为空")
    @ApiModelProperty(name = "totalPayAmount", value = "余额支付金额")
    private BigDecimal totalPayAmount;

    @NotNull(message = "totalBalanceAmount不能为空")
    @ApiModelProperty(name = "totalBalanceAmount", value = "余额支付金额")
    private BigDecimal totalBalanceAmount;

    @NotNull(message = "discountAmount不能为空")
    @ApiModelProperty(name = "discountAmount", value = "本次使用折扣金额【余额支付】方式时，=MIN(EBS在客户可用余额信息接口 “保证金”金额,订单金额)；")
    private BigDecimal discountAmount;

    @NotBlank(message = "currency不能为空")
    @ApiModelProperty(name = "currency", value = "币种CNY")
    private String currency;

    @NotBlank(message = "payDateString不能为空")
    @ApiModelProperty(name = "payDateString", value = "支付日期，字符串格式")
    private String payDateString;

    @NotBlank(message = "moneyfeedPayNo不能为空")
    @ApiModelProperty(name = "moneyfeedPayNo", value = "商城支付流水号")
    private String moneyfeedPayNo;

    @NotBlank(message = "orgId不能为空")
    @ApiModelProperty(name = "orgId", value = "ebs系统中，组织ID")
    private String orgId;
    
    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public BigDecimal getTotalBalanceAmount() {
        return totalBalanceAmount;
    }

    public void setTotalBalanceAmount(BigDecimal totalBalanceAmount) {
        this.totalBalanceAmount = totalBalanceAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayDateString() {
        return payDateString;
    }

    public void setPayDateString(String payDateString) {
        this.payDateString = payDateString;
    }

    public String getMoneyfeedPayNo() {
        return moneyfeedPayNo;
    }

    public void setMoneyfeedPayNo(String moneyfeedPayNo) {
        this.moneyfeedPayNo = moneyfeedPayNo;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
