package com.newhope.moneyfeed.integration.biz.init;

import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.ThirdAppModel;
import com.newhope.moneyfeed.integration.biz.service.third.ThirdAppService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 初始化加载third_app第三方账户数据到AppContext.context
 * @author RDC-201
 *
 */
@Component
public class ThirdAppInitial implements CommandLineRunner {

	@Autowired
	private ThirdAppService thirdAppService;
	@Autowired
	CacheData cacheData;

	@Override
	public void run(String... args) throws Exception {
		ThirdAppModel model = new ThirdAppModel();
		List<ThirdAppModel> thirdAppList = thirdAppService.query(model);
		if (CollectionUtils.isNotEmpty(thirdAppList)) {
			for (ThirdAppModel thirdApp : thirdAppList) {
				//存入redis
				cacheData.getDataCache().set(thirdApp.getApiKey(), thirdApp);
			}
		}
	}

}
