package com.newhope.moneyfeed.integration.dal.dao.ebs.baseData;

import java.util.List;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.ThirdIdRelationModel;
import com.newhope.moneyfeed.integration.api.dto.request.third.ThirdIdRelationQueryDtoReq;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

public interface ThirdIdRelationDao extends BaseDao<ThirdIdRelationModel> {
	
	List<ThirdIdRelationModel> queryThirdIdRelation(@Param("queryParam") ThirdIdRelationQueryDtoReq queryParam);
}