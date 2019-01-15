package com.newhope.moneyfeed.user.dal.dao.businessmanage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserRoleModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcBmUserRoleDao extends BaseDao<UcBmUserRoleModel> {
	
	public List<UcPmRoleModel> queryRoleInfoByUserId(@Param("userId")Long userId);

	List<Long> queryUserIdsByRoleIds(@Param("param")List<Long> roleIds);
}