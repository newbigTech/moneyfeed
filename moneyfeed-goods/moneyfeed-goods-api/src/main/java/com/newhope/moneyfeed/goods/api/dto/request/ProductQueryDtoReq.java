package com.newhope.moneyfeed.goods.api.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/19 14:01
 */
public class ProductQueryDtoReq extends PageDtoReq implements Serializable {
    private static final long serialVersionUID = 5183323754391104432L;

    @ApiModelProperty(name = "customerId", notes = "客户id")
    private Long customerId;

    @ApiModelProperty(name = "shopId", notes = "店铺id")
    private Long shopId;

    @ApiModelProperty(name = "productName", notes = "商品名称")
    private String productName;

    @ApiModelProperty(name = "saleCateId", notes = "目录id")
    private String saleCateId;

    @ApiModelProperty(name = "saleCateLevel", notes = "目录层级")
    private Integer saleCateLevel;

    @ApiModelProperty(name = "status", notes = "状态")
    private String status;

    @ApiModelProperty(name = "customerNum", notes = "客户编码")
    private String customerNum;

    @ApiModelProperty(name = "verify", notes = "是否校验")
    private boolean verify;

    @ApiModelProperty(name = "productSkuIds", notes = "商品sku Ids")
    private List<Long> productSkuIds;

    @ApiModelProperty(name = "brandId", notes = "品牌ids")
    private List<Long>  brandIds;

    public List<Long> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<Long> brandIds) {
        this.brandIds = brandIds;
    }

    public List<Long> getProductSkuIds() {
        return productSkuIds;
    }

    public void setProductSkuIds(List<Long> productSkuIds) {
        this.productSkuIds = productSkuIds;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
