package com.newhope.moneyfeed.integration.api.dto.response.ebs.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商城请求EBS创建订单，"订单创建"结果中"产品"的结果
 *
 * Created by yuyanlin on 2018/11/20
 */
public class MoneyfeedOrderCreateProductDtoResult implements Serializable {

    private static final long serialVersionUID = -3648978180323654865L;

    @ApiModelProperty(name = "suppliesCode", value = "EBS物料编码")
    private String suppliesCode;

    @ApiModelProperty(name = "productCount", value = "数量")
    private int productCount;

    @ApiModelProperty(name = "totalOrginalAmount", value = "厂价")
    private BigDecimal totalOrginalAmount;

    @ApiModelProperty(name = "totalDiscountAmount", value = "折扣价")
    private BigDecimal totalDiscountAmount;

    @ApiModelProperty(name = "totalPayAmount", value = "实付金额")
    private BigDecimal totalPayAmount;

    @ApiModelProperty(name = "unit", value = "单位")
    private String unit;

    @ApiModelProperty(name = "organizationCode", value = "库存组织编码")
    private String organizationCode;

    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getTotalOrginalAmount() {
        return totalOrginalAmount;
    }

    public void setTotalOrginalAmount(BigDecimal totalOrginalAmount) {
        this.totalOrginalAmount = totalOrginalAmount;
    }

    public BigDecimal getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
}
