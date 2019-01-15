package com.newhope.user.user.biz.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.client.UcClientUserThirdAccountModel;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcClientUserThirdAccountDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcClientUserThirdAccountService extends BaseService<UcClientUserThirdAccountModel> {
	
	
	@Autowired
	UcClientUserThirdAccountDao ucClientUserThirdAccountDao;
	
	
	@Override
	protected BaseDao<UcClientUserThirdAccountModel> getDao() {
		return ucClientUserThirdAccountDao;
	}
	
}