package com.newhope.openapi.api.vo.response.user;



import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class ShopResult extends Result{
	
	private static final long serialVersionUID = -7971083286156972901L;

	@ApiModelProperty(name = "id", notes = "商户id")
    private Long id;
	
	@ApiModelProperty(name = "name", notes = "商户名称")
    private String name;
    
	@ApiModelProperty(name = "ebsOrgId", notes = "ebs组织主键id")
    private String ebsOrgId;
    
	@ApiModelProperty(name = "intro", notes = "商户简介")
    private String intro;

	@ApiModelProperty(name = "contactPerson", notes = " 联系人 ")
    private String contactPerson;

	@ApiModelProperty(name = "contactTel", notes = " 联系电话")
    private String contactTel;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEbsOrgId() {
		return ebsOrgId;
	}

	public void setEbsOrgId(String ebsOrgId) {
		this.ebsOrgId = ebsOrgId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
}
