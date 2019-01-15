package com.newhope.moneyfeed.biz.mq.receiver;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.newhope.moneyfeed.api.bean.MqFailMessageModel;
import com.newhope.moneyfeed.dal.dao.uc.MqFailMessageDao;
import com.newhope.moneyfeed.mq.cmd.Command;
import com.newhope.moneyfeed.mq.config.ConstantConfig;
import com.newhope.moneyfeed.mq.receiver.MessageReceiver;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created by liming on 2018/12/21.
 */
@Component
public class DeathMessageReceiver extends MessageReceiver implements ChannelAwareMessageListener {


    private final static Logger logger = LoggerFactory.getLogger(DeathMessageReceiver.class);

    @Autowired
    private MqFailMessageDao mqFailMessageDao;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String messagestr=new String(message.getBody(),"utf-8");
            logger.info("-----------从mq获取的死信消息为[{}]--------------------------",messagestr);
            Map<String, Object> headers = message.getMessageProperties().getHeaders();
            Long cacheTime = (Long) headers.get(ConstantConfig.MQ_CACHE_RETRY_TIME_KEY);	//消息缓存时间
            String exchange = (String) headers.get(ConstantConfig.MQ_CACHE_RETRY_EXCHANGE);	//消息发送的exchange
            String rootkey = (String) headers.get(ConstantConfig.MQ_CACHE_RETRY_ROOTKEY);	//消息发送的rootkey
            String correlationId = (String) headers.get(MessageReceiver.TRACKING_ID);		//correlationId
            insertMqMessage(messagestr,exchange,rootkey,correlationId,cacheTime);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            logger.error("保存死信队列信息出错",e);
            return;
        }
    }

    private  void insertMqMessage(String jsonBody,String exchange,String rootkey,String correlationId,Long cacheTime){
        MqFailMessageModel mqFailMessageModel=new MqFailMessageModel();
        mqFailMessageModel.setMqMessageJson(jsonBody);
        mqFailMessageModel.setMqRetryExchange(exchange);
        mqFailMessageModel.setMqRetryRootkey(rootkey);
        mqFailMessageModel.setTrackingId(correlationId);
        mqFailMessageModel.setMqMessageTime(cacheTime==null?null:new Date(cacheTime));
        mqFailMessageDao.insert(Lists.newArrayList(mqFailMessageModel));
    }

}