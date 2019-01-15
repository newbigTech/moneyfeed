package com.newhope.moneyfeed.user.dal.dao.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerQueryDtoReq;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcCustomerDao extends BaseDao<UcCustomerModel> {
	

	/**
	 * 客户分页查询，适用于商户端
	 * @param queryParam
	 * @param pageBounds
	 * @return
	 */
	PageList<UcCustomerExModel> searchCustomer(@Param("queryParam") CustomerQueryDtoReq queryParam, PageBounds pageBounds);
	
	/**
	 * 不需要分页
	 * @param queryParam
	 * @param pageBounds
	 * @return
	 */
	PageList<UcCustomerExModel> searchCustomer(@Param("queryParam") CustomerQueryDtoReq queryParam);
	
	/**
	 * 客户分页查询，适用于平台，若用上面的searchCustomer，会出现不能去重的重复数据
	 * @param queryParam
	 * @param pageBounds
	 * @return
	 */
	PageList<UcCustomerExModel> queryCustomerList(@Param("queryParam") CustomerQueryDtoReq queryParam, PageBounds pageBounds);
	
	/**
	 * 不需要分页
	 * @param queryParam
	 * @param pageBounds
	 * @return
	 */
	PageList<UcCustomerExModel> queryCustomerList(@Param("queryParam") CustomerQueryDtoReq queryParam);
	
	/**
	 * 获取客户所有员工中最后一次登录时间
	 * @param customerId
	 * @return
	 */
	UcCustomerExModel getCustomerLastLoginTime(@Param("customerId") Long customerId);
	
	/**
	 * 不需要分页
	 * @param queryParam
	 * @param pageBounds
	 * @return
	 */
	List<UcCustomerExModel> queryCustomerAllList(@Param("queryParam") CustomerQueryDtoReq queryParam);
	

}