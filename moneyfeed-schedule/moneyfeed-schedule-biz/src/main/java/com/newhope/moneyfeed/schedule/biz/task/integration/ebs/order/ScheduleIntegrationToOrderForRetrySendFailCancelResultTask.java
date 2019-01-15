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
 * 定时任务，重新发送上次取消订单信息而未成功发送到商城，发送给商城
 *
 * Created by liuyc on 2018/12/04
 */
@EScheduler(jobName = "ScheduleIntegrationToOrderForRetrySendFailCancelResult", shardingTotalCount = 1, cron = "0 */3 * * * ? *", overwrite = true)
@Component
public class ScheduleIntegrationToOrderForRetrySendFailCancelResultTask extends AbstractSimpleElasticJob {
    private final static Logger logger = LoggerFactory.getLogger(ScheduleIntegrationRequestEbsForOrderCreate.class);

    @Autowired
    private IntegrationEbsOrderClient integrationEbsOrderClient;

    @Override
    public void process(JobExecutionMultipleShardingContext shardingContext) {
        logger.info("--------------start ScheduleIntegrationToOrderForRetrySendFailCancelResult-----------------");


        final BaseResponse<DtoResult> resultBaseResponse = integrationEbsOrderClient.ebsToMoneyfeedRetrySendFailCancelResult();

        if(!ResultCode.SUCCESS.getCode().equals(resultBaseResponse.getCode())){
            logger.error("重新发送上次取消订单信息而未成功发送到商城的结果", resultBaseResponse.getMsg());
        }

        logger.info("------------call ScheduleIntegrationToOrderForRetrySendFailCancelResult-------------------");
    }
}
