package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 商城主动查询EBS"创建订单"结果，查询参数
 *
 * Created by yuyanlin on 2018/11/20
 */
public class MoneyfeedToEbsOrderCheckCreateDtoReq implements Serializable {

    private static final long serialVersionUID = 2327619272948401652L;

    @ApiModelProperty(name = "companyShortCode", value = "公司编码（EBS编码）")
    private String companyShortCode;

    @ApiModelProperty(name = "moneyfeedOrderId", value = "商城订单Id")
    private String moneyfeedOrderId;

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    public String getMoneyfeedOrderId() {
        return moneyfeedOrderId;
    }

    public void setMoneyfeedOrderId(String moneyfeedOrderId) {
        this.moneyfeedOrderId = moneyfeedOrderId;
    }
}
