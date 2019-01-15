package com.newhope.moneyfeed.pay.api.dto.req;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *
 * PayOrderInfo query entity
 * {@link com.newhope.moneyfeed.pay.api.bean.PayOrderInfoModel}
 * @author bena.peng
 * @date 2018/12/19 15:51
 */


public class PayOrderInfoQueryDtoReq extends PageDtoReq {
    private static final long serialVersionUID = 6678400954211865312L;

    @ApiModelProperty(name = "tradeBeginDate", notes = "支付订单开始日期，格式yyyy-MM-dd HH:mm:ss")
    private String tradeBeginDate;
    @ApiModelProperty(name = "tradeEndDate", notes = "支付订单结束日期，格式yyyy-MM-dd HH:mm:ss")
    private String tradeEndDate;

    @ApiModelProperty(name = "payOrderNoList", notes = "支付订单集合")
    private List<String> payOrderNoList;

    @ApiModelProperty(name = "checkStatusList", notes = "对账状态集合")
    private List<String> checkStatusList;
    
    @ApiModelProperty(name = "payStatusList", notes = "支付状态集合")
    private List<String> payStatusList;


    public List<String> getPayOrderNoList() {
        return payOrderNoList;
    }

    public void setPayOrderNoList(List<String> payOrderNoList) {
        this.payOrderNoList = payOrderNoList;
    }

    public List<String> getCheckStatusList() {
        return checkStatusList;
    }

    public void setCheckStatusList(List<String> checkStatusList) {
        this.checkStatusList = checkStatusList;
    }

    public String getTradeBeginDate() {
        return tradeBeginDate;
    }

    public void setTradeBeginDate(String tradeBeginDate) {
        this.tradeBeginDate = tradeBeginDate;
    }

    public String getTradeEndDate() {
        return tradeEndDate;
    }

    public void setTradeEndDate(String tradeEndDate) {
        this.tradeEndDate = tradeEndDate;
    }

	public List<String> getPayStatusList() {
		return payStatusList;
	}

	public void setPayStatusList(List<String> payStatusList) {
		this.payStatusList = payStatusList;
	}
}
