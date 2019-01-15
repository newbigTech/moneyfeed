package com.newhope.moneyfeed.user.dal.dao.client;

import org.apache.ibatis.annotations.Param;

import com.newhope.moneyfeed.user.api.bean.client.UcClientUserRoleModel;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcClientUserRoleDao extends BaseDao<UcClientUserRoleModel> {
	
	public int getCustomerRoleCount(@Param("customerId")Long customerId, @Param("roleId")Long roleId);
}