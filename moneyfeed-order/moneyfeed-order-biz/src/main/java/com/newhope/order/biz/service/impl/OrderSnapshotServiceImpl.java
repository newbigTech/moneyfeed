package com.newhope.order.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.order.api.bean.OrderSnapshotModel;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.moneyfeed.order.dal.dao.OrderSnapshotDao;
import com.newhope.order.biz.service.OrderSnapshotService;

@Service("OrderSnapshotService")
public class OrderSnapshotServiceImpl extends BaseServiceImpl<OrderSnapshotModel> implements OrderSnapshotService {
	private final Logger logger = LoggerFactory.getLogger(OrderSnapshotServiceImpl.class);
	@Autowired
	OrderSnapshotDao orderSnapshotDao;
	
	protected BaseDao<OrderSnapshotModel> getDao() {
		return orderSnapshotDao;
	}
	

}
