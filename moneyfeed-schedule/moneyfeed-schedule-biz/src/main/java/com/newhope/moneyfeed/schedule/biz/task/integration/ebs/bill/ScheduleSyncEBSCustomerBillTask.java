package com.newhope.moneyfeed.schedule.biz.task.integration.ebs.bill;

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
import com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.bill.IntergrationEbsSyncBillFeignClient;
import com.newhope.moneyfeed.schedule.biz.task.integration.ebs.baseData.ScheduleSyncEBSCategoryTask;

/**
 * Created by liming on 2018/7/9.
 * 定时任务，同步EBS产品品类
 */
@EScheduler(jobName = "ScheduleSyncEBSCustomerBill", shardingTotalCount = 1, cron = "0 /30 * * * ? *", overwrite = true)
@Component
public class ScheduleSyncEBSCustomerBillTask extends AbstractSimpleElasticJob{

	 private final static Logger logger= LoggerFactory.getLogger(ScheduleSyncEBSCategoryTask.class);
	 
	 @Autowired
	 private IntergrationEbsSyncBillFeignClient feignClient; 
	
	@Override
	public void process(JobExecutionMultipleShardingContext arg0) {
		 logger.info("--------------start-----同步EBS客户对账单-----------------");

	        final BaseResponse<DtoResult> resultBaseResponse = feignClient.syncCustomerBill();

	        if(!ResultCode.SUCCESS.name().equals(resultBaseResponse.getCode())){
	            logger.error("同步EBS产品分类错误",resultBaseResponse.getMsg());
	        }

	        logger.info("------------end-----同步EBS客户对账单-------------------");
	}

}
