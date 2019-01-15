package com.newhope.moneyfeed.integration.api.dto.response.ebs.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 商城请求EBS创建订单，"订单创建"结果中"赠品"的结果
 *
 * Created by yuyanlin on 2018/11/20
 */
public class MoneyfeedOrderCreatePresentDtoResult implements Serializable {

    private static final long serialVersionUID = -2530887892082377276L;

    @ApiModelProperty(name = "suppliesCode", value = "EBS物料编码")
    private String suppliesCode;

    @ApiModelProperty(name = "presentFeedCount", value = "赠包数量")
    private int presentFeedCount;

    @ApiModelProperty(name = "unit", value = "单位")
    private String unit;

    @ApiModelProperty(name = "organizationCode", value = "库存组织编码")
    private String organizationCode;

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }

    public int getPresentFeedCount() {
        return presentFeedCount;
    }

    public void setPresentFeedCount(int presentFeedCount) {
        this.presentFeedCount = presentFeedCount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
