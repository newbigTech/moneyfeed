package com.newhope.moneyfeed.integration.api.dto.response.ebs.order;

import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ebs订单明细
 * Created by Dave Chen on 2018/11/22.
 */
@XmlRootElement(name = "HERDER")
public class QueryEbsOrderDetailInfoItemDtoResult {

    @ApiModelProperty(name = "companyShortCode", value = "公司编码（EBS编码）")
    private String companyShortCode;

    @ApiModelProperty(name = "companyOrgId", value = "公司ID（EBSID）")
    private String companyOrgId;

    @ApiModelProperty(name = "moneyfeedOrderId", value = "商城订单ID")
    private  String moneyfeedOrderId;

    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编号")
    private  String ebsOrderNo;


    @ApiModelProperty(name = "ebsOrderStatus", value = "EBS订单状态")
    private  String ebsOrderStatus;

    @ApiModelProperty(name = "transportTime", value = "进厂时间")
    private Date transportTime;

    private  String transportTimeStr;

    @ApiModelProperty(name = "closeTime", value = "订单关闭时间")
    private Date closeTime;

    private String closeTimeStr;


    @ApiModelProperty(name = "checkinTime", value = "订单开票时间")
    private Date checkinTime;

    private String checkinTimeStr;

    @ApiModelProperty(name = "cancleTime", value = "EBS取消时间")
    private Date cancleTime;

    private String cancleTimeStr;

    @ApiModelProperty(name = "dealTime", value = "出厂时间")
    private Date dealTime;

    private String dealTimeStr;

    @ApiModelProperty(name = "carNo", value = "EBS车牌号")
    private String carNo;

    @ApiModelProperty(name = "carInWeight", value = "进厂车辆重量")
    private BigDecimal carInWeight;

    @ApiModelProperty(name = "carOutWeight", value = "出厂车辆重量")
    private BigDecimal carOutWeight;

    @ApiModelProperty(name = "planTransportTime", value = "计划拉料日期")
    private Date planTransportTime;

    private  String planTransportTimeStr;

    @ApiModelProperty(name = "weightNo", value = "磅单编号")
    private String weightNo;

    @ApiModelProperty(name = "lastUpdateTime", value = "最后更新时间")
    private  Date lastUpdateTime;

    @ApiModelProperty(name = "materialChanged", value = "物料是否发生过变更")
    private Boolean  materialChanged;

    @ApiModelProperty(name = "materials", value = "物料清单")
    private List<QueryEbsOrderMaterialInfoDtoResult> materials;


    @XmlElement(name = "COMPANY_SHORT_CODE")
    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    @XmlElement(name = "MONEYFEED_ORDER_ID")
    public String getMoneyfeedOrderId() {
        return moneyfeedOrderId;
    }

    public void setMoneyfeedOrderId(String moneyfeedOrderId) {
        this.moneyfeedOrderId = moneyfeedOrderId;
    }

    @XmlElement(name = "ORDER_STATUS")
    public String getEbsOrderStatus() {
        return ebsOrderStatus;
    }

    public void setEbsOrderStatus(String ebsOrderStatus) {
        this.ebsOrderStatus = ebsOrderStatus;
    }


    public Date getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(Date transportTime) {
        this.transportTime = transportTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }


    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }


    public Date getCancleTime() {
        return cancleTime;
    }

    public void setCancleTime(Date cancleTime) {
        this.cancleTime = cancleTime;
    }


    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    @XmlElement(name = "CAR_NO")
    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @XmlElement(name = "CAR_IN_WEIGHT")
    public BigDecimal getCarInWeight() {
        return carInWeight;
    }

    public void setCarInWeight(BigDecimal carInWeight) {
        this.carInWeight = carInWeight;
    }

    @XmlElement(name = "CAR_OUT_WEIGHT")
    public BigDecimal getCarOutWeight() {
        return carOutWeight;
    }

    public void setCarOutWeight(BigDecimal carOutWeight) {
        this.carOutWeight = carOutWeight;
    }


    public Date getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(Date planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    public String getCompanyOrgId() {
        return companyOrgId;
    }

    public void setCompanyOrgId(String companyOrgId) {
        this.companyOrgId = companyOrgId;
    }

    @XmlElement(name = "ORDER_NUMBER")
    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    @XmlElement(name = "WEIGHT_NUM")
    public String getWeightNo() {
        return weightNo;
    }

    public void setWeightNo(String weightNo) {
        this.weightNo = weightNo;
    }

    @XmlElement(name = "LINE",type = QueryEbsOrderMaterialInfoDtoResult.class)
    public List<QueryEbsOrderMaterialInfoDtoResult> getMaterials() {
        return materials;
    }

    public void setMaterials(List<QueryEbsOrderMaterialInfoDtoResult> materials) {
        this.materials = materials;
    }

    @XmlElement(name = "CAR_ENTER_TIME")
    public String getTransportTimeStr() {
        return transportTimeStr;
    }

    public void setTransportTimeStr(String transportTimeStr) {
        this.transportTimeStr = transportTimeStr;
    }

    @XmlElement(name = "CLOSE_TIME")
    public String getCloseTimeStr() {
        return closeTimeStr;
    }

    public void setCloseTimeStr(String closeTimeStr) {
        this.closeTimeStr = closeTimeStr;
    }

    @XmlElement(name = "CHECK_TIME")
    public String getCheckinTimeStr() {
        return checkinTimeStr;
    }


    public void setCheckinTimeStr(String checkinTimeStr) {
        this.checkinTimeStr = checkinTimeStr;
    }

    @XmlElement(name = "CANCLE_TIME")
    public String getCancleTimeStr() {
        return cancleTimeStr;
    }

    public void setCancleTimeStr(String cancleTimeStr) {
        this.cancleTimeStr = cancleTimeStr;
    }

    @XmlElement(name = "OUT_TIME")
    public String getDealTimeStr() {
        return dealTimeStr;
    }

    public void setDealTimeStr(String dealTimeStr) {
        this.dealTimeStr = dealTimeStr;
    }

    @XmlElement(name = "PLAN_DATE")
    public String getPlanTransportTimeStr() {
        return planTransportTimeStr;
    }

    public void setPlanTransportTimeStr(String planTransportTimeStr) {
        this.planTransportTimeStr = planTransportTimeStr;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Boolean getMaterialChanged() {
        return materialChanged;
    }

    public void setMaterialChanged(Boolean materialChanged) {
        this.materialChanged = materialChanged;
    }
}

