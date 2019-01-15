package com.newhope.moneyfeed.goods.api.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/22 10:24
 */
public class SalecateProductQueryDtoReq implements Serializable {
    private static final long serialVersionUID = 7657742676137865334L;

    @ApiModelProperty(name = "cateId", notes = "目录id")
    private String cateId;

    @ApiModelProperty(name = "productCode", notes = "商品id")
    private String productCode;

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
