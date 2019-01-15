package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDto;

@XmlRootElement(name = "DATA")
public class EBSResponseLockWarehouseResultList {

	private List<EBSResponseLockWarehouseResult> dataList;

	@XmlElement(name = "HEADER",type = EBSResponseLockWarehouseResult.class)
	public List<EBSResponseLockWarehouseResult> getDataList() {
		return dataList;
	}

	public void setDataList(List<EBSResponseLockWarehouseResult> dataList) {
		this.dataList = dataList;
	}
	
	
}
