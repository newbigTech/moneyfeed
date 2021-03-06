package com.newhope.openapi.api.vo.request.product;


import java.io.Serializable;

public class AdProductQueryReq implements Serializable {

    /** 店铺id */
    private Long shopId;

    /** 店铺名称 */
    private String shopName;

    /** 商品编码 */
    private String productCode;

    /** 物料编码 */
    private String suppliesCode;

    /** 产品名称 */
    private String suppliesDescribe;

    /** 一级目录id */
    private String oneCateId;

    /** 一级目录名称 */
    private String oneCateDesc;

    /** 二级目录id */
    private String twoCateId;

    /** 二级目录名称 */
    private String twoCateDesc;

    /** 三级目录id */
    private String threeCateId;

    /** 三级目录名称 */
    private String threeCateDesc;

    /** 四级目录 */
    private String fourCateId;

    /** 四级目录描述 */
    private String fourCateDesc;

    /** 重量 */
    private String weight;

    /** 使用阶段 */
    private String stage;

    /** 关注点 */
    private String concerns;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }

    public String getSuppliesDescribe() {
        return suppliesDescribe;
    }

    public void setSuppliesDescribe(String suppliesDescribe) {
        this.suppliesDescribe = suppliesDescribe;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getConcerns() {
        return concerns;
    }

    public void setConcerns(String concerns) {
        this.concerns = concerns;
    }
}
