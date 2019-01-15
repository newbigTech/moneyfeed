package com.newhope.openapi.api.vo.request.pay;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: PayOrderCallbackReq  
* @Description: 支付平台回调传入的参数
* @author luoxl
* @date 2018年12月25日 上午8:46:54  
*    
*/
public class PayOrderCallbackReq implements Serializable {

	private static final long serialVersionUID = 2307994736147076419L;
	
	@ApiModelProperty(name = "apiName", notes = "接口名字")
	private String apiName;
	@ApiModelProperty(name = "notifyTime", notes = "通知时间")
	private String notifyTime;
	@ApiModelProperty(name = "tradeAmt", notes = "支付金额")
	private String tradeAmt;
	@ApiModelProperty(name = "merchNo", notes = "商户号")
	private String merchNo;
	@ApiModelProperty(name = "merchParam", notes = "商户参数")
	private String merchParam;
	@ApiModelProperty(name = "orderNo", notes = "商户订单号")
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
	@ApiModelProperty(name = "notifyType", notes = "支付平台返回商户银行账号")
    private String shopBankAcco;

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

	public String getShopBankAcco() {
		return shopBankAcco;
	}

	public void setShopBankAcco(String shopBankAcco) {
		this.shopBankAcco = shopBankAcco;
	}


}
