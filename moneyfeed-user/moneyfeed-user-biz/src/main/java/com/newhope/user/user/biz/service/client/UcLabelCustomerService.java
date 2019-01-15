package com.newhope.user.user.biz.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.platform.UcPmLabelCustomerMappingModel;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.platform.UcPmLabelCustomerMappingDao;
import com.newhope.user.user.biz.service.BaseService;

@Service
public class UcLabelCustomerService extends BaseService<UcPmLabelCustomerMappingModel>  {
	
	@Autowired
	private UcPmLabelCustomerMappingDao ucPmLabelCustomerMappingDao;

	@Override
	protected BaseDao<UcPmLabelCustomerMappingModel> getDao() {
		return ucPmLabelCustomerMappingDao;
	}

}
