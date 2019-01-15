package com.newhope.moneyfeed.pay.api.dto.req;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: PayOrderCallbackDtoReq  
* @Description: 处理回调传入的数据
* @author luoxl
* @date 2018年12月25日 上午8:52:22  
*    
*/
public class PayOrderCallbackDtoReq implements Serializable {

	private static final long serialVersionUID = 968534446714937281L;

	@ApiModelProperty(name = "apiName", notes = "接口名字")
	private String apiName;
	@ApiModelProperty(name = "notifyTime", notes = "通知时间")
	private String notifyTime;
	@ApiModelProperty(name = "tradeAmt", notes = "订单支付金额")
	private String tradeAmt;
	@ApiModelProperty(name = "merchNo", notes = "商户号")
	private String merchNo;
	@ApiModelProperty(name = "merchParam", notes = "商户参数")
	private String merchParam;
	@ApiModelProperty(name = "orderNo", notes = "商户支付订单号")
	private String orderNo;
	@ApiModelProperty(name = "tradeDate", notes = "交易日期")
	private String tradeDate;
	@ApiModelProperty(name = "accNo", notes = "支付平台订单号")
	private String accNo;
	@ApiModelProperty(name = "accDate", notes = "支付平台订单支付日期")
	private String accDate;
	@ApiModelProperty(name = "orderStatus", notes = "订单状态")
	private String orderStatus;
	@ApiModelProperty(name = "signMsg", notes = "签名")
	private String signMsg;
	@ApiModelProperty(name = "notifyType", notes = "通知类型")
	private String notifyType;
	@ApiModelProperty(name = "merchParamMap", notes = "订单主键id")
	Map<String, String> merchParamMap = new HashMap<String, String>();
	@ApiModelProperty(name = "orgId", notes = "机构ID")
	private String orgId;
	@ApiModelProperty(name = "userId", notes = "用户ID")
    private Long userId;
	@ApiModelProperty(name = "userName", notes = "用户名")
    private String userName;
	@ApiModelProperty(name = "shopName", notes = "商户名")
    private String shopName;
	@ApiModelProperty(name = "userMobile", notes = "用户手机")
    private String userMobile;
	@ApiModelProperty(name = "shopMobile", notes = "商户电话")
    private String shopMobile;
	@ApiModelProperty(name = "thirdAcct", notes = "用户微信ID")
    private String thirdAcct;
	@ApiModelProperty(name = "channelCode", notes = "完成支付所选择通道代码")
	private String channelCode;
	@ApiModelProperty(name = "submerchid", notes = "完成支付所选择通道子商户账户号")
	private String submerchid;
	@ApiModelProperty(name = "waterNo", notes = "通道交易流水号")
	private String waterNo;
	@ApiModelProperty(name = "bankName", notes = "银行名称")
	private String bankName;
	@ApiModelProperty(name = "bankCardNo", notes = "银行卡号(后四位)")
	private String bankCardNo;
	
	public String getApiName() {
		return apiName;
	}

	public String getNotifyTime() {
		return notifyTime;
	}

	public String getTradeAmt() {
		return tradeAmt;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public String getMerchParam() {
		return merchParam;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public String getAccNo() {
		return accNo;
	}

	public String getAccDate() {
		return accDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public void setMerchParam(String merchParam) {
		this.merchParam = merchParam;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public Map<String, String> getMerchParamMap() {
		return merchParamMap;
	}

	public void setMerchParamMap(Map<String, String> merchParamMap) {
		this.merchParamMap = merchParamMap;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getShopName() {
		return shopName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public String getShopMobile() {
		return shopMobile;
	}

	public String getThirdAcct() {
		return thirdAcct;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public void setShopMobile(String shopMobile) {
		this.shopMobile = shopMobile;
	}

	public void setThirdAcct(String thirdAcct) {
		this.thirdAcct = thirdAcct;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public String getSubmerchid() {
		return submerchid;
	}

	public String getWaterNo() {
		return waterNo;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public void setSubmerchid(String submerchid) {
		this.submerchid = submerchid;
	}

	public void setWaterNo(String waterNo) {
		this.waterNo = waterNo;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

}
