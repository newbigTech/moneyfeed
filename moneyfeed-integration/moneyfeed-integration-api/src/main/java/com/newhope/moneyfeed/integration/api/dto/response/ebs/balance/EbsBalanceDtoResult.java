package com.newhope.moneyfeed.integration.api.dto.response.ebs.balance;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModelProperty;


public class EbsBalanceDtoResult extends DtoResult implements Serializable{

	private static final long serialVersionUID = -2961065907229589424L;

	@ApiModelProperty(name="dataList", notes="客户余额数据集", required=true)
	private List<EbsBalanceDto> dataList;

	public List<EbsBalanceDto> getDataList() {
		return dataList;
	}

	public void setDataList(List<EbsBalanceDto> dataList) {
		this.dataList = dataList;
	}
	
	
}
