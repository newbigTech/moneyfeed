package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

/**
 *   订单商品快照
 */
public class OrderSnapshotModel extends BaseModel {
	private static final long serialVersionUID = 4096146020314979429L;

	/** 订单号 */
    private String orderNo;

    /** 订单ID */
    private Long orderId;

    /** 商品code */
    private String productCode;

    /** 商品名称 */
    private String productName;

    /** 公司id */
    private Long companyId;

    /** 物料编号 */
    private String suppliesCode;

    /** 物料id */
    private String suppliesId;

    /** 物料描述 */
    private String suppliesDescribe;

    /** 辅助单位 */
    private String secondaryUom;

    /** 主单位 */
    private String primaryUom;

    /** 一级分类id */
    private String oneCateId;

    /** 一级分类描述 */
    private String oneCateDesc;

    /** 二级分类id */
    private String twoCateId;

    /** 二级分类描述 */
    private String twoCateDesc;

    /** 三级分类id */
    private String threeCateId;

    /** 三级分类描述 */
    private String threeCateDesc;

    /** 四级分类id */
    private String fourCateId;

    /** 四级分类描述 */
    private String fourCateDesc;

    /** 目录code */
    private String cateCode;

    /** 商户id */
    private Long shopId;

    /** skuid */
    private Long skuId;

    /** 厂价 */
    private BigDecimal price;

    /** ebs定价 */
    private BigDecimal ebsPrice;

    /** ebs总金额 */
    private BigDecimal ebsTotalAmount;

    /** 数量 */
    private Integer quantity;

    /** 公司名称 */
    private String companyName;

    /** 组织id */
    private String orgId;

    /** 业务实体代码 */
    private String companyShortCode;

    /** 库存组织名称 */
    private String organizationName;

    /** 库存组织代码 */
    private String organizationCode;

    /** sku属性参数json */
    private String skuAttrParams;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }

    public String getSuppliesId() {
        return suppliesId;
    }

    public void setSuppliesId(String suppliesId) {
        this.suppliesId = suppliesId;
    }

    public String getSuppliesDescribe() {
        return suppliesDescribe;
    }

    public void setSuppliesDescribe(String suppliesDescribe) {
        this.suppliesDescribe = suppliesDescribe;
    }

    public String getSecondaryUom() {
        return secondaryUom;
    }

    public void setSecondaryUom(String secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    public String getPrimaryUom() {
        return primaryUom;
    }

    public void setPrimaryUom(String primaryUom) {
        this.primaryUom = primaryUom;
    }

    public String getOneCateId() {
        return oneCateId;
    }

    public void setOneCateId(String oneCateId) {
        this.oneCateId = oneCateId;
    }

    public String getOneCateDesc() {
        return oneCateDesc;
    }

    public void setOneCateDesc(String oneCateDesc) {
        this.oneCateDesc = oneCateDesc;
    }

    public String getTwoCateId() {
        return twoCateId;
    }

    public void setTwoCateId(String twoCateId) {
        this.twoCateId = twoCateId;
    }

    public String getTwoCateDesc() {
        return twoCateDesc;
    }

    public void setTwoCateDesc(String twoCateDesc) {
        this.twoCateDesc = twoCateDesc;
    }

    public String getThreeCateId() {
        return threeCateId;
    }

    public void setThreeCateId(String threeCateId) {
        this.threeCateId = threeCateId;
    }

    public String getThreeCateDesc() {
        return threeCateDesc;
    }

    public void setThreeCateDesc(String threeCateDesc) {
        this.threeCateDesc = threeCateDesc;
    }

    public String getFourCateId() {
        return fourCateId;
    }

    public void setFourCateId(String fourCateId) {
        this.fourCateId = fourCateId;
    }

    public String getFourCateDesc() {
        return fourCateDesc;
    }

    public void setFourCateDesc(String fourCateDesc) {
        this.fourCateDesc = fourCateDesc;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getEbsPrice() {
        return ebsPrice;
    }

    public void setEbsPrice(BigDecimal ebsPrice) {
        this.ebsPrice = ebsPrice;
    }

    public BigDecimal getEbsTotalAmount() {
        return ebsTotalAmount;
    }

    public void setEbsTotalAmount(BigDecimal ebsTotalAmount) {
        this.ebsTotalAmount = ebsTotalAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getSkuAttrParams() {
        return skuAttrParams;
    }

    public void setSkuAttrParams(String skuAttrParams) {
        this.skuAttrParams = skuAttrParams;
    }
}