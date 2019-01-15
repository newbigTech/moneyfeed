package com.newhope.order.biz.service;

import com.newhope.moneyfeed.order.api.bean.OrderSysParamModel;
import com.newhope.moneyfeed.order.api.dto.request.base.OrderSysParamQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.base.OrderSysParamListDtoResult;

public interface OrderSysParamService extends BaseService<OrderSysParamModel> {

	OrderSysParamListDtoResult getSysParam(OrderSysParamQueryDtoReq dtoReq);
}
