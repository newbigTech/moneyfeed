package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: TransportInfoToEbsResultDtoReq  
* @Description: integration调用商城更新 拉料信息结果
* @author luoxl
* @date 2018年11月21日 下午3:05:14  
*    
*/
public class TransportInfoToEbsResultDtoReq implements Serializable {
 
	private static final long serialVersionUID = 8872175048053639000L;
	
	@ApiModelProperty(name = "modifyFlag", notes = "是否修改成功")
	private boolean modifyFlag;
	@ApiModelProperty(name = "ebsOrderNo", notes = "EBS订单编码")
	private String ebsOrderNo;
	@ApiModelProperty(name = "msg", notes = "失败原因")
	private String msg;
	public boolean isModifyFlag() {
		return modifyFlag;
	}
	public String getEbsOrderNo() {
		return ebsOrderNo;
	}
	public String getMsg() {
		return msg;
	}
	public void setModifyFlag(boolean modifyFlag) {
		this.modifyFlag = modifyFlag;
	}
	public void setEbsOrderNo(String ebsOrderNo) {
		this.ebsOrderNo = ebsOrderNo;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
