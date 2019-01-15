package com.newhope.moneyfeed.schedule.biz.task.integration.ebs.baseData;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.schedule.biz.rpc.integration.ebs.baseData.EbsSyncBaseDataFeignClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liming on 2018/7/9.
 * 定时任务，同步EBS产品品类
 */
@EScheduler(jobName = "SyncEBSCategoryForMoneyfeed", shardingTotalCount = 1, cron = "0 /30 * * * ? *", overwrite = true)
@Component
public class ScheduleSyncEBSCategoryTask extends AbstractSimpleElasticJob {

    private final static Logger logger= LoggerFactory.getLogger(ScheduleSyncEBSCategoryTask.class);

    @Autowired
    private EbsSyncBaseDataFeignClient feignClient;

    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {

        logger.info("--------------start sync ebs category-----------------");

        final BaseResponse<DtoResult> resultBaseResponse = feignClient.syncEBSCategory();

        if(!ResultCode.SUCCESS.name().equals(resultBaseResponse.getCode())){
            logger.error("同步EBS产品分类错误",resultBaseResponse.getMsg());
        }

        logger.info("------------call ebs category end-------------------");
    }
}