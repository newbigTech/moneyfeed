package com.newhope.moneyfeed.user.api.dto.response.platform;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.exbean.platform.UcPmResourceExModel;

public class PmResourceListDtoResult extends DtoResult {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 564334083955967942L;
	
	private List<UcPmResourceExModel> resourceList = new ArrayList<UcPmResourceExModel>();

	public List<UcPmResourceExModel> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<UcPmResourceExModel> resourceList) {
		this.resourceList = resourceList;
	}
	
}
