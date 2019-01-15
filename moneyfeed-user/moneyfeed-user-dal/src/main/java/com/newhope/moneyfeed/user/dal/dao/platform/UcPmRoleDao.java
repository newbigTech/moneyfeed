package com.newhope.moneyfeed.user.dal.dao.platform;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.platform.RoleQueryDtoReq;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcPmRoleDao extends BaseDao<UcPmRoleModel> {
	
	
	public PageList<UcPmRoleModel> queryRole(@Param("queryParam") RoleQueryDtoReq queryParam);
	
	public PageList<UcPmRoleModel> queryRole(@Param("queryParam") RoleQueryDtoReq queryParam, PageBounds pageBounds);
	
	public PageList<UcPmRoleModel> getRoleList(@Param("queryParam") RoleQueryDtoReq queryParam);
	
	public PageList<UcPmRoleModel> getRoleList(@Param("queryParam") RoleQueryDtoReq queryParam, PageBounds pageBounds);
}