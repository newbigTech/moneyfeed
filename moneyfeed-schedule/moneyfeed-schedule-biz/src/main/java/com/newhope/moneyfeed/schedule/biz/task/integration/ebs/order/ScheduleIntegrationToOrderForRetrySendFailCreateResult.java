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
 * 定时任务，重新把上次未发送成功的订单创建失败的结果，发送给商城
 *
 * Created by yuyanlin on 2018/11/28
 */
@EScheduler(jobName = "ScheduleIntegrationToOrderForRetrySendFailCreateResult", shardingTotalCount = 1, cron = "0 0/3 * * * ? ", overwrite = true)
@Component
public class ScheduleIntegrationToOrderForRetrySendFailCreateResult extends AbstractSimpleElasticJob {
    private final static Logger logger = LoggerFactory.getLogger(ScheduleIntegrationRequestEbsForOrderCreate.class);

    @Autowired
    private IntegrationEbsOrderClient integrationEbsOrderClient;

    @Override
    public void process(JobExecutionMultipleShardingContext shardingContext) {
        logger.info("--------------start ScheduleIntegrationToOrderForRetrySendFailCreateResult-----------------");


        final BaseResponse<DtoResult> resultBaseResponse = integrationEbsOrderClient.ebsToMoneyfeedRetrySendFailCreateResult();

        if(!ResultCode.SUCCESS.getCode().equals(resultBaseResponse.getCode())){
            logger.error("重新发送上次未发送的创建订单结果失败", resultBaseResponse.getMsg());
        }

        logger.info("------------end ScheduleIntegrationToOrderForRetrySendFailCreateResult-------------------");
    }
}
