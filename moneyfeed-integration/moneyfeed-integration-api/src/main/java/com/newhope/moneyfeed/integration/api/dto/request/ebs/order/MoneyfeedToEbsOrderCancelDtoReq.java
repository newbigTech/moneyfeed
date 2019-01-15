package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 商城请求EBS取消订单，取消参数
 *
 * Created by yuyanlin on 2018/11/20
 */
public class MoneyfeedToEbsOrderCancelDtoReq implements Serializable {

    private static final long serialVersionUID = -389426714498620227L;

    @NotBlank(message = "orgId为空")
    @ApiModelProperty(name = "org_id", value = "组织id）")
    private String org_id;
    
    @NotBlank(message = "EBS订单编码为空")
    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;


    public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }
}
