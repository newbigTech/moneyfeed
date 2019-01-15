package com.newhope.openapi.api.vo.response.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class SaleCategoryListResult  extends Result implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name = "saleCategoryResultList", notes = "销售目录集合")
    private List<SaleCategoryResult> saleCategoryResultList = new ArrayList<>();

    public List<SaleCategoryResult> getSaleCategoryResultList() {
        return saleCategoryResultList;
    }

    public void setSaleCategoryResultList(List<SaleCategoryResult> saleCategoryResultList) {
        this.saleCategoryResultList = saleCategoryResultList;
    }
}
