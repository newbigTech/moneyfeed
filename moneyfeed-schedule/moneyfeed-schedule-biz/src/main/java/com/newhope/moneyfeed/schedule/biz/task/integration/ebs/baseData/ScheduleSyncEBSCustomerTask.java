package com.newhope.moneyfeed.schedule.biz.task.integration.ebs.baseData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.baseData.EbsSyncBaseDataFeignClient;

/**
 * Created by liming on 2018/7/9.
 * 定时任务，同步EBS客户
 */
@EScheduler(jobName = "SyncEBSCustomer", shardingTotalCount = 1, cron = "0 /30 * * * ? *", overwrite = true)
@Component
public class ScheduleSyncEBSCustomerTask extends AbstractSimpleElasticJob{

	private final static Logger logger = LoggerFactory.getLogger(ScheduleSyncEBSCategoryTask.class);

	@Autowired
	private EbsSyncBaseDataFeignClient feignClient;
	
	@Override
	public void process(JobExecutionMultipleShardingContext arg0) {
		logger.info("--------------开始同步EBS客户-----------------");

		final BaseResponse<DtoResult> resultBaseResponse = feignClient.syncEBSCustomer();

		if (!ResultCode.SUCCESS.getCode().equals(resultBaseResponse.getCode())) {
			logger.error("同步EBS客户错误", resultBaseResponse.getMsg());
		}

		logger.info("------------同步EBS客户异步线程结束-------------------");
	}

}
