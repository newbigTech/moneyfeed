package com.newhope.moneyfeed.integration.api.dto.response.ebs.bill;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillModel;

import io.swagger.annotations.ApiModelProperty;

public class EbsCustomerBillDtoResult extends DtoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3555502126641884422L;
	
	@ApiModelProperty(name="billModelList", value="对账单汇总数据集", required=true)
	private List<EbsCustomerBillModel> dataList;

	public List<EbsCustomerBillModel> getDataList() {
		return dataList;
	}

	public void setDataList(List<EbsCustomerBillModel> dataList) {
		this.dataList = dataList;
	}
	
	
}
