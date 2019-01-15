package com.newhope.moneyfeed.integration.api.bean.ebs.baseData;

import com.newhope.moneyfeed.api.bean.BaseModel;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;


public class EbsMaterialModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7975185044073310369L;

	private String companyName;

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

    private BigDecimal calculate;

    private String orgId;
    
    //基础价格及是出厂单价
    private BigDecimal basePrice;
    
    //库存组织ID
    private String organizationId;

    /** 物料状态  Active Inactive OPM */
    private String status;
    @XmlElement(name = "NAME")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @XmlElement(name = "SHORT_CODE")
    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    @XmlElement(name = "ORGANIZATION_NAME")
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @XmlElement(name = "ORGANIZATION_CODE")
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @XmlElement(name = "ITEM_NUMBER")
    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    @XmlElement(name = "ITEM_ID")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @XmlElement(name = "ITEM_DESC")
    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    @XmlElement(name = "PP_CODE")
    public String getPpCode() {
        return ppCode;
    }

    public void setPpCode(String ppCode) {
        this.ppCode = ppCode;
    }

    @XmlElement(name = "PP_DES")
    public String getPpDes() {
        return ppDes;
    }

    public void setPpDes(String ppDes) {
        this.ppDes = ppDes;
    }


    @XmlElement(name = "BREED_CODE")
    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    @XmlElement(name = "BREED_DES")
    public String getBreedDes() {
        return breedDes;
    }

    public void setBreedDes(String breedDes) {
        this.breedDes = breedDes;
    }

    @XmlElement(name = "PM_CODE")
    public String getPmCode() {
        return pmCode;
    }

    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }

    @XmlElement(name = "PM_DES")
    public String getPmDes() {
        return pmDes;
    }

    public void setPmDes(String pmDes) {
        this.pmDes = pmDes;
    }

    @XmlElement(name = "ONE_CAT")
    public String getOneCat() {
        return oneCat;
    }

    public void setOneCat(String oneCat) {
        this.oneCat = oneCat;
    }

    @XmlElement(name = "ONE_CAT_DES")
    public String getOneCatDes() {
        return oneCatDes;
    }

    public void setOneCatDes(String oneCatDes) {
        this.oneCatDes = oneCatDes;
    }

    @XmlElement(name = "TWO_CAT")
    public String getTwoCat() {
        return twoCat;
    }

    public void setTwoCat(String twoCat) {
        this.twoCat = twoCat;
    }

    @XmlElement(name = "TWO_CAT_DES")
    public String getTwoCatDes() {
        return twoCatDes;
    }

    public void setTwoCatDes(String twoCatDes) {
        this.twoCatDes = twoCatDes;
    }

    @XmlElement(name = "THREE_CAT")
    public String getThreeCat() {
        return threeCat;
    }

    public void setThreeCat(String threeCat) {
        this.threeCat = threeCat;
    }

    @XmlElement(name = "THREE_CAT_DES")
    public String getThreeCatDes() {
        return threeCatDes;
    }

    public void setThreeCatDes(String threeCatDes) {
        this.threeCatDes = threeCatDes;
    }

    @XmlElement(name = "FOUR_CAT")
    public String getFourCat() {
        return fourCat;
    }

    public void setFourCat(String fourCat) {
        this.fourCat = fourCat;
    }

    @XmlElement(name = "FOUR_CAT_DES")
    public String getFourCatDes() {
        return fourCatDes;
    }

    public void setFourCatDes(String fourCatDes) {
        this.fourCatDes = fourCatDes;
    }

    @XmlElement(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "GUIGE")
    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    @XmlElement(name = "SECONDARY_UOM")
    public String getSecondaryUom() {
        return secondaryUom;
    }

    public void setSecondaryUom(String secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    @XmlElement(name = "PRIMARY_UOM")
    public String getPrimaryUom() {
        return primaryUom;
    }

    public void setPrimaryUom(String primaryUom) {
        this.primaryUom = primaryUom;
    }

    @XmlElement(name = "RATE")
    public BigDecimal getCalculate() {
        return calculate;
    }

    public void setCalculate(BigDecimal calculate) {
        this.calculate = calculate;
    }

    @XmlElement(name = "ORG_ID")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @XmlElement(name = "UNIT_PRICE")
	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	@XmlElement(name = "ORGANIZATION_ID")
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	
}