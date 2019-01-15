package com.newhope.order.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.order.api.bean.OrderRollbackModel;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.moneyfeed.order.dal.dao.OrderRollbackDao;
import com.newhope.order.biz.service.OrderRollbackService;

@Service("OrderRollbackService")
public class OrderRollbackServiceImpl extends BaseServiceImpl<OrderRollbackModel> implements OrderRollbackService {
	private final Logger logger = LoggerFactory.getLogger(OrderRollbackServiceImpl.class);
	@Autowired
	OrderRollbackDao orderRollbackDao;

	@Override
	protected BaseDao<OrderRollbackModel> getDao() {
		return orderRollbackDao;
	}

}
