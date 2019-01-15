package com.newhope.openapi.api.vo.request.customer;

import com.newhope.moneyfeed.api.vo.request.PageReq;

import io.swagger.annotations.ApiModelProperty;

public class IntentionCustomerQueryReq extends PageReq {
	/**
	 * 
	 */
	private static final long serialVersionUID = -889566215448769129L;

	@ApiModelProperty(notes = "店铺Id，平台登陆时传")
	private Long shopId;
	
	@ApiModelProperty("姓名")
	private String name;
	
	@ApiModelProperty("手机号")
	private String mobile;
	
	@ApiModelProperty("开始时间")
	private String beginDatetime;
    
	@ApiModelProperty("结束时间")
    private String endDatetime;
    
	@ApiModelProperty("分配商户名称")
    private String allotShopName;
    
    @ApiModelProperty("意向商户名称")
    private String intentionShopName;
    
    @ApiModelProperty("状态：ALLOTTING-待分配；ALLOTTED-已分配")
    private String status;
    
    @ApiModelProperty("商户登陆时传这个参数")
    private Long allotShopId;
    
	public String getAllotShopName() {
		return allotShopName;
	}

	public String getIntentionShopName() {
		return intentionShopName;
	}

	public String getStatus() {
		return status;
	}

	public void setAllotShopName(String allotShopName) {
		this.allotShopName = allotShopName;
	}

	public void setIntentionShopName(String intentionShopName) {
		this.intentionShopName = intentionShopName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getShopId() {
		return shopId;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getBeginDatetime() {
		return beginDatetime;
	}

	public String getEndDatetime() {
		return endDatetime;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setBeginDatetime(String beginDatetime) {
		this.beginDatetime = beginDatetime;
	}

	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Long getAllotShopId() {
		return allotShopId;
	}

	public void setAllotShopId(Long allotShopId) {
		this.allotShopId = allotShopId;
	}
	

}
