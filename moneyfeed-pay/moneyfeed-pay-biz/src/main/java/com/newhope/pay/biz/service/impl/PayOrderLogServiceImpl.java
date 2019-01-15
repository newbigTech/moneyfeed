package com.newhope.pay.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.pay.api.bean.PayOrderLogModel;
import com.newhope.moneyfeed.pay.dal.BaseDao;
import com.newhope.moneyfeed.pay.dal.dao.PayOrderLogDao;
import com.newhope.pay.biz.service.PayOrderLogService;

@Service("PayOrderLogService")
public class PayOrderLogServiceImpl extends BaseServiceImpl<PayOrderLogModel> implements PayOrderLogService {
	
	private final Logger logger = LoggerFactory.getLogger(PayOrderLogServiceImpl.class);

	@Autowired
	PayOrderLogDao payOrderLogDao;
	
	@Override
	protected BaseDao<PayOrderLogModel> getDao() {
		return payOrderLogDao;
	}

	
}
