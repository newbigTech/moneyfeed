package com.newhope.moneyfeed.integration.biz.service.common;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderLogModel;
import com.newhope.moneyfeed.integration.api.enums.common.MQSendToClientEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderLogTypeEnum;
import com.newhope.moneyfeed.integration.api.exbean.common.SendMQCommonModel;

import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderLogServiceImpl;
import com.newhope.moneyfeed.mq.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dave Chen on 2018/12/17.
 */
@Service
public class SendMQService {

    private static Logger logger = LoggerFactory.getLogger(SendMQService.class);

    @Autowired
    private EbsOrderLogServiceImpl ebsOrderLogService;

    @Autowired
    private MessageService messageService;



    /**
     * 发送MQ信息
     * @param mq
     */
    public  void sendMq(SendMQCommonModel mq){
        sendMq(mq,true,null);
    }

    /**
     * 定时重发失败的MQ信息
     */
    public  void ReSendMQMsg(){

        List<EbsOrderLogModel> logs = ebsOrderLogService.queryFailSendMqLog();

        if(logs!=null && !logs.isEmpty()){

            logger.info("本次中台需要重发的数量为"+logs.size());

            for (EbsOrderLogModel log : logs) {
                try {

                    if (log != null) {
                        SendMQCommonModel mq = JSON.parseObject(log.getMsgJson(), SendMQCommonModel.class);
                        sendMq(mq,false,log);
                    }
                }
                catch (Exception e){
                    CommonService.formatExceptionMsg(this.getClass(),e);
                }
            }
        }

    }


    private void sendMq(SendMQCommonModel mq,boolean fisrtSend,EbsOrderLogModel log) {
        if (mq.getMqMsgKind() == null) {
            return;
        }

        if (mq.getMqSendToClient() == null) {
            return;
        }


        String client = mq.getMqSendToClient().name();
        String data = JSON.toJSONString(mq);

        try {

            logger.info("开始向"+client+"中心发送MQ，内容为"+data);

            if (client.equals(MQSendToClientEnum.order.name())) {
                // to do 调用发送到 订单中心的MQ方法
                messageService.sendMessageFromIntToOrder(data);
            } else if (client.equals(MQSendToClientEnum.product.name())) {
                //to do 抵用发送到  产品中心的MQ方法
            }

            if(!fisrtSend && log!=null){
                updateSuccesslog(log);
            }

            logger.info("结束向"+client+"中心发送MQ，内容为"+data+",发送完成");

        }catch (Exception e){
            CommonService.formatExceptionMsg(this.getClass(),e);
            if(fisrtSend) {
              //第一次发送失败，直接新增一条日期
                saveErrorLog(mq, e);
            }
            else if(log!=null){
                //非第一次发送失败更新记录
                updateErrorlog(log,e);
            }
        }
    }



    private  void updateSuccesslog(EbsOrderLogModel log) {

        try {
            log.setOperationResult((byte) 1);
            log.setSendTime(new Date());
            EbsOrderLogModel old = new EbsOrderLogModel();
            old.setId(log.getId());
            ebsOrderLogService.update(old, log);
        } catch (Exception e1) {
            CommonService.formatExceptionMsg(this.getClass(), e1);
        }
    }

    private  void updateErrorlog(EbsOrderLogModel log,Exception e) {

        try {
            log.setSendTime(new Date());
            log.setFailCount(log.getFailCount() + 1);
            log.setErrorMsg(e.getMessage());
            log.setOperationResult((byte) 0);
            EbsOrderLogModel old = new EbsOrderLogModel();
            old.setId(log.getId());
            ebsOrderLogService.update(old, log);
        } catch (Exception e1) {
            CommonService.formatExceptionMsg(this.getClass(), e1);
        }
    }

    private  void saveErrorLog(SendMQCommonModel mq,Exception e){

        try {
            EbsOrderLogModel log = new EbsOrderLogModel();
            log.setErrorMsg(e.getMessage());
            log.setFailCount(0);
            log.setLogType(EbsOrderLogTypeEnum.SEND_MQ_MSG.name());
            log.setMsgJson(JSON.toJSONString(mq));
            log.setOperationResult((byte) 0);
            log.setSendTime(new Date());

            List<EbsOrderLogModel> logs = new ArrayList<EbsOrderLogModel>(1);
            logs.add(log);
            ebsOrderLogService.save(logs);
        }
        catch ( Exception e1){
            CommonService.formatExceptionMsg(this.getClass(),e1);
        }
    }

}
