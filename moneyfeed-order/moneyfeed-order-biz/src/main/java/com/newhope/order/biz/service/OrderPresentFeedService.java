package com.newhope.order.biz.service;

import com.newhope.moneyfeed.order.api.bean.OrderPresentFeedModel;
import com.newhope.moneyfeed.order.api.dto.request.present.OrderPresentFeedQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.present.OrderPresentFeedListDtoResult;

public interface OrderPresentFeedService extends BaseService<OrderPresentFeedModel>{
	
	public OrderPresentFeedListDtoResult queryOrderPresentFeed(OrderPresentFeedQueryDtoReq dtoReq);

	
}
