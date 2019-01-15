package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 商城请求EBS取消订单，取消参数
 *
 * Created by yuyanlin on 2018/11/20
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class EbsOrderCancelReq implements Serializable {

    private static final long serialVersionUID = -389426714498620227L;

    @ApiModelProperty(name = "org_id", value = "组织id）")
    private String org_id;

    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;


    @XmlElement(name = "inv:VALUE1")
    public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	@XmlElement(name = "inv:VALUE2")
	public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }
}
