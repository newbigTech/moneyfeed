package com.newhope.moneyfeed.common.vo.ebs;

import javax.xml.bind.annotation.XmlElement;

/**
 * 物料实体类
 * Created by liming on 2018/7/3.
 */
public class EBSProduct {


    /**
     * 所属公司
     */
    private String company;


    /**
     * 公司code
     */
    private String companyCode;


    /**
     * 所属库存
     */
    private String warehouse;


    /**
     * 库存组织
     */
    private String warehouseCode;

    /**
     * 商品num
     */
    private String productNum;

    /**
     * 商品ID
     */
    private String productId;


    /**
     * 商品描述
     */
    private String productName;


    /**
     * 品名代码
     */
    private String pmCode;


    /**
     * 品名描述
     */
    private String pmDesc;


    /**
     * 实物分类第1级代码
     */
    private String oneCatId;


    /**
     * 实物分类第1级说明
     */
    private String oneCatDesc;


    /**
     * 实物分类第2级代码
     */
    private String twoCatId;


    /**
     * 实物分类第2级说明
     */
    private String twoCatDesc;


    /**
     * 实物分类第3级代码
     */
    private String threeCatId;

    /**
     * 实物分类第3级说明
     */
    private String threeCatDesc;

    /**
     * 实物分类第4级代码
     */
    private String fourCatId;

    /**
     * 实物分类第4级说明
     */
    private String fourCatDesc;

    /**
     * 状态
     */
    private String status;


    /**
     * 品牌代码
     */
    private String ppCode;


    /**
     * 品牌描述
     */
    private String ppDesc;

    /**
     * 养殖品种代码
     */
    private String breedCode;


    /**
     * 养殖品种描述
     */
    private String breedDesc;

    @XmlElement(name = "NAME")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @XmlElement(name = "SHORT_CODE")
    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @XmlElement(name = "ORGANIZATION_NAME")
    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    @XmlElement(name = "ORGANIZATION_CODE")
    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    @XmlElement(name = "ITEM_NUMBER")
    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    @XmlElement(name = "ITEM_ID")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @XmlElement(name = "ITEM_DESC")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @XmlElement(name = "PM_CODE")
    public String getPmCode() {
        return pmCode;
    }

    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }


    @XmlElement(name = "PM_DES")
    public String getPmDesc() {
        return pmDesc;
    }

    public void setPmDesc(String pmDesc) {
        this.pmDesc = pmDesc;
    }


    @XmlElement(name = "ONE_CAT")
    public String getOneCatId() {
        return oneCatId;
    }


    public void setOneCatId(String oneCatId) {
        this.oneCatId = oneCatId;
    }


    @XmlElement(name = "ONE_CAT_DES")
    public String getOneCatDesc() {
        return oneCatDesc;
    }

    public void setOneCatDesc(String oneCatDesc) {
        this.oneCatDesc = oneCatDesc;
    }

    @XmlElement(name = "TWO_CAT")
    public String getTwoCatId() {
        return twoCatId;
    }

    public void setTwoCatId(String twoCatId) {
        this.twoCatId = twoCatId;
    }

    @XmlElement(name = "TWO_CAT_DES")
    public String getTwoCatDesc() {
        return twoCatDesc;
    }

    public void setTwoCatDesc(String twoCatDesc) {
        this.twoCatDesc = twoCatDesc;
    }


    @XmlElement(name = "THREE_CAT")
    public String getThreeCatId() {
        return threeCatId;
    }

    public void setThreeCatId(String threeCatId) {
        this.threeCatId = threeCatId;
    }


    @XmlElement(name = "THREE_CAT_DES")
    public String getThreeCatDesc() {
        return threeCatDesc;
    }

    public void setThreeCatDesc(String threeCatDesc) {
        this.threeCatDesc = threeCatDesc;
    }


    @XmlElement(name = "FOUR_CAT")
    public String getFourCatId() {
        return fourCatId;
    }

    public void setFourCatId(String fourCatId) {
        this.fourCatId = fourCatId;
    }

    @XmlElement(name = "FOUR_CAT_DES")
    public String getFourCatDesc() {
        return fourCatDesc;
    }

    public void setFourCatDesc(String fourCatDesc) {
        this.fourCatDesc = fourCatDesc;
    }


    @XmlElement(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @XmlElement(name = "PP_CODE")
    public String getPpCode() {
        return ppCode;
    }

    public void setPpCode(String ppCode) {
        this.ppCode = ppCode;
    }

    @XmlElement(name = "PP_DES")
    public String getPpDesc() {
        return ppDesc;
    }

    public void setPpDesc(String ppDesc) {
        this.ppDesc = ppDesc;
    }


    @XmlElement(name = "BREED_CODE")
    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    @XmlElement(name = "BREED_DES")
    public String getBreedDesc() {
        return breedDesc;
    }

    public void setBreedDesc(String breedDesc) {
        this.breedDesc = breedDesc;
    }


    @Override
    public String toString() {
        return "EBSProduct{" +
                "company='" + company + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", productNum='" + productNum + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", pmCode='" + pmCode + '\'' +
                ", pmDesc='" + pmDesc + '\'' +
                ", oneCatId='" + oneCatId + '\'' +
                ", oneCatDesc='" + oneCatDesc + '\'' +
                ", twoCatId='" + twoCatId + '\'' +
                ", twoCatDesc='" + twoCatDesc + '\'' +
                ", threeCatId='" + threeCatId + '\'' +
                ", threeCatDesc='" + threeCatDesc + '\'' +
                ", fourCatId='" + fourCatId + '\'' +
                ", fourCatDesc='" + fourCatDesc + '\'' +
                ", status='" + status + '\'' +
                ", ppCode='" + ppCode + '\'' +
                ", ppDesc='" + ppDesc + '\'' +
                ", breedCode='" + breedCode + '\'' +
                ", breedDesc='" + breedDesc + '\'' +
                '}';
    }
}