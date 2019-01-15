package com.newhope.moneyfeed.biz.sys;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.newhope.moneyfeed.api.bean.MqFailMessageModel;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.common.cache.MqCache;
import com.newhope.moneyfeed.dal.dao.uc.MqFailMessageDao;
import com.newhope.moneyfeed.mq.cmd.Command;
import com.newhope.moneyfeed.mq.config.ConstantConfig;
import com.newhope.moneyfeed.mq.receiver.MessageReceiver;
import com.newhope.moneyfeed.mq.sender.CommandSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by liming on 2018/12/18.
 */
@Service
public class ReSendMqMessageService {

    private final Logger logger = LoggerFactory.getLogger(ReSendMqMessageService.class);

    @Autowired
    private MqCache mqCache;
    @Autowired
    private CommandSender commandSender;
    @Autowired
    private MqFailMessageDao mqFailMessageDao;
    
    public  void reSendMqMessage(){

        logger.info("ScheduleMqSendCompensatio begin");
        Map<String, Object> messages = mqCache.getMqCache().hGetAll(ConstantConfig.MQ_CACHE_RETRY_KEY);
        if (messages != null) {
            for (Map.Entry<String, Object> entry : messages.entrySet()) {
                org.springframework.amqp.core.Message myMessage = (org.springframework.amqp.core.Message) entry.getValue();
                Map<String, Object> headers = myMessage.getMessageProperties().getHeaders();
                Long cacheTime = (Long) headers.get(ConstantConfig.MQ_CACHE_RETRY_TIME_KEY);	//消息缓存时间
                String exchange = (String) headers.get(ConstantConfig.MQ_CACHE_RETRY_EXCHANGE);	//消息发送的exchange
                String rootkey = (String) headers.get(ConstantConfig.MQ_CACHE_RETRY_ROOTKEY);	//消息发送的rootkey
                String correlationId = (String) headers.get(MessageReceiver.TRACKING_ID);		//correlationId
                if (cacheTime == null || 86400000 < (
                        System.currentTimeMillis() - cacheTime.longValue()))	{
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        Command command = mapper.readValue(myMessage.getBody(), new TypeReference<Command>() {});
                        String jsonBody= JSONObject.toJSONString(command);
                        insertMqMessage(jsonBody,exchange,rootkey,correlationId,cacheTime);
                    } catch (IOException e) {
                        logger.error("转换mq消息出错",e);
                    }
                    //清除缓存时间超过24小时的垃圾数据
                    mqCache.getMqCache().hDel(ConstantConfig.MQ_CACHE_RETRY_KEY, correlationId);
                } else if (60000 < (System.currentTimeMillis() - cacheTime.longValue())) {		//缓存时间超过1分钟未被confirm则重发
                    //发送消息到MQ, comfirm callback确认后删除该消息缓存
                    try {
                        commandSender.send(myMessage, exchange, rootkey, correlationId);
                    } catch (Exception e) {
                        logger.error("[ScheduleMqSenprocess]发送事件消息异常", e);
                    }
                }
            }
        }
        logger.info("ScheduleMqSendCompensation  end");
    }
    private  void insertMqMessage(String jsonBody,String exchange,String rootkey,String correlationId,long cacheTime){
        MqFailMessageModel mqFailMessageModel=new MqFailMessageModel();
        mqFailMessageModel.setMqMessageJson(jsonBody);
        mqFailMessageModel.setMqRetryExchange(exchange);
        mqFailMessageModel.setMqRetryRootkey(rootkey);
        mqFailMessageModel.setTrackingId(correlationId);
        mqFailMessageModel.setMqMessageTime(new Date(cacheTime));
        mqFailMessageDao.insert(Lists.newArrayList(mqFailMessageModel));
    }
}