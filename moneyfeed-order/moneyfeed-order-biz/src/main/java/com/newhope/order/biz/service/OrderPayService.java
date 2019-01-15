package com.newhope.order.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.OrderPayByEBSDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PaySpecialDtoReq;

public interface OrderPayService{
	
	DtoResult orderPayByEBS(OrderPayByEBSDtoReq dtoReq);
	
	DtoResult paySpecial(PaySpecialDtoReq paySpecialDtoReq);
}
