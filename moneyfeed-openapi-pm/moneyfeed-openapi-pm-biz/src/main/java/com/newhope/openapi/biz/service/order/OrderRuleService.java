package com.newhope.openapi.biz.service.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderRuleDetailDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmUserDtoResult;
import com.newhope.openapi.api.vo.request.order.OrderRuleQueryReq;
import com.newhope.openapi.api.vo.request.order.OrderRuleReq;
import com.newhope.openapi.api.vo.request.order.OrderRuleUpdateReq;
import com.newhope.openapi.api.vo.response.order.OrderRuleDetailResult;
import com.newhope.openapi.biz.rpc.feign.order.OrderRuleFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 19:00
 */
@Service
public class OrderRuleService {
    @Autowired
    OrderRuleFeignClient orderRuleFeignClient;
    @Autowired
    RSessionCache rSessionCache;
    /**    
     * 
     *
     * @author: dongql  
     * @date: 2018/11/21 15:14  
     * @param req
     * @return: com.newhope.openapi.api.vo.response.order.OrderRuleDetailResult  
     */  
    public OrderRuleDetailResult queryOrderRuleDetail(OrderRuleQueryReq req) {
        OrderRuleDetailResult result = new OrderRuleDetailResult();
        OrderRuleQueryDtoReq dtoReq = new OrderRuleQueryDtoReq();
        BeanUtils.copyProperties(req, dtoReq);
        if (req.getUcShopId() == null) {
            Object obj = rSessionCache.getUserInfo();
            if(obj == null ){
                result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
                result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
                return result;
            }
            dtoReq.setUcShopId(((UcPmUserDtoResult) obj).getShopId());
        }
        BaseResponse<OrderRuleDetailDtoResult> response = orderRuleFeignClient.queryOrderRuleDetail(dtoReq);
        if (ResultCode.SUCCESS.getCode().equals(response.getCode()) && null != response.getData()) {
           BeanUtils.copyProperties(response.getData(), result);
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        return result;
    }

    /**    
     * 
     *
     * @author: dongql  
     * @date: 2018/11/21 15:14
     * @param req  
     * @return: com.newhope.moneyfeed.api.vo.response.Result  
     */  
    public Result editOrderRule(OrderRuleUpdateReq req) throws BizException {
        Result result = new Result();
        OrderRuleUpdateDtoReq dtoReq = new OrderRuleUpdateDtoReq();
        BeanUtils.copyProperties(req, dtoReq);
        Object obj = rSessionCache.getUserInfo();
        if (obj == null) {
            result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
            result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
            return result;
        }
        dtoReq.setOperUserId(((UcPmUserDtoResult) obj).getId());
        dtoReq.setOperUserName(((UcPmUserDtoResult) obj).getName());
        BaseResponse<DtoResult> response = orderRuleFeignClient.editOrderRule(dtoReq);
        BeanUtils.copyProperties(response, result);
        return result;
    }


    /**
     * 新增订单规则
     * @param req
     * @return
     */
    public Result saveOrderRule(OrderRuleReq req)  {
        Result result = new Result();
        OrderRuleDtoReq dtoReq = new OrderRuleDtoReq();
        BeanUtils.copyProperties(req, dtoReq);
        Object obj = rSessionCache.getUserInfo();
        if (obj == null) {
            result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
            result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
            return result;
        }

        dtoReq.setOperUserId(((UcPmUserDtoResult) obj).getId());
        dtoReq.setOperUserName(((UcPmUserDtoResult) obj).getName());
        BaseResponse<DtoResult> response = orderRuleFeignClient.saveOrderRule(dtoReq);
        BeanUtils.copyProperties(response, result);
        return result;
    }

}
