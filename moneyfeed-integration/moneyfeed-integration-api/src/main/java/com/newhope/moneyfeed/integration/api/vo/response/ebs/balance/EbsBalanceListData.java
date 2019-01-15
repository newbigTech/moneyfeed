package com.newhope.moneyfeed.integration.api.vo.response.ebs.balance;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDto;

@XmlRootElement(name = "DATA")
public class EbsBalanceListData {

	private List<EbsBalanceDto> ebsDataList;

	@XmlElement(name = "HEADER",type = EbsBalanceDto.class)
	public List<EbsBalanceDto> getEbsDataList() {
		return ebsDataList;
	}

	public void setEbsDataList(List<EbsBalanceDto> ebsDataList) {
		this.ebsDataList = ebsDataList;
	}
	
}
