package com.newhope.moneyfeed.schedule.biz.task.mq;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dangdang.ddframe.job.spring.annotation.EScheduler;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.schedule.biz.rpc.mq.MqReTryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liming on 2018/12/19.
 */

@EScheduler(jobName = "MqReTryTask", shardingTotalCount = 1, cron = "0 0/33 * * * ?", overwrite = true)
@Component
public class MqReTryTask  extends AbstractSimpleElasticJob {

    private final static Logger logger= LoggerFactory.getLogger(MqReTryTask.class);

    @Autowired
    private MqReTryClient mqReTryClient;

    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {

        final BaseResponse<DtoResult> dtoResultBaseResponse = mqReTryClient.mqRetry();
        if(dtoResultBaseResponse.isSuccess()){
            logger.info("------------mqretry task run success-------------------");
        }else {
            logger.error("------------mqretry task run fail,case [{}]-----------------",dtoResultBaseResponse.getData().getMsg());
        }
    }

}