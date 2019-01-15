package com.newhope.user.user.biz.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.client.UcCustomerAccountModel;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcCustomerAccountDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcCustomerAccountService extends BaseService<UcCustomerAccountModel> {
	
	
	@Autowired
	UcCustomerAccountDao ucCustomerAccountDao;
	@Override
	protected BaseDao<UcCustomerAccountModel> getDao() {
		return ucCustomerAccountDao;
	}
	
}