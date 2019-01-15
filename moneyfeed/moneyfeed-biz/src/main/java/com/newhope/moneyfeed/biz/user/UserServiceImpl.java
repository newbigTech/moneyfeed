package com.newhope.moneyfeed.biz.user;

import com.alibaba.fastjson.JSONObject;
import com.newhope.moneyfeed.api.dto.request.user.UserLoginDtoReq;
import com.newhope.moneyfeed.api.dto.request.user.UserRemoveDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.response.LoginDtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.ThirdSourceEnums;
import com.newhope.moneyfeed.api.service.wechat.UserService;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.util.MD5Util;
import com.newhope.moneyfeed.common.util.wechat.WechatUtil;
import com.newhope.moneyfeed.biz.wechat.WechatService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liming on 2018/11/17.
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private WechatService wechatService;

    @Autowired
    private RSessionCache rSessionCache;


    public LoginDtoResult login(UserLoginDtoReq request){

        LoginDtoResult result = null;
        result = this.loginByOauth(request);
        return result;

    }


    /**
     * OAUTH授权方式登录
     * @param
     * @return
     */
    @Override
    public LoginDtoResult loginByOauth(UserLoginDtoReq request) {
        LoginDtoResult result = new LoginDtoResult();
        //根据code取token
        JSONObject jsonObject = null;
        //根据thirdSource调用不同三方工具获取对应的openid
        if (ThirdSourceEnums.WECHAT.name().equals(request.getThirdSource())) {	//三方账户为微信
            jsonObject = wechatService.getAccessTokenForAuth(request.getAuthcode(), request.getAppCode(), request.getThirdSource());
            if (jsonObject == null) {
                //需要跳转到错误页面
                logger.error("[WechatOpenController.getcode]没有取得微信用户信息");
                result.setCode(ResultCode.THIRD_AUTH_FAIL.getCode());
                result.setMsg(ResultCode.THIRD_AUTH_FAIL.getDesc());
                return result;
            } else if (StringUtils.isNotEmpty(jsonObject.getString("errcode"))) {
                logger.error("[WechatOpenController.getcode]调用微信失败:" + jsonObject.toString());
                result.setCode(ResultCode.THIRD_AUTH_FAIL.getCode());
                result.setMsg(ResultCode.THIRD_AUTH_FAIL.getDesc());
                return result;
            }
            String token = "";
            try {
                token = wechatService.getToken();
            } catch (Exception e) {
                logger.error("[UserService.loginByOauth]获取token异常", e);
                result.setCode(ResultCode.FAIL.getCode());
                result.setMsg(ResultCode.FAIL.getDesc());
                return result;
            }
            JSONObject userJson = WechatUtil.getUserInfo(token, jsonObject.getString("openid"), request.getAppCode());
            if(null == userJson){
                result.setCode(ResultCode.FAIL.getCode());
                result.setMsg("未获取到微信用户信息");
                return result;
            }
            result.setThirdAccount(jsonObject.getString("openid"));
            result.setThirdImg(userJson.getString("headimgurl"));
            result.setThirdNickName(userJson.getString("nickname"));
            //0未关注，1关注
            result.setSubscribeFlag(userJson.getString("subscribe"));
        }
            result.setMsg(ResultCode.SUCCESS.getDesc());
            result.setCode(ResultCode.SUCCESS.getCode());
            return result;
        }

    @Override
    public DtoResult loginInfoRemove(UserRemoveDtoReq userRemoveDtoReq) {
            userRemoveDtoReq.getUserIds().stream().forEach(userId ->{
                String token = MD5Util.sign(userId  + userRemoveDtoReq.getThirdSource(), CommonConstant.salt, "utf-8");
                boolean flag = rSessionCache.removeCache(token);
                if(!flag){
                    logger.error("用户id为[{}]的用户移除失败",userId);
                }
            });
        return DtoResult.success();
    }
}



