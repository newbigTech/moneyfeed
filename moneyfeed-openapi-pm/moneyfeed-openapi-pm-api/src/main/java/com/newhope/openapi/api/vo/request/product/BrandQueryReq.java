package com.newhope.openapi.api.vo.request.product;

import com.newhope.moneyfeed.api.vo.request.PageReq;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-openapi-pm
 * @date : 2018/12/24 08:58
 */
public class BrandQueryReq extends PageReq implements Serializable {
    private static final long serialVersionUID = -7662369487409003631L;

    @ApiModelProperty(name = "brandId", value = "品牌id")
    private Long brandId;

    @ApiModelProperty(name = "brandName", value = "品牌名称")
    private String brandName;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
