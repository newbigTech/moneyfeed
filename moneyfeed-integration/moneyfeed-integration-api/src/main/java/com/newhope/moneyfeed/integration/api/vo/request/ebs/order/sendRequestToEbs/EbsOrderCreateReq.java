package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs;


import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by yuyanlin on 2018/11/21
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class EbsOrderCreateReq extends EbsOrderCreateProductReq implements Serializable {

    private static final long serialVersionUID = 685697571345887588L;

    @ApiModelProperty(name = "companyShortCode", value = "公司编码（EBS编码）")
    private String companyShortCode;

    @ApiModelProperty(name = "customerNum", value = "客户编码")
    private String customerNum;

    @ApiModelProperty(name = "moneyFeedOrderId", value = "商城订单ID")
    private String moneyFeedOrderId;

    @ApiModelProperty(name = "moneyFeedOrderNo", value = "商城订单code")
    private String moneyFeedOrderNo;

    @ApiModelProperty(name = "channel", value = "线上渠道ID（商城、标记是否来自于商城、默认MONEYFEED）")
    private String channel;

    @ApiModelProperty(name = "planTransportTime", value = "计划拉料日期（YYYY-MM-DD）")
    private String planTransportTime;

    @ApiModelProperty(name = "carNo", value = "车牌号")
    private String carNo;

    @ApiModelProperty(name = "orderType", value = "订单类型")
    private String orderType;

    @XmlElement(name = "inv:VALUE1")
    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    @XmlElement(name = "inv:VALUE6")
    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    @XmlElement(name = "inv:VALUE7")
    public String getMoneyFeedOrderId() {
        return moneyFeedOrderId;
    }

    public void setMoneyFeedOrderId(String moneyFeedOrderId) {
        this.moneyFeedOrderId = moneyFeedOrderId;
    }

    @XmlElement(name = "inv:VALUE8")
    public String getMoneyFeedOrderNo() {
        return moneyFeedOrderNo;
    }

    public void setMoneyFeedOrderNo(String moneyFeedOrderNo) {
        this.moneyFeedOrderNo = moneyFeedOrderNo;
    }

    @XmlElement(name = "inv:VALUE9")
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @XmlElement(name = "inv:VALUE10")
    public String getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(String planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    @XmlElement(name = "inv:VALUE11")
    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @XmlElement(name = "inv:VALUE12")
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }



}
