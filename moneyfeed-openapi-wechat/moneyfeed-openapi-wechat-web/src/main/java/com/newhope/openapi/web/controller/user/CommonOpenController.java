package com.newhope.openapi.web.controller.user;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.cache.RSmsCache;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.util.ContextHolderUtil;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopCustomerPropertyQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopCustomerPropertyListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.UserVisitShopDtoResult;
import com.newhope.moneyfeed.user.api.enums.client.AllowOnlineBusinessEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerPropertyTypeEnums;
import com.newhope.openapi.api.rest.user.CommonOpenAPI;
import com.newhope.openapi.biz.rpc.feign.user.ShopFeignClient;
import com.newhope.openapi.biz.service.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommonOpenController extends AbstractController implements CommonOpenAPI {
    private static final Logger logger = LoggerFactory.getLogger(CommonOpenController.class);
    @Autowired
    private RSmsCache rSmsCache;
    @Autowired
    private RSessionCache rSessionCache;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopFeignClient shopFeignClient;

    @Override
    public BaseResponse<Result> validateSMSCode(@RequestParam(value = "phone") String phone, @RequestParam(value = "code") String code) {
        try {
            Preconditions.checkState(code.equals(rSmsCache.getSmsCode(phone)));
            return buildJson(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc());
        } catch (Exception e) {
            logger.error("获取短信验证码出错");
            throw new BizException(ResultCode.BASE_SMS_CODE_ERROR);
        }
    }

    @Override
    public BaseResponse<Result> loginForTest(@RequestParam("key") String key, @RequestParam("userId") Long userId) {
        Long shopId = 1L;
        if (key.equals("78hSoGVVE38QxsDOcjKPCaeu1kN3x5Z2")) {
            userService.cacheUserAggrInfo(userId, "web",null);
            Result result = new Result();
            ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
            if (userInfo == null) {
                result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
                result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
                return buildJson(result);
            }
            List<UcShopModel> shopList = userInfo.getCustomer().getShopList();
            for (UcShopModel shop : shopList) {
                if (shop.getId().equals(shopId)) {
                    UserVisitShopDtoResult visitShop = new UserVisitShopDtoResult();
                    visitShop.setShop(shop);
                    ShopCustomerPropertyQueryDtoReq propertyParam = new ShopCustomerPropertyQueryDtoReq();
                    propertyParam.setCustomerId(userInfo.getCustomer().getCustomer().getId());
                    propertyParam.setShopId(shopId);
                    BaseResponse<ShopCustomerPropertyListResult> propertyResult = shopFeignClient.queryShopCustomerProperty(propertyParam);
                    Map<String, UcShopCustomerPropertyModel> propertysMap = Maps.newHashMap();
                    if (propertyResult.isSuccess()) {
                        if (CollectionUtils.isNotEmpty(propertyResult.getData().getPropertys())) {
                            for (UcShopCustomerPropertyModel property : propertyResult.getData().getPropertys()) {
                                if (CustomerPropertyTypeEnums.ALLOW_ONLINE_BUSINESS.getValue().equals(property.getName())) {
                                    if (AllowOnlineBusinessEnums.UN_ALLOW.getValue().equals(property.getDetail())) {
                                        result.setCode(ResultCode.SHOP_COLSE_CUSTOMER_BUSINESS.getCode());
                                        result.setMsg(ResultCode.SHOP_COLSE_CUSTOMER_BUSINESS.getDesc());
                                        return buildJson(result);
                                    }
                                }
                                propertysMap.put(property.getName(), property);
                            }
                        }
                    }
                    visitShop.setShopCustomerRules(propertysMap);
                    userInfo.setVisitShop(visitShop);
                    rSessionCache.setUserInfo(ContextHolderUtil.getResponse(), userInfo, String.valueOf(userInfo.getUser().getId()), CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
                    visitShop.setShopCustomerRules(propertysMap);
                    userInfo.setVisitShop(visitShop);
                }
            }

            rSessionCache.setUserInfo(ContextHolderUtil.getResponse(), userInfo, String.valueOf(userInfo.getUser().getId()), CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
            return buildJson(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc());
        } else {
            return buildJson(ResultCode.FAIL.getCode(), "key不正确");
        }
    }
}
