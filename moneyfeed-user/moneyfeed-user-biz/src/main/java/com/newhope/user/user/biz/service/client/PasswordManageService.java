package com.newhope.user.user.biz.service.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.newhope.commons.lang.MD5Utils;
import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.SmsTemplateEnums;
import com.newhope.moneyfeed.api.enums.wechat.WechatMsgTypeEnums;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.cache.RSmsCache;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserThirdAccountModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerPayModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.PasswordManageDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.PayPasswordVerifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.PasswordManageDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.PasswordQueryDtoResult;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcCustomerPayDao;
import com.newhope.user.user.biz.rpc.feign.base.SmsFeignClient;
import com.newhope.user.user.biz.rpc.feign.base.WechatMsgFeignClient;
import com.newhope.user.user.biz.service.BaseService;

@Service
public class PasswordManageService extends BaseService<UcCustomerPayModel> {
    private static final Logger logger = LoggerFactory.getLogger(PasswordManageService.class);
    @Autowired
    private UcCustomerPayDao payDao;
    @Autowired
    private UcCustomerClientUserMappingService ucCustomerClientUserMappingService;
    @Autowired
    private UcClientUserService clientUserService;
    @Autowired
    private UcCustomerService customerService;
    @Autowired
    private UcClientUserThirdAccountService thirdAccountService;
    @Autowired
    private RSmsCache rSmsCache;
    @Autowired
    private CacheData cacheData;
    @Autowired
	SmsFeignClient smsFeignClient;
    @Autowired
    WechatMsgFeignClient wechatMsgFeignClient;
    private static String SYS_PHONE="028-68308294";
    
    public DtoResult save(PasswordManageDtoReq request) {
        checkPhoneCode(request);
        List<UcCustomerClientUserMappingModel> model = getUcCustomerClientUserMappingModels(request);
        if (model.isEmpty()) {
            return PasswordManageDtoResult.fail(ResultCode.USER_CUSTOMER_NOT_EXIST);
        }
        checkExist(model.get(0));
        checkCardNum(model.get(0).getCustomerId(), request.getCardNo());
        UcCustomerPayModel payModel = wrap(request, model);
        if (super.save(Lists.newArrayList(payModel))) {
        	//发送短信通知用户
        	sendMsgToUser(request.getUserId(),request.getPhone());
            return DtoResult.success();
        } else {
            return DtoResult.fail(ResultCode.FAIL);
        }
    }

    public DtoResult update(PasswordManageDtoReq request) {
        checkPhoneCode(request);
        if (!checkUserExist(request)) {
            return DtoResult.fail(ResultCode.USER_NOT_EXIST);
        }
        List<UcCustomerClientUserMappingModel> model = getUcCustomerClientUserMappingModels(request);
        if (model.isEmpty()) {
            return DtoResult.fail(ResultCode.USER_CUSTOMER_NOT_EXIST);
        }

        UcCustomerPayModel conditions = new UcCustomerPayModel();
        conditions.setCustomerId(model.get(0).getCustomerId());
        UcCustomerPayModel payModel = wrap(request, model);
        checkCardNum(model.get(0).getCustomerId(), request.getCardNo());
        if (super.update(conditions, payModel)) {
        	//发送短信通知用户
        	sendMsgToUser(request.getUserId(),request.getPhone());
        	//重试失败次数
        	String cacheKey = DateUtil.getDateStr(new Date(), DateUtil.YYYYMMDD)+request.getUserId();
        	try {
        		cacheData.getDataCache().set(cacheKey, 0, 24*3600);
        	} catch (Exception e) {
        		throw new BizException(ResultCode.FAIL.getCode(), "获取缓存出错");
    		}
            return DtoResult.success();
        } else {
            return DtoResult.fail(ResultCode.FAIL);
        }
    }

	public void sendMsgToUser(Long userId, String mobile) {
		try {
			SmsSendDtoReq smsDto = new SmsSendDtoReq();
			smsDto.setAuthCode(false);
			smsDto.setMobile(mobile);
			smsDto.setTemplateId(SmsTemplateEnums.SMS_152210313.getTemplateId());
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("sysPhone", SYS_PHONE);
			smsDto.setParamMap(paramMap);
			smsFeignClient.sendSms(smsDto);
			UcClientUserThirdAccountModel accountParam = new UcClientUserThirdAccountModel();
			accountParam.setUserId(userId);
			List<UcClientUserThirdAccountModel> accountList = thirdAccountService.query(accountParam);
			if (CollectionUtils.isNotEmpty(accountList)) {
				WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
				wechatMsgDtoReq.setOpenid(accountList.get(0).getThirdAccount());
				wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.MODIFY_PAY_PWD_SUCCESS);
				List<String> params = new ArrayList<String>();
				params.add(SYS_PHONE);
				wechatMsgDtoReq.setParams(params);
				wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
			}
		} catch (Exception e) {
			logger.error("修改支付密码成功发送消息失败"+e.getMessage());
		}
	}
    
    public PasswordQueryDtoResult query(Long userId) {
        UcCustomerClientUserMappingModel params = new UcCustomerClientUserMappingModel();
        params.setClientUserId(userId);
        final List<UcCustomerClientUserMappingModel> userMappingModels = ucCustomerClientUserMappingService.getDao().select(params);
        if (!userMappingModels.isEmpty()) {
            UcCustomerClientUserMappingModel model = userMappingModels.get(0);
            UcCustomerPayModel query = new UcCustomerPayModel();
            query.setCustomerId(model.getCustomerId());
            PasswordQueryDtoResult result = new PasswordQueryDtoResult();
            result.setPhone(model.getClientUserMobile());
            result.setCode(ResultCode.SUCCESS.getCode());
            final List<UcCustomerPayModel> select = payDao.select(query);
            if (CollectionUtils.isNotEmpty(select)) {
                result.setHasPassword(true);
                try {
                    result.setHexPassword(MD5Utils.encodeMD5Hex(MD5Utils.encodeMD5Hex(select.get(0).getPassword())));
                } catch (Exception e) {
                    throw new BizException(ResultCode.FAIL.getCode(), "密码加密出错");
                }
                return result;
            } else {
                result.setHasPassword(false);
                return result;
            }
        } else {
            return PasswordQueryDtoResult.fail(ResultCode.USER_CUSTOMER_NOT_EXIST);
        }
    }

    public DtoResult verify(PayPasswordVerifyDtoReq request){
    	DtoResult result = new DtoResult();
    	//判断用户是否已经重试够数了
    	Integer retryCount = 0;
    	String cacheKey = DateUtil.getDateStr(new Date(), DateUtil.YYYYMMDD)+request.getUserId();
    	try {
    		Integer count = (Integer) cacheData.getDataCache().get(cacheKey);
    		if(count!=null){
    			retryCount = count;
    			if(retryCount>=CommonConstant.USER_RETRY_PAY_PWD_COUNT){
    				result.setCode(ResultCode.USER_PAY_RETRY_ERROR.getCode());
                	result.setMsg(ResultCode.USER_PAY_RETRY_ERROR.getDesc());
                	return result;
    			}
    		}
    	} catch (Exception e) {
    		throw new BizException(ResultCode.FAIL.getCode(), "获取缓存出错");
		}
    	UcCustomerClientUserMappingModel params = new UcCustomerClientUserMappingModel();
        params.setClientUserId(request.getUserId());
        final List<UcCustomerClientUserMappingModel> userMappingModels = ucCustomerClientUserMappingService.getDao().select(params);
        if (CollectionUtils.isNotEmpty(userMappingModels)) {
            UcCustomerClientUserMappingModel model = userMappingModels.get(0);
            UcCustomerPayModel query = new UcCustomerPayModel();
            query.setCustomerId(model.getCustomerId());
            final List<UcCustomerPayModel> select = payDao.select(query);
            if (CollectionUtils.isNotEmpty(select)) {
            	try {
            		String pwd = MD5Utils.encodeMD5Hex(MD5Utils.encodeMD5Hex(request.getPassword()));
            		if(select.get(0).getPassword().equals(pwd)){
            			cacheData.getDataCache().set(cacheKey, 0, 24*3600);
            			result.setCode(ResultCode.SUCCESS.getCode());
                    	result.setMsg(ResultCode.SUCCESS.getDesc());
                    	return result;
            		}
                } catch (Exception e) {
                    throw new BizException(ResultCode.FAIL.getCode(), "系统出错");
                }
            	retryCount+=1;
            	try {
					cacheData.getDataCache().set(cacheKey, retryCount, 24*3600);
				} catch (Exception e) {
					throw new BizException(ResultCode.FAIL.getCode(), "获取缓存出错");
				}
            	result.setData(retryCount);
            	result.setCode(ResultCode.USER_PAY_PASS_ERROR.getCode());
            	result.setMsg(ResultCode.USER_PAY_PASS_ERROR.getDesc());
            }else{
            	result.setCode(ResultCode.USER_PAY_PASS_UN_EXIST.getCode());
            	result.setMsg(ResultCode.USER_PAY_PASS_UN_EXIST.getDesc());
            }
        } else {
        	result.setCode(ResultCode.USER_CUSTOMER_NOT_EXIST.getCode());
        	result.setMsg(ResultCode.USER_CUSTOMER_NOT_EXIST.getDesc());
        }
        return result;
    }
    private boolean checkUserExist(PasswordManageDtoReq request) {
        UcClientUserModel userParams = new UcClientUserModel();
        userParams.setId(request.getUserId());
        return !clientUserService.getDao().select(userParams).isEmpty();
    }

    private void checkExist(UcCustomerClientUserMappingModel userMappingModel) {
        UcCustomerPayModel conditions = new UcCustomerPayModel();
        conditions.setCustomerId(userMappingModel.getCustomerId());
        if (!payDao.select(conditions).isEmpty()) {
            throw new BizException(ResultCode.USER_PAYMENT_PASSWORD_EXIST);
        }
    }

    private void checkPhoneCode(PasswordManageDtoReq request) {
        try {
            Preconditions.checkState(request.getCode().equals(rSmsCache.getSmsCode(request.getPhone())));
        } catch (Exception e) {
            logger.error("获取短信验证码出错");
            throw new BizException(ResultCode.BASE_SMS_CODE_ERROR);
        }
    }

    private void checkCardNum(Long customerId, String cardNo) {
        try {
            CustomerQueryDtoReq queryParam = new CustomerQueryDtoReq();
            queryParam.setId(customerId);
            CustomerDtoListResult customerDtoListResult = customerService.searchCustomer(queryParam);
            if (!customerDtoListResult.getCustomers().isEmpty()) {
                Preconditions.checkState(customerDtoListResult.getCustomers().get(0).getCardNum().equals(cardNo));
            }
        } catch (Exception e) {
            throw new BizException(ResultCode.USER_CARD_ERROR);
        }
    }

    /**
     * 先查询用户对应的客户关系
     */
    private List<UcCustomerClientUserMappingModel> getUcCustomerClientUserMappingModels(PasswordManageDtoReq request) {
        UcCustomerClientUserMappingModel params = new UcCustomerClientUserMappingModel();
        params.setClientUserId(request.getUserId());
        params.setEnable(true);
        return ucCustomerClientUserMappingService.getDao().select(params);
    }

    private UcCustomerPayModel wrap(PasswordManageDtoReq request, List<UcCustomerClientUserMappingModel> model) {
        UcCustomerPayModel payModel = new UcCustomerPayModel();
        payModel.setCustomerId(model.get(0).getCustomerId());
        try {
            payModel.setPassword(MD5Utils.encodeMD5Hex(MD5Utils.encodeMD5Hex(request.getPassword())));
        } catch (Exception e) {
            throw new BizException(ResultCode.FAIL.getCode(), "密码加密出错");
        }
        return payModel;
    }

    @Override
    protected BaseDao<UcCustomerPayModel> getDao() {
        return payDao;
    }
}
