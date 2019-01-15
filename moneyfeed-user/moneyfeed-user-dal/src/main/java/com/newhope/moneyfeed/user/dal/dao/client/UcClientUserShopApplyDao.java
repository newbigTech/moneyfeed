package com.newhope.moneyfeed.user.dal.dao.client;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserShopApplyModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcClientUserShopApplyExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserShopApplyQueryReq;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcClientUserShopApplyDao extends BaseDao<UcClientUserShopApplyModel> {
	
	public PageList<UcClientUserShopApplyExModel> queryUserShopApplyList(@Param("queryParam") ClientUserShopApplyQueryReq queryParam, PageBounds pageBounds);
	
	/**
	 * 不需要分页
	 * @param queryParam
	 * @return
	 */
	public PageList<UcClientUserShopApplyExModel> queryUserShopApplyList(@Param("queryParam") ClientUserShopApplyQueryReq queryParam);
	
	public Integer queryUserShopApplyCount(@Param("queryParam") ClientUserShopApplyQueryReq queryParam);
}