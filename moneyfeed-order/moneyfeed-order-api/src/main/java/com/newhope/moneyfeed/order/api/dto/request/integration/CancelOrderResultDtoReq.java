package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: CancelOrderResultDtoReq  
* @Description: integration调用商城更新“取消订单”结果
* @author luoxl
* @date 2018年11月20日 下午6:42:58  
*    
*/
public class CancelOrderResultDtoReq implements Serializable {

	private static final long serialVersionUID = -1966819663978816000L;

	@ApiModelProperty(name = "cancelFlag", notes = "是否取消成功")
	private boolean cancelFlag;
	
	@ApiModelProperty(name = "ebsOrderNo", notes = "EBS订单编码")
	@NotBlank(message = "ebsOrderNo不能为空")
	private String ebsOrderNo;
	
	@ApiModelProperty(name = "companyShortCode", notes = "公司编码（EBS编码）")
	private String companyShortCode;
	
	@ApiModelProperty(name = "msg", notes = "失败原因")
	private String msg;

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public String getEbsOrderNo() {
		return ebsOrderNo;
	}

	public String getCompanyShortCode() {
		return companyShortCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public void setEbsOrderNo(String ebsOrderNo) {
		this.ebsOrderNo = ebsOrderNo;
	}

	public void setCompanyShortCode(String companyShortCode) {
		this.companyShortCode = companyShortCode;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
