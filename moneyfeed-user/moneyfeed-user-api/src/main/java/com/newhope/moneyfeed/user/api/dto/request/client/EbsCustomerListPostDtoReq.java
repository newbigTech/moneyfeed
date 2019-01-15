package com.newhope.moneyfeed.user.api.dto.request.client;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class EbsCustomerListPostDtoReq implements Serializable {
	
	private static final long serialVersionUID = -9152758386395422614L;
	
	@ApiModelProperty("数据集合")
	private List<EbsCustomerPostDtoReq> dataList = new ArrayList<EbsCustomerPostDtoReq>();

	public List<EbsCustomerPostDtoReq> getDataList() {
		return dataList;
	}

	public void setDataList(List<EbsCustomerPostDtoReq> dataList) {
		this.dataList = dataList;
	}
	
}