package com.newhope.openapi.api.vo.response.user;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class BmUserResult extends Result implements Serializable {
	

	private static final long serialVersionUID = -8438128115040575859L;

	@ApiModelProperty("userid")
    private Long id;

    @ApiModelProperty("用户姓名")
    private String name;
    
    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("商户id")
    private Long shopId;
    
    @ApiModelProperty("商户名称")
    private String shopName;

	@ApiModelProperty("商户状态")
	private Boolean status;

    @ApiModelProperty("角色信息")
    private List<BmRoleResult> roleList;
    
    @ApiModelProperty("商户对应公司Id")
    private String ebsOrgId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getMobile() {
		return mobile;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public List<BmRoleResult> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<BmRoleResult> roleList) {
		this.roleList = roleList;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getEbsOrgId() {
		return ebsOrgId;
	}

	public void setEbsOrgId(String ebsOrgId) {
		this.ebsOrgId = ebsOrgId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
