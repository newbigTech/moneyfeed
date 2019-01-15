package com.newhope.openapi.api.vo.request.user;

import com.newhope.moneyfeed.api.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel("账户管理-财务信息请求参数")
public class FinancialInformationReq extends PageReq {
    @NotNull(message = "[year]年份不能为空")
    @ApiModelProperty("年份")
    private Integer year;
    @NotNull(message = "[month]月份不能为空")
    @ApiModelProperty("月份")
    private Integer month;
    @NotNull(message = "[userId]用户id不能为空")
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("支付方式：查询支付流水可以传入该字段")
    private String typeOfPay;
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTypeOfPay() {
        return typeOfPay;
    }

    public void setTypeOfPay(String typeOfPay) {
        this.typeOfPay = typeOfPay;
    }
}
