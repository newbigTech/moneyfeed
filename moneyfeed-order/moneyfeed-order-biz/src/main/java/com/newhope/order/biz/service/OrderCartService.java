package com.newhope.order.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.order.api.bean.OrderCartModel;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.carts.OrderCartsSkuListDtoResult;

public interface OrderCartService extends BaseService<OrderCartModel>{

	OrderCartsSkuListDtoResult getCarts(CartsQueryDtoReq dtoReq);
	
	DtoResult updateCarts(CartsUpdateDtoReq dtoReq);
}
