package com.newhope.moneyfeed.order.api.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Create by yyq on 2018/11/17
 */
public class OrderPresentDtoResult implements Serializable {
    private static final long serialVersionUID = -5017332255396978723L;
    @ApiModelProperty(name = "productName", notes = "商品名称")
    private String productName;
    @ApiModelProperty(name = "weightParam", notes = "重量规格")
    private String weightParam;
    @ApiModelProperty(name = "count", notes = "数量")
    private Integer count;
    @ApiModelProperty(name = "total", notes = "总计")
    private Integer total;
    @ApiModelProperty(name = "unit", notes = "单位")
    private String unit;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWeightParam() {
        return weightParam;
    }

    public void setWeightParam(String weightParam) {
        this.weightParam = weightParam;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
