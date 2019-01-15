package com.newhope.moneyfeed.integration.biz.service.third;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.ThirdIdRelationModel;
import com.newhope.moneyfeed.integration.api.dto.request.third.ThirdIdRelationQueryDtoReq;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.baseData.ThirdIdRelationDao;

@Service
public class ThirdIdRelationService extends BaseService<ThirdIdRelationModel> {
	public static Logger logger = LoggerFactory.getLogger(ThirdIdRelationService.class);

	@Autowired
	ThirdIdRelationDao thirdIdRelationDao;

	@Override
	protected BaseDao<ThirdIdRelationModel> getDao() {
		return thirdIdRelationDao;
	}

	public List<ThirdIdRelationModel> queryThirdIdRelation(ThirdIdRelationQueryDtoReq queryParam){
		return thirdIdRelationDao.queryThirdIdRelation(queryParam);
	}
}


