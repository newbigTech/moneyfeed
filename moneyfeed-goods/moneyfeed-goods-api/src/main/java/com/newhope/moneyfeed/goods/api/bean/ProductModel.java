package com.newhope.moneyfeed.goods.api.bean;

/**
 *   商品表
 */
public class ProductModel extends BaseModel {
    /** 商品code */
    private String productCode;

    /** 商品名称 */
    private String productName;

    /** 公司id */
    @Deprecated
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

    /** 级分类id */
    private String oneCateId;

    /** 一级分类描述 */
    private String oneCateDesc;

    /** 二级分类ID */
    private String twoCateId;

    /** 二级分类描述 */
    private String twoCateDesc;

    /** 三级分类ID */
    private String threeCateId;

    /** 三级分类描述 */
    private String threeCateDesc;

    /** 四级分类ID */
    private String fourCateId;

    /** 四级分类描述 */
    private String fourCateDesc;

    /** 目录code */
    private String cateCode;

    /** 状态:上架、下架、失效 */
    private String status;

    /** 组织ID */
    private String orgId;

    /** 品牌id */
    private Long brandId;

    /** 品牌名称 */
    private String brandName;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

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