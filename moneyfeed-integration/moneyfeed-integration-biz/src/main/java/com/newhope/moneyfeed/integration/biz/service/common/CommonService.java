package com.newhope.moneyfeed.integration.biz.service.common;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.integration.api.enums.common.MQMsgKindEnum;
import com.newhope.moneyfeed.integration.api.enums.common.MQSendToClientEnum;
import com.newhope.moneyfeed.integration.api.exbean.common.SendMQCommonModel;
import com.newhope.moneyfeed.integration.biz.rpc.feign.base.CommonFeignClient;

import com.newhope.moneyfeed.order.api.dto.request.order.pay.PayResultDtoReq;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yuyanlin on 2018/11/21
 */
@Service
public class CommonService {

    private static Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private CommonFeignClient commonFeignClient;
    
    @Autowired
    private CacheData cacheData;

    @Autowired
    private SendMQService sendMQService;

    public DtoResult getSuccessDtoResult() {
        DtoResult successDtoResult = new DtoResult();
        successDtoResult.setCode(ResultCode.SUCCESS.getCode());
        successDtoResult.setMsg(ResultCode.SUCCESS.getDesc());

        return successDtoResult;
    }

    public static void formatExceptionMsg(Class<?> clazz, Exception e) {
        String s = null;
        if (!e.getClass().getSimpleName().equals("BizException") && !e.getClass().getSimpleName().equals("ParamException")) {
            s = String.format("[系统异常]，产生类：%s,具体堆栈信息:%s", new Object[]{clazz.getName(), ExceptionUtils.getFullStackTrace(e)});
        } else {
            s = String.format("[系统业务异常]，产生类：%s,具体信息:%s", new Object[]{clazz.getName(), ExceptionUtils.getFullStackTrace(e)});
        }
        logger.error(s);
    }

    /**
     * 比较两个日期是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static boolean sameDate(Date d1, Date d2) {

        if(null == d1 && null == d2){
            return  true;
        }

        if (null == d1 || null == d2)
            return false;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        return cal1.getTime().equals(cal2.getTime());

    }

    /**
     * 记录可追溯的日志
     * @param beforeAmount  事件发生前的金额
     * @param afterAmount  事件发生后的金额
     * @param beforeStatus 事件发生前的状态
     * @param afterStatus 事件发生后的状态
     * @param beforeObject 事件发生前的对象
     * @param afterObject 事件发生后的对象
     * @param ebsOrderId EBS订单编号
     * @param eventDate 事件发生日期
     * @param eventId  事件ID
     * @param payCode 支付好
     * @param moneyFeedOrderId 商场订单ID
     * @param operatuin 对应的事件类型
     * @param comment 备注
     */
    public void saveFulllog(BigDecimal beforeAmount, BigDecimal afterAmount, String beforeStatus, String afterStatus,
                            Object beforeObject, Object afterObject, String ebsOrderId, Date eventDate, Long eventId,
                            String payCode, Long moneyFeedOrderId,
                            UserOperEventType userOperEventType, UserOperationEnums operatuin,String comment, UserOperSourceType source){
        UserOperationLogDtoReq req =new UserOperationLogDtoReq();
        req.setAfterEventAmount(afterAmount);
        req.setBeforeEventAmount(beforeAmount);
        req.setBeforeEventStatus(beforeStatus);
        req.setAfterEventStatus(afterStatus);
        if(beforeObject!=null) {
            req.setBeforeEventModel(JSON.toJSONString(beforeObject));
        }
        if(afterObject!=null) {
            req.setAfterEventModel(JSON.toJSONString(afterObject));
        }

        req.setEbsOrderId(ebsOrderId); //TODO
        //req.setDataStatus();//TODO
        req.setEventDate(eventDate);
        req.setEventId(eventId);
        req.setPayCode(payCode);
        req.setOrderId(moneyFeedOrderId);
        req.setSource(source);//TODO
        req.setUserOperationEnums(operatuin);
        req.setUserOperEventType(userOperEventType); //操作类型
        req.setComment(comment);

        try {
            commonFeignClient.recordingUserOperation(req);
        }
        catch (Exception e){
            CommonService.formatExceptionMsg(this.getClass(),e);
        }

    }
    
    
    /**
	 * 给当前key加锁
	 * @param key
	 */
	public void addLock(String key){
		Object keyValue = null;
		try {
			keyValue = cacheData.getDataCache().get(key);
		} catch (Exception e) {
			formatExceptionMsg(this.getClass(), e);
			throw new BizException("获取rediskey失败，请稍后再试");
		}
		// 生成存栏单验证
		if (keyValue != null) {
			throw new BizException("充值中，请稍后再试");
		}
		try {
			cacheData.getDataCache().set(key, "true");
		} catch (Exception e) {
			formatExceptionMsg(this.getClass(), e);
			throw new BizException("生成缓存信息失败，key["+ key +"]，请稍后再试");
		}
	}
	
	/**
	 * 批次释放锁
	 * @param key
	 */
	public void removeLock(String key){
		try {
			cacheData.getDataCache().remove(key);
		} catch (Exception e) {
			formatExceptionMsg(this.getClass(), e);
			throw new BizException("移除缓存信息失败，key["+ key +"]，请稍后再试");
		}
	}

	public void sendToOrderPayOrChargeMqResult(String ebsOrderNo,
                                             String payType,
                                             Boolean payResultFlag,
                                             String payResultMsg,
                                             String orgId,
                                             String moneyfeedPayNo,
                                             MQMsgKindEnum msgType){
        PayResultDtoReq payResult = new PayResultDtoReq();

        payResult.setEbsorderNo(ebsOrderNo);
        payResult.setPayType(payType);
        payResult.setPayFlag(payResultFlag);
        payResult.setMsg(payResultMsg);
        payResult.setOrgId(orgId);
        payResult.setMoneyfeedPayNo(moneyfeedPayNo);

        SendMQCommonModel mq = new SendMQCommonModel();
        mq.setMqMsgKind(msgType);
        mq.setMqSendToClient(MQSendToClientEnum.order);
        mq.setData(payResult);

        sendMQService.sendMq(mq);
    }

}
