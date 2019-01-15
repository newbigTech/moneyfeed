package com.newhope.moneyfeed.integration.api.bean.ebs.baseData;


import com.newhope.moneyfeed.api.bean.BaseModel;

public class EbsCompanyModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6030771835958654495L;

	/** 公司主键ID */
    private String companyId;

    /** 公司地址 */
    private String companyAddress;

    /** 公司主键ID */
    private String companyName;

    /** 公司简码 */
    private String shortCode;

    /** 状态，normal正常，disable，禁用 */
    private String status;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}