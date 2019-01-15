package com.newhope.user.user.biz.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.platform.UcPmResourceModel;
import com.newhope.moneyfeed.user.api.dto.request.platform.ResourceQueryDtoReq;
import com.newhope.moneyfeed.user.api.exbean.platform.UcPmResourceExModel;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.platform.UcPmResourceDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcPmResourceService extends BaseService<UcPmResourceModel> {

	
	@Autowired
	UcPmResourceDao ucPmResourceDao;
	
	@Override
	protected BaseDao<UcPmResourceModel> getDao() {
		return ucPmResourceDao;
	}

	public List<UcPmResourceModel> searchResource(ResourceQueryDtoReq resourceQueryReq){
		return ucPmResourceDao.searchResource(resourceQueryReq);
	}
	
	public List<UcPmResourceExModel> searchRoleResource(ResourceQueryDtoReq resourceQueryReq){
		return ucPmResourceDao.searchRoleResource(resourceQueryReq);
	}
}
