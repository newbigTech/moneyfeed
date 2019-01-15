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
public class ProductQueryReq extends PageReq implements Serializable {

    private static final long serialVersionUID = 6625290012915237266L;

    @ApiModelProperty(name = "productName", notes = "商品名称")
    private String productName;

    @ApiModelProperty(name = "saleCateId", notes = "目录id")
    private String saleCateId;

    @ApiModelProperty(name = "saleCateLevel", notes = "目录层级")
    private Integer saleCateLevel;

    @ApiModelProperty(name = "productSkuIds", notes = "商品sku Id")
    private List<Long> productSkuIds;

    @ApiModelProperty(name = "shopId", notes = "店铺 Id")
    private Long shopId;

    @ApiModelProperty(name = "brandId", notes = "品牌ids")
    private List<Long>  brandIds;

    public List<Long> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<Long> brandIds) {
        this.brandIds = brandIds;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<Long> getProductSkuIds() {
        return productSkuIds;
    }

    public void setProductSkuIds(List<Long> productSkuIds) {
        this.productSkuIds = productSkuIds;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

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
