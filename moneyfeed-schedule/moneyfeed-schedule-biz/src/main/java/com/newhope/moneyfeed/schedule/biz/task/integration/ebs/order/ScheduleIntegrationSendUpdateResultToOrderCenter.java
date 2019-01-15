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
 * 定时任务，把订单更新的结果发送给商城（订单中心）
 *
 * Created by yuyanlin on 2018/12/3
 */
@EScheduler(jobName = "ScheduleIntegrationSendUpdateResultToOrderCenter", shardingTotalCount = 1, cron = "0/5 * * * * ? *", overwrite = true)
@Component
public class ScheduleIntegrationSendUpdateResultToOrderCenter extends AbstractSimpleElasticJob {
    private final static Logger logger = LoggerFactory.getLogger(ScheduleIntegrationRequestEbsForOrderCreate.class);

    @Autowired
    private IntegrationEbsOrderClient integrationEbsOrderClient;


    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
        logger.info("--------------start ScheduleIntegrationSendUpdateResultToOrderCenter-----------------");


        final BaseResponse<DtoResult> resultBaseResponse = integrationEbsOrderClient.ebsToMoneyfeedSendUpdateInfoResult();

        if(!ResultCode.SUCCESS.getCode().equals(resultBaseResponse.getCode())){
            logger.error("重新向订单中心发送更新结果失败", resultBaseResponse.getMsg());
        }

        logger.info("------------end ScheduleIntegrationSendUpdateResultToOrderCenter-------------------");
    }
}
