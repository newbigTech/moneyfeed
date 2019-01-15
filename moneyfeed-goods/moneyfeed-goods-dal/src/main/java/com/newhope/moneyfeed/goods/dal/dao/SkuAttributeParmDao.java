package com.newhope.moneyfeed.goods.dal.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.newhope.moneyfeed.goods.api.bean.SkuAttributeParmModel;

public interface SkuAttributeParmDao extends BaseDao<SkuAttributeParmModel> {
	
	Long insertAllWeightList(@Param("fromDate") Date fromDate);
}