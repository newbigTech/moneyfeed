package com.newhope.moneyfeed.goods.api.bean;

/**
 *   组织用户折扣(政策)数据
 */
public class CustomerMaterielRelationModel extends BaseModel {
    private String dataStatus;

    private String orgId;

    /** 库存组织编号 */
    private String organizationCode;

    /** 物料编号 */
    private String materielNo;

    /** 客户编号 */
    private String customerNo;

    /** 有效性 */
    private Boolean effectiveness;

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getMaterielNo() {
        return materielNo;
    }

    public void setMaterielNo(String materielNo) {
        this.materielNo = materielNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Boolean getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(Boolean effectiveness) {
        this.effectiveness = effectiveness;
    }
}