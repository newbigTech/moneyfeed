package com.newhope.moneyfeed.pay.api.dto.response;

import java.io.Serializable;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModelProperty;


/**  
* @ClassName: PayOrderInfoDtoResult  
* @Description: 创建支付订单后返回的数据  
* @author luoxl
* @date 2018年12月25日 上午8:51:42  
*    
*/
public class PayOrderCreateDtoResult extends DtoResult implements Serializable {
 
	private static final long serialVersionUID = -8582378007351767605L;

	@ApiModelProperty(name = "payOrderId", notes = "支付订单ID")
    private Long payOrderId;
	@ApiModelProperty(name = "payOrderNo", notes = "支付订单No")
    private String payOrderNo;
	@ApiModelProperty(name = "moneyfeedOrderId", notes = "订单号")
    private Long moneyfeedOrderId;
	@ApiModelProperty(name = "platformId", notes = "商户ID")
    private String platformId;
	@ApiModelProperty(name = "merchantAcc", notes = "商户帐号")
    public String merchantAcc;
	@ApiModelProperty(name = "cusNo", notes = "客户号")
	private String cusNo;
    
	public Long getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Long payOrderId) {
		this.payOrderId = payOrderId;
	}

	public Long getMoneyfeedOrderId() {
		return moneyfeedOrderId;
	}

	public void setMoneyfeedOrderId(Long moneyfeedOrderId) {
		this.moneyfeedOrderId = moneyfeedOrderId;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getPlatformId() {
		return platformId;
	}

	public String getMerchantAcc() {
		return merchantAcc;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public void setMerchantAcc(String merchantAcc) {
		this.merchantAcc = merchantAcc;
	}

	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

}
