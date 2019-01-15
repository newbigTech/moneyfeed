package com.newhope.moneyfeed.goods.api.bean;

/**
 *   商品 公司信息
 */
public class CompanyModel extends BaseModel {
    /** 公司名称 */
    private String companyName;

    /** 组织ID */
    private String orgId;

    /** 业务实体代码 */
    private String companyShortCode;

    /** 库存组织名称 */
    private String organizationName;

    /** 库存组织代码 */
    private String organizationCode;

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
}