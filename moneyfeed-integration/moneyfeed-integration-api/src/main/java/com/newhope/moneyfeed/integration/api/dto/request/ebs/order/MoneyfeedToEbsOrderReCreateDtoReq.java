package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liuyc on 2018/12/28
 */
public class MoneyfeedToEbsOrderReCreateDtoReq extends MoneyfeedToEbsOrderCreateDtoReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "ebsOrderNo为空")
    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;

	public String getEbsOrderNo() {
		return ebsOrderNo;
	}

	public void setEbsOrderNo(String ebsOrderNo) {
		this.ebsOrderNo = ebsOrderNo;
	}
    
    
}
