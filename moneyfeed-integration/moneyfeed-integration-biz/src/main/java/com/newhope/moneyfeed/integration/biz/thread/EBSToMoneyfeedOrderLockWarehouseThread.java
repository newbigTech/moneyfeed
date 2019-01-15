package com.newhope.moneyfeed.integration.biz.thread;
import java.util.concurrent.Callable;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.EBSCompanyRespListResult;
import com.newhope.moneyfeed.integration.api.service.SyncEBScompanyService;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsToMoneyfeedOrderService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.LockWarehouseReq;

/*
 * created by bo.wang 20181207
 * */
public class EBSToMoneyfeedOrderLockWarehouseThread implements Callable<Boolean> {

	private final static Logger logger = LoggerFactory.getLogger(EBSToMoneyfeedOrderLockWarehouseThread.class);

	@Override
	public Boolean call() throws Exception {

		final SyncEBScompanyService syncEBScompanyService = AppContext.getSpringContext().getBean(SyncEBScompanyService.class);
		EbsCompanyModel companyModel = new EbsCompanyModel();
		final EBSCompanyRespListResult syncCompanyList = syncEBScompanyService.selectComapny(companyModel);
		final EbsToMoneyfeedOrderService ebsToMoneyfeedOrderService = AppContext.getSpringContext().getBean(EbsToMoneyfeedOrderService.class);

		if (!ResultCode.SUCCESS.getCode().equals(syncCompanyList.getCode())) {
			logger.error("查询需要同步的ebs组织报错", syncCompanyList.getMsg());
			return Boolean.TRUE;
		}
		if (CollectionUtils.isEmpty(syncCompanyList.getDataList())) {
			logger.info("-------未发现需要同步的EBS组织-----------------");
			return Boolean.TRUE;
		}

		for (EbsCompanyModel ebsCompanyModel : syncCompanyList.getDataList()) {

			logger.info("*****查询EBS订单锁库状态*****公司简码：" + ebsCompanyModel.getShortCode());

			LockWarehouseReq req = new LockWarehouseReq();
			req.setOrgId(ebsCompanyModel.getCompanyId());

			final DtoResult result = ebsToMoneyfeedOrderService.ebsToMoneyfeedOrderLockWarehouseResult(req);

			logger.info("*****查询EBS订单锁库状态结束*****公司简码：" + ebsCompanyModel.getShortCode() + "----" + result.getMsg()
					+ "-----" + result.getCode());
		}

		return Boolean.TRUE;
	}

}
