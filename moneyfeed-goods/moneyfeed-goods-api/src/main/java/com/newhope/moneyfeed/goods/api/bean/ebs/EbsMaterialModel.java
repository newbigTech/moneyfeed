package com.newhope.moneyfeed.goods.api.bean.ebs;

import com.newhope.moneyfeed.goods.api.bean.BaseModel;

import java.math.BigDecimal;

public class EbsMaterialModel extends BaseModel {
    private String companyName;

    private String orgId;

    /** 公司简码 */
    private String companyShortCode;

    /** 库存组织名称 */
    private String organizationName;

    /** 库存组织简码 */
    private String organizationCode;

    /** 物料编码 */
    private String itemNumber;

    private String itemId;

    private String itemDesc;

    /** 规格 */
    private String guige;

    /** 辅助单位 */
    private String secondaryUom;

    private BigDecimal calculate;

    /** 主单位 */
    private String primaryUom;

    /** 品牌code */
    private String ppCode;

    /** 品牌描述 */
    private String ppDes;

    /** 养殖品种代码 */
    private String breedCode;

    /** 养殖品种描述 */
    private String breedDes;

    /** 品名代码 */
    private String pmCode;

    /** 品名描述 */
    private String pmDes;

    /** 一级分类 */
    private String oneCat;

    private String oneCatDes;

    private String twoCat;

    private String twoCatDes;

    private String threeCat;

    private String threeCatDes;

    private String fourCat;

    private String fourCatDes;

    /** 物料状态  Active Inactive OPM */
    private String status;

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

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getSecondaryUom() {
        return secondaryUom;
    }

    public void setSecondaryUom(String secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    public BigDecimal getCalculate() {
        return calculate;
    }

    public void setCalculate(BigDecimal calculate) {
        this.calculate = calculate;
    }

    public String getPrimaryUom() {
        return primaryUom;
    }

    public void setPrimaryUom(String primaryUom) {
        this.primaryUom = primaryUom;
    }

    public String getPpCode() {
        return ppCode;
    }

    public void setPpCode(String ppCode) {
        this.ppCode = ppCode;
    }

    public String getPpDes() {
        return ppDes;
    }

    public void setPpDes(String ppDes) {
        this.ppDes = ppDes;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getBreedDes() {
        return breedDes;
    }

    public void setBreedDes(String breedDes) {
        this.breedDes = breedDes;
    }

    public String getPmCode() {
        return pmCode;
    }

    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }

    public String getPmDes() {
        return pmDes;
    }

    public void setPmDes(String pmDes) {
        this.pmDes = pmDes;
    }

    public String getOneCat() {
        return oneCat;
    }

    public void setOneCat(String oneCat) {
        this.oneCat = oneCat;
    }

    public String getOneCatDes() {
        return oneCatDes;
    }

    public void setOneCatDes(String oneCatDes) {
        this.oneCatDes = oneCatDes;
    }

    public String getTwoCat() {
        return twoCat;
    }

    public void setTwoCat(String twoCat) {
        this.twoCat = twoCat;
    }

    public String getTwoCatDes() {
        return twoCatDes;
    }

    public void setTwoCatDes(String twoCatDes) {
        this.twoCatDes = twoCatDes;
    }

    public String getThreeCat() {
        return threeCat;
    }

    public void setThreeCat(String threeCat) {
        this.threeCat = threeCat;
    }

    public String getThreeCatDes() {
        return threeCatDes;
    }

    public void setThreeCatDes(String threeCatDes) {
        this.threeCatDes = threeCatDes;
    }

    public String getFourCat() {
        return fourCat;
    }

    public void setFourCat(String fourCat) {
        this.fourCat = fourCat;
    }

    public String getFourCatDes() {
        return fourCatDes;
    }

    public void setFourCatDes(String fourCatDes) {
        this.fourCatDes = fourCatDes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}