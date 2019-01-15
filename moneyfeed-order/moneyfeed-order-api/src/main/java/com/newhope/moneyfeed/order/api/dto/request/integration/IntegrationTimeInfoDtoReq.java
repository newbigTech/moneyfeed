package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: IntegrationTimeInfoDtoReq  
* @Description: 接收中台时间变更事件及带入的内容修改订单信息
* @author luoxl
* @date 2018年11月20日 下午2:42:33  
*    
*/
public class IntegrationTimeInfoDtoReq implements Serializable {

	private static final long serialVersionUID = 5378844324791765534L;

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
    private List<OrderMaterialInfoDtoResult> materials;

	public String getCompanyShortCode() {
		return companyShortCode;
	}

	public String getCompanyOrgId() {
		return companyOrgId;
	}

	public String getMoneyfeedOrderId() {
		return moneyfeedOrderId;
	}

	public String getEbsOrderNo() {
		return ebsOrderNo;
	}

	public String getEbsOrderStatus() {
		return ebsOrderStatus;
	}

	public Date getTransportTime() {
		return transportTime;
	}

	public String getTransportTimeStr() {
		return transportTimeStr;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public String getCloseTimeStr() {
		return closeTimeStr;
	}

	public Date getCheckinTime() {
		return checkinTime;
	}

	public String getCheckinTimeStr() {
		return checkinTimeStr;
	}

	public Date getCancleTime() {
		return cancleTime;
	}

	public String getCancleTimeStr() {
		return cancleTimeStr;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public String getDealTimeStr() {
		return dealTimeStr;
	}

	public String getCarNo() {
		return carNo;
	}

	public BigDecimal getCarInWeight() {
		return carInWeight;
	}

	public BigDecimal getCarOutWeight() {
		return carOutWeight;
	}

	public Date getPlanTransportTime() {
		return planTransportTime;
	}

	public String getPlanTransportTimeStr() {
		return planTransportTimeStr;
	}

	public String getWeightNo() {
		return weightNo;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public Boolean getMaterialChanged() {
		return materialChanged;
	}

	public void setCompanyShortCode(String companyShortCode) {
		this.companyShortCode = companyShortCode;
	}

	public void setCompanyOrgId(String companyOrgId) {
		this.companyOrgId = companyOrgId;
	}

	public void setMoneyfeedOrderId(String moneyfeedOrderId) {
		this.moneyfeedOrderId = moneyfeedOrderId;
	}

	public void setEbsOrderNo(String ebsOrderNo) {
		this.ebsOrderNo = ebsOrderNo;
	}

	public void setEbsOrderStatus(String ebsOrderStatus) {
		this.ebsOrderStatus = ebsOrderStatus;
	}

	public void setTransportTime(Date transportTime) {
		this.transportTime = transportTime;
	}

	public void setTransportTimeStr(String transportTimeStr) {
		this.transportTimeStr = transportTimeStr;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public void setCloseTimeStr(String closeTimeStr) {
		this.closeTimeStr = closeTimeStr;
	}

	public void setCheckinTime(Date checkinTime) {
		this.checkinTime = checkinTime;
	}

	public void setCheckinTimeStr(String checkinTimeStr) {
		this.checkinTimeStr = checkinTimeStr;
	}

	public void setCancleTime(Date cancleTime) {
		this.cancleTime = cancleTime;
	}

	public void setCancleTimeStr(String cancleTimeStr) {
		this.cancleTimeStr = cancleTimeStr;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public void setDealTimeStr(String dealTimeStr) {
		this.dealTimeStr = dealTimeStr;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public void setCarInWeight(BigDecimal carInWeight) {
		this.carInWeight = carInWeight;
	}

	public void setCarOutWeight(BigDecimal carOutWeight) {
		this.carOutWeight = carOutWeight;
	}

	public void setPlanTransportTime(Date planTransportTime) {
		this.planTransportTime = planTransportTime;
	}

	public void setPlanTransportTimeStr(String planTransportTimeStr) {
		this.planTransportTimeStr = planTransportTimeStr;
	}

	public void setWeightNo(String weightNo) {
		this.weightNo = weightNo;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setMaterialChanged(Boolean materialChanged) {
		this.materialChanged = materialChanged;
	}

	public List<OrderMaterialInfoDtoResult> getMaterials() {
		return materials;
	}

	public void setMaterials(List<OrderMaterialInfoDtoResult> materials) {
		this.materials = materials;
	}

}
