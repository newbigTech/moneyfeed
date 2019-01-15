package com.newhope.user.user.biz.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.client.UcClientUserRoleModel;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcClientUserRoleDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcClientUserRoleService extends BaseService<UcClientUserRoleModel> {
	
	
	@Autowired
	UcClientUserRoleDao ucClientUserRoleDao;
	
	
	@Override
	protected BaseDao<UcClientUserRoleModel> getDao() {
		return ucClientUserRoleDao;
	}
	
	/**
	 * 获取该客户role角色的员工数
	 * @param customerId
	 * @param roleId
	 * @return
	 */
	public int getCustomerRoleCount(Long customerId, Long roleId) {
		return ucClientUserRoleDao.getCustomerRoleCount(customerId, roleId);
	}
	
}