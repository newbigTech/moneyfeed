package com.newhope.user.user.biz.service.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleResourceModel;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.platform.UcPmRoleResourceDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcPmRoleResourceService extends BaseService<UcPmRoleResourceModel> {

	
	@Autowired
	UcPmRoleResourceDao ucPmRoleResourceDao;
	
	
	@Override
	protected BaseDao<UcPmRoleResourceModel> getDao() {
		return ucPmRoleResourceDao;
	}

}
