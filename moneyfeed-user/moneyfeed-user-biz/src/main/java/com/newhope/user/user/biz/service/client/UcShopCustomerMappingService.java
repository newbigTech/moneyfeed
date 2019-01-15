package com.newhope.user.user.biz.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerMappingModel;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcShopCustomerMappingDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcShopCustomerMappingService extends BaseService<UcShopCustomerMappingModel> {
	
	
	@Autowired
	UcShopCustomerMappingDao ucShopCustomerMappingDao;
	
	@Override
	protected BaseDao<UcShopCustomerMappingModel> getDao() {
		return ucShopCustomerMappingDao;
	}
	
}