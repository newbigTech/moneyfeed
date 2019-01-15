package com.newhope.moneyfeed.integration.api.vo.response.ebs.bill;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillDetailModel;

@XmlRootElement(name = "DATA")
public class EbsCustomerBillDetailListData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9092028464848933387L;

	
	private List<EbsCustomerBillDetailModel> ebsDataList;

	@XmlElement(name = "HEADER",type = EbsCustomerBillDetailModel.class)
	public List<EbsCustomerBillDetailModel> getEbsDataList() {
		return ebsDataList;
	}
	public void setEbsDataList(List<EbsCustomerBillDetailModel> ebsDataList) {
		this.ebsDataList = ebsDataList;
	}
	
	
}
