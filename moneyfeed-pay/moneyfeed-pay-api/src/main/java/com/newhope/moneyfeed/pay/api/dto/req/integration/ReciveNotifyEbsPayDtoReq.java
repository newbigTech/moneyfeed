package com.newhope.moneyfeed.pay.api.dto.req.integration;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: ReciveNotifyEbsPayDtoReq  
* @Description: EBS返回的处理充值和支付结果  
* @author luoxl
* @date 2018年12月20日 下午2:07:21  
*    
*/
public class ReciveNotifyEbsPayDtoReq implements Serializable {  
	
 
	private static final long serialVersionUID = 3520296482068651535L;
	
	@ApiModelProperty(name = "ebsorderNo", notes = "EBS订单编码")
	private String ebsorderNo;
	@ApiModelProperty(name = "payFlag", notes = "支付处理是否成功	")
	private Boolean payFlag;
	@ApiModelProperty(name = "msg", notes = "失败原因")
	private String msg;
	@ApiModelProperty(name = "payType", notes = "支付方式（余额支付/银行卡支付/订单充值/账户充值")
	private String payType;
	@ApiModelProperty(name = "orgId", notes = "客户所属机构ID")
	private String orgId;
	@ApiModelProperty(name = "moneyfeedPayNo", notes = "商城支付订单号")
	private String moneyfeedPayNo;
	public String getEbsorderNo() {
		return ebsorderNo;
	}
	public Boolean getPayFlag() {
		return payFlag;
	}
	public String getMsg() {
		return msg;
	}
	public String getPayType() {
		return payType;
	}
	public String getOrgId() {
		return orgId;
	}
	public String getMoneyfeedPayNo() {
		return moneyfeedPayNo;
	}
	public void setEbsorderNo(String ebsorderNo) {
		this.ebsorderNo = ebsorderNo;
	}
	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public void setMoneyfeedPayNo(String moneyfeedPayNo) {
		this.moneyfeedPayNo = moneyfeedPayNo;
	}
	
	
}
