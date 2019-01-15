package com.newhope.moneyfeed.user.dal.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmUserRoleModel;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcPmUserRoleDao extends BaseDao<UcPmUserRoleModel> {
	public List<UcPmRoleModel> queryRoleInfoByUserId(@Param("userId")Long userId);
}