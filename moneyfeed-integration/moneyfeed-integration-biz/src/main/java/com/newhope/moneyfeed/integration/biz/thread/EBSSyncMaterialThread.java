package com.newhope.moneyfeed.integration.biz.thread;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.service.SyncEBSDataService;
import com.newhope.moneyfeed.integration.api.service.SyncEBScompanyService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.MaterialReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.EBSCompanyRespListResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by liming on 2018/7/13.
 */
public class EBSSyncMaterialThread implements Callable<Boolean> {

	private final static Logger logger = LoggerFactory.getLogger(EBSSyncMaterialThread.class);

	private String orgId;

	public EBSSyncMaterialThread(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * Computes a result, or throws an exception if unable to do so.
	 *
	 * @return computed result
	 * @throws Exception
	 *             if unable to compute a result
	 */
	@Override
	public Boolean call() throws Exception {

		final SyncEBSDataService syncEBSDataService = AppContext.getSpringContext().getBean(SyncEBSDataService.class);

		final SyncEBScompanyService syncEBScompanyService = AppContext.getSpringContext()
				.getBean(SyncEBScompanyService.class);

		final CacheData cacheData = AppContext.getSpringContext().getBean(CacheData.class);
		
		EbsCompanyModel companyModel=new EbsCompanyModel();
		if(StringUtils.isNotEmpty(orgId))
		{
		companyModel.setCompanyId(orgId);	
		}
		final EBSCompanyRespListResult syncCompanyList = syncEBScompanyService.selectComapny(companyModel);

		if (!ResultCode.SUCCESS.getCode().equals(syncCompanyList.getCode())) {
			cacheData.setData("IntegrationSyncEBSItemStatus"+orgId, "COMPLETE");// 将物料接口同步状态改为完成
			logger.error("查询需要同步的ebs组织报错", syncCompanyList.getMsg());
			return Boolean.TRUE;
		}
		if (CollectionUtils.isEmpty(syncCompanyList.getDataList())) {
			cacheData.setData("IntegrationSyncEBSItemStatus"+orgId, "COMPLETE");// 将物料接口同步状态改为完成
			logger.info("-------未发现需要同步的EBS组织-----------------");
			return Boolean.TRUE;
		}
		String courrentTime = DateUtil.getDateStr(new Date(), DateUtil.YYYYMMDDHHMMSS);
		for (EbsCompanyModel ebsCompanyModel : syncCompanyList.getDataList()) {

			MaterialReq materialReq = new MaterialReq();
			materialReq.setCompanyCode(ebsCompanyModel.getShortCode());
			
			materialReq.setBeginTime("19000101000000");// 考虑出厂价更新
			materialReq.setEndTime(courrentTime);
			logger.info(
					"-----------sync meterial,param:  companyCode:[{}],warehouse:[{}],beginTime:[{}],endTime:[{}]-----------------",
					materialReq.getCompanyCode(), materialReq.getBeginTime(), materialReq.getEndTime());
			final Result result = syncEBSDataService.syncEBSMaterialCheckParam(materialReq);
			logger.info("同步物料结束，公司简码：" + ebsCompanyModel.getShortCode() + "----" + result.getMsg() + "-----"
					+ result.getCode());

		}
		cacheData.setData("IntegrationSyncEBSItemStatus"+orgId, "COMPLETE");// 将物料接口同步状态改为完成
		return Boolean.TRUE;
	}
}