package com.newhope.moneyfeed.integration.api.vo.response.ebs.bill;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillModel;

@XmlRootElement(name = "DATA")
public class EbsCustomerBillListData implements Serializable{
	
	private static final long serialVersionUID = -5211830469152304219L;
	
	private List<EbsCustomerBillModel> ebsDataList;

	@XmlElement(name = "HEADER",type = EbsCustomerBillModel.class)
	public List<EbsCustomerBillModel> getEbsDataList() {
		return ebsDataList;
	}

	public void setEbsDataList(List<EbsCustomerBillModel> ebsDataList) {
		this.ebsDataList = ebsDataList;
	}
	
}
