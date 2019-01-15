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
 * 定时任务，请求EBS，获取订单的取消结果，操作integration，通知商城
 *
 * Created by yuyanlin on 2018/11/21
 */
@EScheduler(jobName = "ScheduleIntegrationRequestEbsForOrderCancel", shardingTotalCount = 1, cron = "0/15 * * * * ?", overwrite = true)
@Component
public class ScheduleIntegrationRequestEbsForOrderCancel extends AbstractSimpleElasticJob {

    private final static Logger logger = LoggerFactory.getLogger(ScheduleIntegrationRequestEbsForOrderCancel.class);

    @Autowired
    private IntegrationEbsOrderClient integrationEbsOrderClient;

    @Override
    public void process(JobExecutionMultipleShardingContext shardingContext) {
        logger.info("--------------start ScheduleIntegrationRequestEbsForOrderCancel-----------------");

        final BaseResponse<DtoResult> resultBaseResponse = integrationEbsOrderClient.ebsToMoneyfeedOrdersCancelResult();

        if(!ResultCode.SUCCESS.getCode().equals(resultBaseResponse.getCode())){
            logger.error("更新订单取消状态失败", resultBaseResponse.getMsg());
        }

        logger.info("------------call ScheduleIntegrationRequestEbsForOrderCancel-------------------");
    }

}
