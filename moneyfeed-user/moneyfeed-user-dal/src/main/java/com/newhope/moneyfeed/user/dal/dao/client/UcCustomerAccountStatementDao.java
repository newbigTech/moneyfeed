package com.newhope.moneyfeed.user.dal.dao.client;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerAccountStatementModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerAccountStatementExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementQueryDtoReq;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcCustomerAccountStatementDao extends BaseDao<UcCustomerAccountStatementModel> {

	public PageList<UcCustomerAccountStatementExModel> queryStatementList(@Param("queryParam") CustomerAccountStatementQueryDtoReq queryParam, PageBounds pageBounds);
	
	public PageList<UcCustomerAccountStatementExModel> queryStatementList(@Param("queryParam") CustomerAccountStatementQueryDtoReq queryParam);
	
}