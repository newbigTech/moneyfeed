package com.newhope.user.user.biz.service.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmUserRoleModel;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.platform.UcPmUserRoleDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcPmUserRoleService extends BaseService<UcPmUserRoleModel> {

	
	@Autowired
	UcPmUserRoleDao ucPmUserRoleDao;

	@Override
	protected BaseDao<UcPmUserRoleModel> getDao() {
		return ucPmUserRoleDao;
	}
	
	public List<UcPmRoleModel> queryRoleInfoByUserId(@Param("userId")Long userId){
		return ucPmUserRoleDao.queryRoleInfoByUserId(userId);
	}
}
