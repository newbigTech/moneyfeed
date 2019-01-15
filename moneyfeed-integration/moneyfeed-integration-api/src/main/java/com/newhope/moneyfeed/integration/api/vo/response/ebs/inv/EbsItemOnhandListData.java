package com.newhope.moneyfeed.integration.api.vo.response.ebs.inv;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.inv.EbsItemOnhandDto;

@XmlRootElement(name = "DATA")
public class EbsItemOnhandListData {
	private List<EbsItemOnhandDto> ebsDataList;

	@XmlElement(name = "HEADER",type = EbsItemOnhandDto.class)
	public List<EbsItemOnhandDto> getEbsDataList() {
		return ebsDataList;
	}

	public void setEbsDataList(List<EbsItemOnhandDto> ebsDataList) {
		this.ebsDataList = ebsDataList;
	}
}
