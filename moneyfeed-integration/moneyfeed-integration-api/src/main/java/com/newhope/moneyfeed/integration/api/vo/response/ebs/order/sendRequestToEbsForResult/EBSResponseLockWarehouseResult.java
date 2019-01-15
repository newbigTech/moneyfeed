package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HEADER")
public class EBSResponseLockWarehouseResult {

	//公司ID
	private String orgId;
	//订单编号
	private String ebsOrderNo;
	//行ID
	private String lineId;
	//物料编码
	private String itemCode;
	//订单数量
	private String  orderedQuantity; 
	
	@XmlElement(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@XmlElement(name = "EBS_ORDER_NO")
	public String getEbsOrderNo() {
		return ebsOrderNo;
	}
	public void setEbsOrderNo(String ebsOrderNo) {
		this.ebsOrderNo = ebsOrderNo;
	}
	
	@XmlElement(name = "LINE_ID")
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
	@XmlElement(name = "SEGMENT1")
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@XmlElement(name = "ORDERED_QUANTITY")
	public String getOrderedQuantity() {
		return orderedQuantity;
	}
	public void setOrderedQuantity(String orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
		
}
