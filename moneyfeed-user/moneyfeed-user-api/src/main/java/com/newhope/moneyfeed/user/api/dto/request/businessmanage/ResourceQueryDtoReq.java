package com.newhope.moneyfeed.user.api.dto.request.businessmanage;

import java.io.Serializable;
import java.util.List;

public class ResourceQueryDtoReq implements Serializable {
	
	private static final long serialVersionUID = 5883942367296427562L;

	private List<Long> resourceIds;
	
	private String type;
	
	private boolean enable;
	
	private List<Long> roleIds;

	public List<Long> getResourceIds() {
		return resourceIds;
	}

	public String getType() {
		return type;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setResourceIds(List<Long> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

}
