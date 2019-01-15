package com.newhope.moneyfeed.pay.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;


public class PayOrderListDtoResult extends DtoResult implements Serializable {
 
	private static final long serialVersionUID = -8242314557083107792L;
	
	private List<PayOrderDtoResult> payOrderDtoResults;

	public List<PayOrderDtoResult> getPayOrderDtoResult() {
		return payOrderDtoResults;
	}

	public void setPayOrderDtoResult(List<PayOrderDtoResult> payOrderDtoResults) {
		this.payOrderDtoResults = payOrderDtoResults;
	}
    
}
