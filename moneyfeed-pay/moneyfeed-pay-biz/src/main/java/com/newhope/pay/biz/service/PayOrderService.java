package com.newhope.pay.biz.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.pay.api.bean.PayOrderInfoModel;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderCallbackDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoQueryDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PaySpecialDtoReq;
import com.newhope.moneyfeed.pay.api.dto.response.PayCallbackDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderCreateDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderListDtoResult;

public interface PayOrderService extends BaseService<PayOrderInfoModel>{
	
	/**  
	* @Title: createPayOrder  
	* @Description: 创建商城订单的支付,充值订单
	*/
	public PayOrderCreateDtoResult createPayOrder(PayOrderInfoDtoReq payOrderInfoDtoReq);
	
	/**
	* @Title: createPayUserAccOrder
	* @Description: 创建账户充值 的支付订单
	*/
	public PayOrderCreateDtoResult createPayUserAccOrder(PayOrderInfoDtoReq payOrderInfoDtoReq);

	/**
	* @Title: payCallback  
	* @Description: 支付后回调
	*/
	public PayCallbackDtoResult payCallback(PayOrderCallbackDtoReq payOrderCallbackDtoReq);
	
	PageList<PayOrderInfoModel> queryList(PayOrderInfoQueryDtoReq queryDtoReq);

	/**  
	* @Title: paySpecial  
	* @Description: 特殊处理方法
	*/
	public DtoResult paySpecial(PaySpecialDtoReq paySpecialDtoReq);
	
	/**  
	* @Title: queryPayOrderList  
	* @Description: 查询支付订单基本列表
	*/
	PayOrderListDtoResult queryPayOrderList(PayOrderDtoReq payOrderDtoReq);
	
}
