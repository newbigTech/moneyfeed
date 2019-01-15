package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-ebs客户关系表
 */
public class UcEbsCustomerModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6486668825945294178L;

	/** 公司id（三方系统公司id） */
    private String orgId;

    /** 公司名称 */
    private String orgName;

    /** 客户编码 */
    private String customerNum;

    /** 客户名称 */
    private String customerName;

    /** 客户别名 */
    private String customerAsName;

    /** 纳税登记编号或个人身份证 */
    private String customerCardNum;

    /** 客户类型 */
    private String customerType;

    /** 联系人名称 */
    private String contactName;

    /** 联系人电话 */
    private String contactPhone;

    /** 客户地址 */
    private String customerAddr;

    /** 联系人主键id */
    private String contactId;

    /** 是否有效 */
    private Boolean enable;

    /** 有效手机号，平台登录关联用 */
    private String usefulMobile;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAsName() {
        return customerAsName;
    }

    public void setCustomerAsName(String customerAsName) {
        this.customerAsName = customerAsName;
    }

    public String getCustomerCardNum() {
        return customerCardNum;
    }

    public void setCustomerCardNum(String customerCardNum) {
        this.customerCardNum = customerCardNum;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCustomerAddr() {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getUsefulMobile() {
        return usefulMobile;
    }

    public void setUsefulMobile(String usefulMobile) {
        this.usefulMobile = usefulMobile;
    }
}