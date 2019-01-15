package com.newhope.moneyfeed.user.dal.dao.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserQueryDtoReq;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcClientUserDao extends BaseDao<UcClientUserModel> {
	 List<UcClientUserModel> searchClientUser(@Param("queryParam") ClientUserQueryDtoReq queryParam, @Param("start") Long start,
             @Param("size") Integer size);

	 Long searchClientUserCount(@Param("queryParam") ClientUserQueryDtoReq queryParam);
	 
}