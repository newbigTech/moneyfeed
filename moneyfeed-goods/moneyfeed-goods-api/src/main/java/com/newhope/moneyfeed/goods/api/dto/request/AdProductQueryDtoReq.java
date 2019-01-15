package com.newhope.moneyfeed.goods.api.dto.request;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AdProductQueryDtoReq  implements Serializable {

    /** 店铺id */
    @ApiModelProperty(name = "shopId", notes = "店鋪id")
    private Long shopId;



    /** 一级目录id */
    @ApiModelProperty(name = "oneCateId", notes = "一級分分类id")
    private String oneCateId;


    /** 二级目录id */
    @ApiModelProperty(name = "twoCateId", notes = "二級分分类id")
    private String twoCateId;


    /** 三级目录id */
    @ApiModelProperty(name = "threeCateId", notes = "三級分分类id")
    private String threeCateId;


    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOneCateId() {
        return oneCateId;
    }

    public void setOneCateId(String oneCateId) {
        this.oneCateId = oneCateId;
    }

    public String getTwoCateId() {
        return twoCateId;
    }

    public void setTwoCateId(String twoCateId) {
        this.twoCateId = twoCateId;
    }

    public String getThreeCateId() {
        return threeCateId;
    }

    public void setThreeCateId(String threeCateId) {
        this.threeCateId = threeCateId;
    }
}
