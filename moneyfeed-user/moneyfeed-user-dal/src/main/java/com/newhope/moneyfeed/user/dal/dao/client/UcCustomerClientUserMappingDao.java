package com.newhope.moneyfeed.user.dal.dao.client;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerClientUserMappingExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerContactQueryDtoReq;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcCustomerClientUserMappingDao extends BaseDao<UcCustomerClientUserMappingModel> {
	
	public PageList<UcCustomerClientUserMappingExModel> queryCustomerContact(
			@Param("queryParam") CustomerContactQueryDtoReq queryParam, PageBounds pageBounds);
	
	/**
	 * 不需要分页
	 * @param queryParam
	 * @param pageBounds
	 * @return
	 */
	public PageList<UcCustomerClientUserMappingExModel> queryCustomerContact(
			@Param("queryParam") CustomerContactQueryDtoReq queryParam);
	
	
	public PageList<UcCustomerClientUserMappingExModel> queryCustomerClientUser(
			@Param("queryParam") CustomerContactQueryDtoReq queryParam, PageBounds pageBounds);
	
	/**
	 * 不需要分页
	 * @param queryParam
	 * @param pageBounds
	 * @return
	 */
	public PageList<UcCustomerClientUserMappingExModel> queryCustomerClientUser(
			@Param("queryParam") CustomerContactQueryDtoReq queryParam);
	
	public PageList<UcCustomerClientUserMappingExModel> queryCustomerEmployee(
			@Param("queryParam") CustomerContactQueryDtoReq queryParam, PageBounds pageBounds);
	
	/**
	 * 不需要分页
	 * @param queryParam
	 * @param pageBounds
	 * @return
	 */
	public PageList<UcCustomerClientUserMappingExModel> queryCustomerEmployee(
			@Param("queryParam") CustomerContactQueryDtoReq queryParam);
	
	public void removeCustomerClientUserMapping(@Param("customerClientUserId") Long customerClientUserId);
}