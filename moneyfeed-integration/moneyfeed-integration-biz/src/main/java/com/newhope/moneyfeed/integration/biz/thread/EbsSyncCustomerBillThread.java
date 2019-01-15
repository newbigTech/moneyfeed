package com.newhope.moneyfeed.integration.biz.thread;

import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.EBSCompanyRespListResult;
import com.newhope.moneyfeed.integration.api.service.SyncEBScompanyService;
import com.newhope.moneyfeed.integration.api.service.ebs.bill.EBSCustomerBillService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.CustomerReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.bill.CustomerBillVoReq;

public class EbsSyncCustomerBillThread implements Callable<Boolean> {

	private final static Logger logger = LoggerFactory.getLogger(EBSSyncCustomerThread.class);

	public EbsSyncCustomerBillThread() {

	}

	@Override
	public Boolean call() throws Exception {

		final EBSCustomerBillService billService = AppContext.getSpringContext().getBean(EBSCustomerBillService.class);

		final SyncEBScompanyService companyService = AppContext.getSpringContext().getBean(SyncEBScompanyService.class);

		final CacheData cacheData = AppContext.getSpringContext().getBean(CacheData.class);

		EbsCompanyModel companyModel = new EbsCompanyModel();

		final EBSCompanyRespListResult companyList = companyService.selectComapny(companyModel);

		if (!ResultCode.SUCCESS.getCode().equals(companyList.getCode())) {
			logger.error("查询需要同步的ebs组织报错", companyList.getMsg());
			return Boolean.TRUE;
		}
		if (CollectionUtils.isEmpty(companyList.getDataList())) {
			logger.info("-------未发现需要同步的EBS组织-----------------");
			return Boolean.TRUE;
		}

		String currentTime = DateUtil.getDateStr(new Date(), DateUtil.YYYYMMDDHHMMSS);

		logger.error("---------开始同步客户账单--------" + currentTime);

		for (EbsCompanyModel company : companyList.getDataList()) {

			String beginTime = (String) cacheData.getData("SYNC_CUSTOMER_BILL_DATE:" + company.getCompanyId());
			if (StringUtils.isEmpty(beginTime)) {
				beginTime = "19000101000000";
			}
			CustomerBillVoReq req = new CustomerBillVoReq();
			req.setOrgId(company.getCompanyId());
			req.setBeginTime(beginTime);
			req.setEndTime(currentTime);
			logger.info("--start param:orgId:[{}],beginTime:[{}],endTime:[{}]--", req.getOrgId(), req.getBeginTime(),
					req.getEndTime());
			final DtoResult result = billService.syncCustomerBill(req);
			if (ResultCode.SUCCESS.getCode().equals(result.getCode())) {
				cacheData.setData("SYNC_CUSTOMER_BILL_DATE:" + company.getCompanyId(), req.getEndTime());
			}
			logger.info("--end param:orgId:[{}],beginTime:[{}],endTime:[{}]--", req.getOrgId(), req.getBeginTime(),
					req.getEndTime());
		}

		logger.error("----同步客户账单结束----" + DateUtil.getDateStr(new Date(), DateUtil.YYYYMMDDHHMMSS));

		return Boolean.TRUE;
	}

}
