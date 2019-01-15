package com.newhope.order.biz.service.impl;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.order.api.bean.OrderRuleModel;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderRuleDetailDtoResult;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.moneyfeed.order.dal.dao.OrderRuleDao;
import com.newhope.order.biz.service.OrderRuleService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 16:44
 */
@Service
public class OrderRuleServiceImpl extends BaseServiceImpl<OrderRuleModel> implements OrderRuleService {
    public static Logger logger = LoggerFactory.getLogger(OrderRuleServiceImpl.class);
    @Autowired
    OrderRuleDao orderRuleDao;

    @Override
    protected BaseDao<OrderRuleModel> getDao() {
        return orderRuleDao;
    }


    @Override
    public OrderRuleDetailDtoResult queryOrderRuleDetail(OrderRuleQueryDtoReq req) {
        OrderRuleDetailDtoResult result = new OrderRuleDetailDtoResult();
        OrderRuleModel model = new OrderRuleModel();
        BeanUtils.copyProperties(req, model);
        List<OrderRuleModel> modelList = orderRuleDao.select(model);
        if(CollectionUtils.isNotEmpty(modelList)){
            BeanUtils.copyProperties(modelList.get(0), result);
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        return result;
    }

    @Override
    public DtoResult editOrderRule(OrderRuleUpdateDtoReq dto) throws BizException{
        DtoResult result = new DtoResult();
        OrderRuleModel olderModel = new OrderRuleModel();
        olderModel.setUcShopId(dto.getUcShopId());
        OrderRuleModel newModel = new OrderRuleModel();
        BeanUtils.copyProperties(dto, newModel);
        if(orderRuleDao.update(olderModel, newModel) < 1){
            result.setCode(ResultCode.OC_ORDER_RULE_UPDATE_FAIL.getCode());
            result.setMsg(ResultCode.OC_ORDER_RULE_UPDATE_FAIL.getDesc());
            return result;
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        return result;
    }

    @Override
    public DtoResult saveOrderRule(OrderRuleDtoReq dto)  {
        OrderRuleModel model = new OrderRuleModel();
        BeanUtils.copyProperties(dto, model);
        long count = orderRuleDao.insert(Collections.singletonList(model));
        if(count<=0){
            logger.error("创建订单规则失败");
            throw new BizException(ResultCode.FAIL.getCode(),"创建订单规则失败");
        }
        return DtoResult.success();
    }
}
