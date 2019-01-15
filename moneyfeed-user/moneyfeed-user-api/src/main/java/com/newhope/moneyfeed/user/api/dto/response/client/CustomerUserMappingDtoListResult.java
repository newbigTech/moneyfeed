package com.newhope.moneyfeed.user.api.dto.response.client;


import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;



public class CustomerUserMappingDtoListResult extends DtoResult {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4179856517340402472L;
	
	private List<UcCustomerClientUserMappingModel> mappings;

	public List<UcCustomerClientUserMappingModel> getMappings() {
		return mappings;
	}

	public void setMappings(List<UcCustomerClientUserMappingModel> mappings) {
		this.mappings = mappings;
	}

	
}