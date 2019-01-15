package com.newhope.moneyfeed.integration.api.dto.response.ebs.bill;

import java.io.Serializable;
import java.util.List;
import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillDetailModel;

import io.swagger.annotations.ApiModelProperty;

public class EbsCustomerBillDetailPageDtoResult extends PageDtoResult implements Serializable{

	private static final long serialVersionUID = -2175987419334446385L;
	
	@ApiModelProperty(name="billModelList", value="对账单明细数据集", required=true)
	private List<EbsCustomerBillDetailModel> dataList;

	public List<EbsCustomerBillDetailModel> getDataList() {
		return dataList;
	}

	public void setDataList(List<EbsCustomerBillDetailModel> dataList) {
		this.dataList = dataList;
	}

}
