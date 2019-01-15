package com.newhope.moneyfeed.biz.sms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.newhope.commons.lang.env.EnvUtils;
import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.sms.SmsVerdictDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.service.sms.SmsService;
import com.newhope.moneyfeed.biz.common.BizConstant;
import com.newhope.moneyfeed.common.cache.RSmsCache;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.common.util.SmsCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 短信服务
 *
 * @author: zhangyanyan
 * @date: 2018/11/19
 */
@Service
public class SmsServiceImpl implements SmsService {
    private final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    //测试人员手机号，测试环境与UAT环境需要能接收短信
    private static final List<String> TEST_MOBILE_LIST = Arrays.asList("18980517838","17280082720",
    		"18980972692","13980062889","18161179767","15982372673","18382281714");

    @Value("${ali.sms.product}")
    private String product;
    @Value("${ali.sms.domain}")
    private String domain;
    @Value("${ali.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${ali.sms.accessKeySecret}")
    private String accessKeySecret;

    @Autowired
    RSmsCache rSmsCache;

    /**
     * 发送短信
     *
     * @param dtoReq
     * @return
     */
    @Override
    public DtoResult sendSms(SmsSendDtoReq dtoReq) {
        DtoResult dtoResult = DtoResult.success();
        try {
            String authCode = StringUtils.EMPTY;
            if (dtoReq.isAuthCode()) {
                checkSms(dtoReq);
                authCode = SmsCodeUtil.getSmsCode();
                Map<String, String> paramMap = null == dtoReq.getParamMap() ? new HashMap<>() : dtoReq.getParamMap();
                paramMap.put("smsCode", authCode);
                dtoReq.setParamMap(paramMap);
                logger.info("{}:短信验证码:{}", dtoReq.getMobile(), authCode);
            }
            //发送阿里云短信
            sendAliSms(dtoReq);

            // 发送次数设置cache
            String cacheCountKey = wrapCacheCountKey(dtoReq.getMobile(), dtoReq.getTemplateId());
            rSmsCache.setSmsCodeCount(rSmsCache.getSmsCodeCount(cacheCountKey), cacheCountKey);
            // 设置时间到cache
            rSmsCache.setSmsCodeTimeout(dtoReq.getMobile(), new Date());
            // 验证码保存进cache
            if (dtoReq.isAuthCode()) {
                rSmsCache.setSmsWithTimeout(dtoReq.getMobile(), authCode,15*60);
                if (!EnvUtils.production()) { //非生产环境，返回验证码
                    dtoResult.setData(authCode);
                }
            }
            return dtoResult;
        } catch (BizException ex) {
            return DtoResult.fail(ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            logger.error("发送短信异常：", ex);
            return DtoResult.fail(ResultCode.BASE_SMS_SEND_FAILED);
        }
    }


    private void sendAliSms(SmsSendDtoReq dtoReq) throws Exception {
        SendSmsResponse sendSmsResponse = null;
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(dtoReq.getMobile());
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("料你富");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(dtoReq.getTemplateId());
        request.setTemplateParam(JSON.toJSONString(dtoReq.getParamMap()));
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        if (EnvUtils.production() || testMobileExists(dtoReq.getMobile())) {
            // hint 此处可能会抛出异常，注意catch
            sendSmsResponse = acsClient.getAcsResponse(request);
        }
        logger.info("[sendSmsAli->>阿里云短信：] {} : {} : {}, 返回：{}", dtoReq.getTemplateId(), dtoReq.getMobile(),
                JSON.toJSONString(dtoReq.getParamMap()), JSON.toJSONString(sendSmsResponse));
    }

    /**
     * 校验短信能否再次发送
     *
     * @param dtoReq
     * @throws BizException
     */
    private void checkSms(SmsSendDtoReq dtoReq) throws BizException {
        int count = 0;
        try {
            String cacheCuntKey = wrapCacheCountKey(dtoReq.getMobile(), dtoReq.getTemplateId());
            count = rSmsCache.getSmsCodeCount(cacheCuntKey);
        } catch (Exception e) {
            logger.error("获取短信缓存异常", e);
            throw new BizException(ResultCode.BASE_MQ_SEND_FAILED.getCode(), "获取短信缓存异常");
        }

        //校验短信发送次数
        if (count > BizConstant.DEFAULT_SMS_SEND_MAX_COUNT) {
            logger.error("发送短信失败，{}每天最多发{}次短信", dtoReq.getMobile(), BizConstant.DEFAULT_SMS_SEND_MAX_COUNT);
            throw new BizException(ResultCode.BASE_SMS_SEND_FAILED_MORE_MAX_COUNT);
        }
        //短信验证码需要校验短信发送间隔时间
        if (!dtoReq.isAuthCode()) {
            return;
        }
        Date timeoutDate = null;
        try {
            timeoutDate = rSmsCache.getSmsCodeTimeout(dtoReq.getMobile());
        } catch (Exception e) {
            logger.error("获取短信缓存异常", e);
            throw new BizException(ResultCode.BASE_MQ_SEND_FAILED.getCode(), "获取短信缓存异常");
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(new Date());
        //校验短信发送间隔
        int diff = 0;
        if (timeoutDate == null) {
            diff = BizConstant.DEFAULT_SMS_SEND_TIME_INTERVAL;
        } else {
            c2.setTime(timeoutDate);
            diff = DateUtils.dateDiff('s', c1, c2);
        }
        if (diff < BizConstant.DEFAULT_SMS_SEND_TIME_INTERVAL) {
            logger.error("{}秒内只允许发一次验证码短信:{}-{}", BizConstant.DEFAULT_SMS_SEND_TIME_INTERVAL, dtoReq.getMobile(), dtoReq.getTemplateId());
            throw new BizException(ResultCode.BASE_SMS_SEND_FAILED.getCode(), BizConstant.DEFAULT_SMS_SEND_TIME_INTERVAL + "秒内只允许发一次验证码短信");
        }
    }

    /**
     * 拼接缓存短信发送次数KEY，手机号+模板类型
     * 背景：不同类型的短信模板可发送最大数量的短信不同
     *
     * @param mobile
     * @param templateType
     * @return
     */
    private String wrapCacheCountKey(String mobile, String templateType) {
        return mobile + "_" + templateType;
    }

    /**
     * 验证短信验证码
     *
     * @param dtoReq
     * @return
     */
    @Override
    public DtoResult verdictSmsCode(SmsVerdictDtoReq dtoReq) {
        try {
            String cachedCode = rSmsCache.getSmsCode(dtoReq.getMobile());
            if (null == cachedCode || !dtoReq.getSmsCode().equals(cachedCode)) {
                return DtoResult.fail(ResultCode.BASE_SMS_CODE_ERROR);
            }
            return DtoResult.success();
        } catch (Exception e) {
            logger.error("校验短信验证码异常：", e);
            return DtoResult.fail(ResultCode.BASE_SMS_CODE_ERROR);
        }
    }
    
    /**
     * 检查是否为测试手机号
     * @param mobile
     * @dateTime : 2018年12月11日下午2:45:56
     */
    private boolean testMobileExists(String mobile){
    	
    	if(StringUtils.isNotBlank(mobile)){
    		String [] mobiles = mobile.split(",");
    		if(mobile.length() > 1){
    			for(String m : mobiles){
    				if(!TEST_MOBILE_LIST.contains(m))
    					return false;
    			}
    			return true;
    		}
    		return TEST_MOBILE_LIST.contains(mobile);
    	}
    	return false;
    }
    
}
