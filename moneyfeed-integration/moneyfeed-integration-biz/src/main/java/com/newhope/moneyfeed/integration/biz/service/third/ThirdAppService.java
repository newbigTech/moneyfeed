package com.newhope.moneyfeed.integration.biz.service.third;

import com.newhope.moneyfeed.common.cache.RSmsCache;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.ThirdAppModel;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.third.ThirdAppDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdAppService extends BaseService<ThirdAppModel> {
	public static Logger logger = LoggerFactory.getLogger(ThirdAppService.class);

	@Autowired
	ThirdAppDao thirdAppDao;

	@Autowired
	ThirdIdRelationService thirdIdRelationService;

	@Autowired
	RSmsCache rSmsCache;

	@Override
	protected BaseDao<ThirdAppModel> getDao() {
		return thirdAppDao;
	}

}
