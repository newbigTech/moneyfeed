package com.newhope.order.biz.service;

import com.newhope.moneyfeed.order.api.bean.IntegrationLogModel;

public interface IntegrationLogService extends BaseService<IntegrationLogModel>{
	
	public void addLog(IntegrationLogModel integrationLogModel);
	
}
