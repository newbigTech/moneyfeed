package com.newhope.moneyfeed.user.api.dto.response.platform;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModelProperty;

/**
 *   用户中心-商户用户表
 */
public class UcPmUserDtoResult extends DtoResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1117545350593656081L;

	private Long id;

	/** 用户姓名 */
    private String name;

    /** 用户手机号 */
    private String mobile;

    /** 商户id */
    private Long shopId;
    
    /** 商户名称 */
    private String shopName;
    
    @ApiModelProperty("角色信息")
    private List<UcPmRoleDtoResult> roleDtoList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	public Long getId() {
		return id;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public List<UcPmRoleDtoResult> getRoleDtoList() {
		return roleDtoList;
	}

	public void setRoleDtoList(List<UcPmRoleDtoResult> roleDtoList) {
		this.roleDtoList = roleDtoList;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}