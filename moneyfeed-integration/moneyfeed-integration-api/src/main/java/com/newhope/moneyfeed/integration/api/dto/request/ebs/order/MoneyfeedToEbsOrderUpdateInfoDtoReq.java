package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yuyanlin on 2018/11/26
 */
public class MoneyfeedToEbsOrderUpdateInfoDtoReq implements Serializable {

    private static final long serialVersionUID = 6287776628180644249L;

    @NotBlank(message = "orgId不能为空")
    @ApiModelProperty(name = "orgId", value = "EBS公司Id")
    private String orgId;

    @NotBlank(message = "ebsOrderNo不能为空")
    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;

    @ApiModelProperty(name = "carNo", value = "商城车牌号")
    private String carNo;

    @NotBlank(message = "planTransportTime不能为空")
    @ApiModelProperty(name = "planTransportTime", value = "商城计划拉料日期")
    private String planTransportTime;

    private Date planTransportTimeDate;

    @ApiModelProperty(name = "whetherLock", value = "是否锁库")
    private Boolean whetherLock;

    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    public String getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(String planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Date getPlanTransportTimeDate() {
        return planTransportTimeDate;
    }

    public void setPlanTransportTimeDate(Date planTransportTimeDate) {
        this.planTransportTimeDate = planTransportTimeDate;
    }

    public Boolean getWhetherLock() {
        return whetherLock;
    }

    public void setWhetherLock(Boolean whetherLock) {
        this.whetherLock = whetherLock;
    }
}
