package com.newhope.moneyfeed.schedule.biz.task.integration.ebs.order;

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
import com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.order.IntegrationEbsOrderClient;

/**
 * 定时任务，获取EBS锁库成功订单并通知订单中心
 * 
 * add by bo.wang 20181210
 *
 */
@EScheduler(jobName = "ScheduleIntegrationQueryLockWarehouseTask", shardingTotalCount = 1, cron = "0 /10 * * * ? ", overwrite = true)
@Component
public class ScheduleIntegrationQueryLockWarehouseTask extends AbstractSimpleElasticJob {

	private final static Logger logger = LoggerFactory.getLogger(ScheduleIntegrationQueryLockWarehouseTask.class);

	@Autowired
	private IntegrationEbsOrderClient integrationEbsOrderClient;

	@Override
	public void process(JobExecutionMultipleShardingContext arg0) {
		logger.info("***********start**获取EBS锁库成功订单并通知订单中心********");

		final BaseResponse<DtoResult> resultBaseResponse = integrationEbsOrderClient.ebsToMoneyfeedOrderLockWarehouseResult();

		if (!ResultCode.SUCCESS.getCode().equals(resultBaseResponse.getCode())) {
			logger.error("获取EBS锁库成功订单异常:"+resultBaseResponse.getMsg());
		}

		logger.info("***********end**获取EBS锁库成功订单并通知订单中心********");
	}

}
