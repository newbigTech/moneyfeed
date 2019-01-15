package com.newhope.order.biz.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.newhope.moneyfeed.order.api.bean.IntegrationLogModel;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.moneyfeed.order.dal.dao.IntegrationLogDao;
import com.newhope.order.biz.service.IntegrationLogService;


@Service
public class IntegrationLogServiceImpl extends BaseServiceImpl<IntegrationLogModel>  implements IntegrationLogService {

    private final Logger logger = LoggerFactory.getLogger(IntegrationLogServiceImpl.class);

    @Autowired
    IntegrationLogDao integrationLogDao;
    
	@Override
	protected BaseDao<IntegrationLogModel> getDao() {
		return integrationLogDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addLog(IntegrationLogModel integrationLogModel) {
		
		save(Arrays.asList(integrationLogModel));
	}

}


