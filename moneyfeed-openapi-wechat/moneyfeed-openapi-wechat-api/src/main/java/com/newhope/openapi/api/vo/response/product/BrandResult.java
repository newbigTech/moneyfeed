package com.newhope.openapi.api.vo.response.product;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/12/24 08:46
 */
public class BrandResult implements Serializable {

    private static final long serialVersionUID = 3205717181444495016L;

    @ApiModelProperty(name = "brandId", notes = "品牌id")
    private Long brandId;

    @ApiModelProperty(name = "brandName", notes = "品牌名")
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
