package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs;

import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by yuyanlin on 2018/11/30
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class EbsOrderUpdateReq implements Serializable {

    private static final long serialVersionUID = -7425539776345672205L;

    @ApiModelProperty(name = "orgId", value = "公司Id(EBS系统中)")
    private String orgId;

    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;

    @ApiModelProperty(name = "carNo", value = "订单中的车牌号")
    private String carNo;

    @ApiModelProperty(name = "planTransportTime", value = "计划拉货日期")
    private String planTransportTime;

    @ApiModelProperty(name = "whetherLock", value = "是否锁库")
    private String whetherLock;

    @XmlElement(name = "inv:VALUE1")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @XmlElement(name = "inv:VALUE2")
    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    @XmlElement(name = "inv:VALUE3")
    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @XmlElement(name = "inv:VALUE4")
    public String getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(String planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    @XmlElement(name = "inv:VALUE5")
    public String getWhetherLock() {
        return whetherLock;
    }

    public void setWhetherLock(String whetherLock) {
        this.whetherLock = whetherLock;
    }
}
