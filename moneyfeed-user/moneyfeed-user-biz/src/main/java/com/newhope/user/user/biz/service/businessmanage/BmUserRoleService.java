package com.newhope.user.user.biz.service.businessmanage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserRoleModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.businessmanage.UcBmUserRoleDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class BmUserRoleService extends BaseService<UcBmUserRoleModel> {

	
	@Autowired
	UcBmUserRoleDao userRoleDao;

	@Override
	protected BaseDao<UcBmUserRoleModel> getDao() {
		return userRoleDao;
	}
	
	public List<UcPmRoleModel> queryRoleInfoByUserId(@Param("userId")Long userId){
		return userRoleDao.queryRoleInfoByUserId(userId);
	}

	public List<Long> queryUserIdsByRoleIds(List<Long> roleIds) {
		return userRoleDao.queryUserIdsByRoleIds(roleIds);
	}
}
