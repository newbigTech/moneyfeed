package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-客户表
 */
public class UcCustomerModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6978210783270844259L;

	/** 客户编号,自动生成 */
    private String code;

    /** 客户名称 */
    private String name;

    /** 客户简称 */
    private String shortName;

    /** 备注 */
    private String comment;

    /** 个人/企业 */
    private String type;

    /** 联系人 */
    private String contactPerson;

    /** 联系电话 */
    private String contactTel;

    /** 身份证/税号 */
    private String cardNum;

    /** 是否可用 */
    private Boolean enable;

    /** ebs客户主键标识 */
    private String ebsCustomerNum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getEbsCustomerNum() {
        return ebsCustomerNum;
    }

    public void setEbsCustomerNum(String ebsCustomerNum) {
        this.ebsCustomerNum = ebsCustomerNum;
    }
}