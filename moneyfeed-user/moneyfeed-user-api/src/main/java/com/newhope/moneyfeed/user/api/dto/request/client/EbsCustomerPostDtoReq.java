package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class EbsCustomerPostDtoReq implements Serializable {

	private static final long serialVersionUID = -205983904830761997L;

	@ApiModelProperty("公司id（三方系统公司id）")
	private String orgId;

	@ApiModelProperty("公司名称")
	private String orgName;

	@ApiModelProperty("客户编码")
	private String customerNum;

	@ApiModelProperty("客户名称")
	private String customerName;

	@ApiModelProperty("客户别名")
	private String customerAsName;

	@ApiModelProperty("客户类型")
	private String customerType;

	@ApiModelProperty("纳税登记编号或个人身份证")
	private String customerCardNum;

	@ApiModelProperty("联系人名称")
	private String contactName;

	@ApiModelProperty("联系人电话")
	private String contactPhone;

	@ApiModelProperty("客户地址")
	private String customerAddr;

	@ApiModelProperty("联系人主键id")
	private String contactId;

	@ApiModelProperty("是否有效 ")
	private Boolean enable;

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

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

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerCardNum() {
		return customerCardNum;
	}

	public void setCustomerCardNum(String customerCardNum) {
		this.customerCardNum = customerCardNum;
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

}