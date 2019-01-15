package com.newhope.moneyfeed.integration.api.dto.request.third;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * EBS核销红包请求
 *
 * @author : qideng
 * @project: trade
 * @date : 2018-05-07 18:50
 */
public class EbsHongBaoUsedDtoReq implements Serializable {
    private static final long serialVersionUID = -2659936291795503626L;

    @NotBlank(message = "totalAmount为必传参数")
    @ApiModelProperty(name = "totalAmount", notes = "消费总金额", required = true)
    public BigDecimal totalAmount;

    @NotBlank(message = "useAmount为必传参数")
    @ApiModelProperty(name = "useAmount", notes = "使用红包金额", required = true)
    public BigDecimal useAmount;

    @NotBlank(message = "mobile为必传参数")
    @ApiModelProperty(name = "mobile", notes = "使用手机号", required = true)
    public String mobile;

    @NotBlank(message = "operType为必传参数")
    @ApiModelProperty(name = "operType", notes = "操作类型", required = true)
    public String operType;

    @NotBlank(message = "authCode为必传参数")
    @ApiModelProperty(name = "authCode", notes = "使用验证码", required = true)
    public String authCode;

    @NotBlank(message = "weight为必传参数")
    @ApiModelProperty(name = "weight", notes = "购买饲料总量(KG)", required = true)
    public String weight;

    @NotBlank(message = "orderNumber为必传参数")
    @ApiModelProperty(name = "orderNumber", notes = "使用红包的订单号", required = true)
    public String orderNumber;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

}
