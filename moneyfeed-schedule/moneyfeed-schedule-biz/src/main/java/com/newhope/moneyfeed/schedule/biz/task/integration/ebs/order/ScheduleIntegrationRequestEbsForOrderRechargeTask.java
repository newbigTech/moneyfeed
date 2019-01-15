package com.newhope.moneyfeed.schedule.biz.task.integration.ebs.order;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.order.IntegrationEbsOrderClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务，请求EBS，获取充值结果，操作integration，通知商城
 *
 * Created by yuyanlin on 2018/12/18
 */
@EScheduler(jobName = "ScheduleIntegrationRequestEbsForOrderRecharge", shardingTotalCount = 1, cron = "0/10 * * * * ? ", overwrite = true)
@Component
public class ScheduleIntegrationRequestEbsForOrderRechargeTask extends AbstractSimpleElasticJob {

    private final static Logger logger = LoggerFactory.getLogger(ScheduleIntegrationRequestEbsForOrderRechargeTask.class);

    @Autowired
    private IntegrationEbsOrderClient integrationEbsOrderClient;

    @Override
    public void process(JobExecutionMultipleShardingContext shardingContext) {
        logger.info("--------------start ScheduleIntegrationRequestEbsForOrderRecharge-----------------");


        final BaseResponse<DtoResult> resultBaseResponse = integrationEbsOrderClient.ebsToMoneyfeedOrdersRechargeResult();

        if(!ResultCode.SUCCESS.name().equals(resultBaseResponse.getCode())){
            logger.error("更新充值状态失败", resultBaseResponse.getMsg());
        }

        logger.info("------------call ScheduleIntegrationRequestEbsForOrderRecharge-------------------");
    }

}
