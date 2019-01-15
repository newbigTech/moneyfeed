package com.newhope.order.biz.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.order.api.bean.OrderPresentFeedModel;
import com.newhope.moneyfeed.order.api.dto.request.present.OrderPresentFeedQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.present.OrderPresentFeedListDtoResult;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.moneyfeed.order.dal.dao.OrderPresentFeedDao;
import com.newhope.order.biz.service.OrderPresentFeedService;

@Service
public class OrderPresentFeedServiceImpl extends BaseServiceImpl<OrderPresentFeedModel>  implements OrderPresentFeedService {

    private final Logger logger = LoggerFactory.getLogger(OrderPresentFeedServiceImpl.class);

    @Autowired
    OrderPresentFeedDao orderPresentFeedDao;
    
	@Override
	protected BaseDao<OrderPresentFeedModel> getDao() {
		
		return orderPresentFeedDao;
	}

	@Override
	public OrderPresentFeedListDtoResult queryOrderPresentFeed(OrderPresentFeedQueryDtoReq dtoReq) {
		OrderPresentFeedListDtoResult rderPresentFeedListDtoResult = new OrderPresentFeedListDtoResult();
		OrderPresentFeedModel orderPresentFeed = new OrderPresentFeedModel();
		orderPresentFeed.setOrderId(dtoReq.getOrderId());
		List<OrderPresentFeedModel> list = query(orderPresentFeed);
		rderPresentFeedListDtoResult.setOrderPresentFeeds(list);
		rderPresentFeedListDtoResult.setCode(ResultCode.SUCCESS.getCode());
		return rderPresentFeedListDtoResult;
	}

}
