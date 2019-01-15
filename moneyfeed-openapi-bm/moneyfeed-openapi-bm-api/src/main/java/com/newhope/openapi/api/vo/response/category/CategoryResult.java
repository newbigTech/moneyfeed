package com.newhope.openapi.api.vo.response.category;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

public class CategoryResult extends Result {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List categoryDtoResultList = new ArrayList<>();

	public List getCategoryDtoResultList() {
		return categoryDtoResultList;
	}

	public void setCategoryDtoResultList(List categoryDtoResultList) {
		this.categoryDtoResultList = categoryDtoResultList;
	}

}
