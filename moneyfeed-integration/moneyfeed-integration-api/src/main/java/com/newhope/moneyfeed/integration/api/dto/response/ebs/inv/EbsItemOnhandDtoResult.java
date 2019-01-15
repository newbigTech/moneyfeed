package com.newhope.moneyfeed.integration.api.dto.response.ebs.inv;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModelProperty;

public class EbsItemOnhandDtoResult extends DtoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2319935178202348922L;

	@ApiModelProperty(name="dataList", notes="物料现有量集合", required=true)
	private List<EbsItemOnhandDto> dataList;

	public List<EbsItemOnhandDto> getDataList() {
		return dataList;
	}

	public void setDataList(List<EbsItemOnhandDto> dataList) {
		this.dataList = dataList;
	}
	
}
