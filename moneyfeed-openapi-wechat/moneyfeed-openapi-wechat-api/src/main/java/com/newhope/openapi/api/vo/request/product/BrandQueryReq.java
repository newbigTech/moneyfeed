package com.newhope.openapi.api.vo.request.product;

import com.newhope.moneyfeed.api.vo.request.PageReq;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/11/19 18:47
 */
public class BrandQueryReq extends PageReq implements Serializable {


    private static final long serialVersionUID = 227259082653784904L;
    @ApiModelProperty(name = "saleCateId", notes = "目录id")
    private String saleCateId;

    @ApiModelProperty(name = "saleCateLevel", notes = "目录层级")
    private Integer saleCateLevel;

    public String getSaleCateId() {
        return saleCateId;
    }

    public void setSaleCateId(String saleCateId) {
        this.saleCateId = saleCateId;
    }

    public Integer getSaleCateLevel() {
        return saleCateLevel;
    }

    public void setSaleCateLevel(Integer saleCateLevel) {
        this.saleCateLevel = saleCateLevel;
    }
}
