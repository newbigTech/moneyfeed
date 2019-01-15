package com.newhope.openapi.biz.service.order;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.balance.EbsBalanceDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDto;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDtoResult;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.OrderPayByEBSDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderValidateRepositoryDtoResult;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.dto.request.client.PayPasswordVerifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopCustomerPropertyQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.PasswordQueryDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopCustomerPropertyListResult;
import com.newhope.moneyfeed.user.api.enums.client.AllowOnlineBusinessEnums;
import com.newhope.moneyfeed.user.api.enums.client.CreditBalanceShowRuleEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerPropertyTypeEnums;
import com.newhope.openapi.api.vo.request.order.pay.OrderPayByEBSReq;
import com.newhope.openapi.api.vo.request.order.pay.PayPasswordVerifyReq;
import com.newhope.openapi.api.vo.response.order.pay.PayAccountInfoResult;
import com.newhope.openapi.api.vo.response.order.pay.PayByEBSValidateResult;
import com.newhope.openapi.biz.rpc.feign.order.OrderFeignClient;
import com.newhope.openapi.biz.rpc.feign.order.OrderIntegrationFeignClient;
import com.newhope.openapi.biz.rpc.feign.order.OrderPayFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.EbsCustomerBalanceFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.PasswordManageFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.ShopFeignClient;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
public class OrderPayService {

	private static final Logger logger = LoggerFactory.getLogger(OrderPayService.class);
	
	@Autowired
    private RSessionCache rSessionCache;
	
	@Autowired
	OrderPayFeignClient orderPayFeignClient;
	
	@Autowired
	OrderFeignClient orderFeignClient;
	
	@Autowired
	PasswordManageFeignClient passwordManageFeignClient;
	
	@Autowired
    private EbsCustomerBalanceFeignClient customerBalanceFeignClient;
	
	@Autowired
	private OrderIntegrationFeignClient orderIntegrationFeignClient;
	
	@Autowired
	private ShopFeignClient shopFeignClient;
	
	public PayAccountInfoResult getAccountInfo(Long orderId) {
		
		PayAccountInfoResult result = new PayAccountInfoResult();
		ClientUserCacheDtoResult user = getCurrentUser();
		//查询订单
		OrderInfoQueryDtoReq orderInfoQueryDtoReq = new OrderInfoQueryDtoReq();
		orderInfoQueryDtoReq.setId(orderId);
		BaseResponse<OrderListDtoResult> queryOrderResponse = orderFeignClient.queryOrderInfoList(orderInfoQueryDtoReq);
		if (!ResultCode.SUCCESS.getCode().equals(queryOrderResponse.getCode())){
			result.setCode(queryOrderResponse.getCode());
			result.setMsg(queryOrderResponse.getMsg());
			return result;
		}
		if (null == queryOrderResponse.getData() 
				|| CollectionUtils.isEmpty(queryOrderResponse.getData().getOrderList())) {
			result.setCode(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode());
			result.setMsg(ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
			return result;
		}
		OrderDtoResult orderDtoResult = queryOrderResponse.getData().getOrderList().get(0);
		result.setTotalPayAmount(orderDtoResult.getTotalPayAmount());
		result.setPlanTransportTime(orderDtoResult.getPlanTransportTime());
		result.setShopAddress(orderDtoResult.getUcShopAddress());
		
		//查询余额
		EbsBalanceDtoReq ebsBalanceDtoReq = new EbsBalanceDtoReq();
        ebsBalanceDtoReq.setCustomerCode(user.getCustomer().getCustomer().getEbsCustomerNum());
        ebsBalanceDtoReq.setOrgCode(String.valueOf(user.getVisitShop().getShop().getEbsOrgId()));
        logger.info("调用integration获取ebs余额请求参数：{}", JSON.toJSONString(ebsBalanceDtoReq));
        BaseResponse<EbsBalanceDtoResult> feignResult = customerBalanceFeignClient.ebsBalanceQuery(ebsBalanceDtoReq);
        if (!feignResult.isSuccess()) {
        	result.setCode(feignResult.getCode());
            result.setMsg(feignResult.getMsg());
            return result;
        }
        final List<EbsBalanceDto> dataList = feignResult.getData().getDataList();
        if (CollectionUtils.isNotEmpty(dataList)) {
        	result.setAvailableBalance(dataList.stream().map(EbsBalanceDto::getAvailableBalance).reduce(BigDecimal.ZERO, BigDecimal::add));
            result.setWhetherInOwe(dataList.get(0).getWhetherInOwe());
		}
        // 余额显示规则
        UcShopCustomerPropertyModel ucShopCustomerPropertyModel = user.getVisitShop().getShopCustomerRules().get(CustomerPropertyTypeEnums.CREDIT_BALANCE_SHOW_RULE.name());
        result.setShowRule(CreditBalanceShowRuleEnums.valueOf(ucShopCustomerPropertyModel.getValue()));
        // 查询是否支持线下交易
        boolean allowOfflineBusiness = false;
        ShopCustomerPropertyQueryDtoReq propertyParam = new ShopCustomerPropertyQueryDtoReq();
		propertyParam.setCustomerId(user.getCustomer().getCustomer().getId());
		propertyParam.setShopId(Long.valueOf(orderDtoResult.getShopId()));
		BaseResponse<ShopCustomerPropertyListResult> propertyResult =  shopFeignClient.queryShopCustomerProperty(propertyParam);
		if(propertyResult.isSuccess()){
			if( CollectionUtils.isNotEmpty( propertyResult.getData().getPropertys()) ){
				for( UcShopCustomerPropertyModel property : propertyResult.getData().getPropertys() ){
					if(CustomerPropertyTypeEnums.ALLOW_OFFLINE_BUSINESS.getValue().equals(property.getName())){
						if(AllowOnlineBusinessEnums.ALLOW.getValue().equals(property.getValue())){
							allowOfflineBusiness = true;
						}
						break;
					}
				}
			}
		}
		result.setAllowOfflineBusiness(allowOfflineBusiness);
        
        //是否需要充值
        if (result.getAvailableBalance().compareTo(result.getTotalPayAmount()) >= 0) {
        	result.setRechargeAmount(new BigDecimal(0));
		} else {
			result.setRechargeAmount(result.getTotalPayAmount().subtract(result.getAvailableBalance()));
		}
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        return result;
	}
	
	public DtoResult inventoryVerify(Long orderId){
		DtoResult result = DtoResult.success();
		
		OrderInfoQueryDtoReq orderInfoQueryDtoReq = new OrderInfoQueryDtoReq();
		orderInfoQueryDtoReq.setId(orderId);
		orderInfoQueryDtoReq.setPage(null);
		BaseResponse<OrderListDtoResult> orderInfoQueryResponse = orderFeignClient.queryOrderInfoList(orderInfoQueryDtoReq);
		if (!orderInfoQueryResponse.isSuccess()){
        	result.setCode(orderInfoQueryResponse.getCode());
            result.setMsg(orderInfoQueryResponse.getMsg());
			return result;
		}
		if (null == orderInfoQueryResponse.getData() || CollectionUtils.isEmpty(orderInfoQueryResponse.getData().getOrderList())) {
			result.setCode(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode());
			result.setMsg(ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
			return result;
		}
		OrderDtoResult orderDtoResult = orderInfoQueryResponse.getData().getOrderList().get(0);
		//拉料是今天，验证库存
		if (DateUtils.isSameDay(new Date(), orderDtoResult.getPlanTransportTime())) {
			BaseResponse<OrderValidateRepositoryDtoResult> repositoryFeignResult = orderIntegrationFeignClient.validateRepository(orderId);
	        if (!repositoryFeignResult.isSuccess()){
	        	result.setCode(repositoryFeignResult.getCode());
	            result.setMsg(repositoryFeignResult.getMsg());
				return result;
			}
	        OrderValidateRepositoryDtoResult orderValidateRepositoryDtoResult = repositoryFeignResult.getData();
	        if(orderValidateRepositoryDtoResult.getRepositoryLowFlag()){
	        	result.setCode(ResultCode.OC_ORDER_REPOSITORY_LOW.getCode());
	        	result.setMsg("库存不足,"+ JSON.toJSONString(orderValidateRepositoryDtoResult.getMaterialName())+",请修改拉料日期!");
	    		return result;
	    	}
		}

		return result;
	}
	public DtoResult passwordVerify(PayPasswordVerifyReq req) {
		DtoResult result = DtoResult.success();
		ClientUserCacheDtoResult user = getCurrentUser();
		//验证密码
		PayPasswordVerifyDtoReq payPasswordVerifyDtoReq = new PayPasswordVerifyDtoReq();
		payPasswordVerifyDtoReq.setUserId(user.getUser().getId());
		payPasswordVerifyDtoReq.setPassword(req.getPassword());
		BaseResponse<DtoResult> verifyPasswordResponse = passwordManageFeignClient.verify(payPasswordVerifyDtoReq);
		if (!ResultCode.SUCCESS.getCode().equals(verifyPasswordResponse.getCode())){
			result.setCode(verifyPasswordResponse.getCode());
			result.setMsg(verifyPasswordResponse.getMsg());
	        if (ResultCode.USER_PAY_PASS_ERROR.getCode().equals(verifyPasswordResponse.getCode())
	        		 && null != verifyPasswordResponse.getData()) {
	        	result.setData(CommonConstant.USER_RETRY_PAY_PWD_COUNT - (Integer)verifyPasswordResponse.getData().getData());
			}
			return result;
		}
		return result;
	}
	
	public DtoResult orderPayByEBS(OrderPayByEBSReq req) {
		DtoResult result = new DtoResult();
		ClientUserCacheDtoResult user = getCurrentUser();
		//验证密码
		PayPasswordVerifyDtoReq payPasswordVerifyDtoReq = new PayPasswordVerifyDtoReq();
		payPasswordVerifyDtoReq.setUserId(user.getUser().getId());
		payPasswordVerifyDtoReq.setPassword(req.getPassword());
		BaseResponse<DtoResult> verifyPasswordResponse = passwordManageFeignClient.verify(payPasswordVerifyDtoReq);
		if (!ResultCode.SUCCESS.getCode().equals(verifyPasswordResponse.getCode())){
			result.setCode(verifyPasswordResponse.getCode());
			result.setMsg(verifyPasswordResponse.getMsg());
	        if (ResultCode.USER_PAY_PASS_ERROR.getCode().equals(verifyPasswordResponse.getCode())
	        		 && null != verifyPasswordResponse.getData()) {
	        	result.setData(CommonConstant.USER_RETRY_PAY_PWD_COUNT - (Integer)verifyPasswordResponse.getData().getData());
			}
			return result;
		}
		//当天拉货验证库存
		OrderInfoQueryDtoReq orderInfoQueryDtoReq = new OrderInfoQueryDtoReq();
		orderInfoQueryDtoReq.setId(req.getOrderId());
		orderInfoQueryDtoReq.setPage(null);
		BaseResponse<OrderListDtoResult> orderInfoQueryResponse = orderFeignClient.queryOrderInfoList(orderInfoQueryDtoReq);
		if (!orderInfoQueryResponse.isSuccess()){
        	result.setCode(orderInfoQueryResponse.getCode());
            result.setMsg(orderInfoQueryResponse.getMsg());
			return result;
		}
		if (null == orderInfoQueryResponse.getData() || CollectionUtils.isEmpty(orderInfoQueryResponse.getData().getOrderList())) {
			result.setCode(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode());
			result.setMsg(ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
			return result;
		}
		OrderDtoResult orderDtoResult = orderInfoQueryResponse.getData().getOrderList().get(0);
		//拉料是今天，验证库存
		if (DateUtils.isSameDay(new Date(), orderDtoResult.getPlanTransportTime())) {
			BaseResponse<OrderValidateRepositoryDtoResult> repositoryFeignResult = orderIntegrationFeignClient.validateRepository(req.getOrderId());
	        if (!repositoryFeignResult.isSuccess()){
	        	result.setCode(repositoryFeignResult.getCode());
	            result.setMsg(repositoryFeignResult.getMsg());
				return result;
			}
	        OrderValidateRepositoryDtoResult orderValidateRepositoryDtoResult = repositoryFeignResult.getData();
	        if(orderValidateRepositoryDtoResult.getRepositoryLowFlag()){
	        	result.setCode(ResultCode.OC_ORDER_REPOSITORY_LOW.getCode());
	        	result.setMsg(ResultCode.OC_ORDER_REPOSITORY_LOW.getDesc() + ",请修改拉料日期:" + JSON.toJSONString(orderValidateRepositoryDtoResult.getMaterialName()));
	    		return result;
	    	}
		}
		
        //查询余额
        EbsBalanceDtoReq ebsBalanceDtoReq = new EbsBalanceDtoReq();
        ebsBalanceDtoReq.setCustomerCode(user.getCustomer().getCustomer().getEbsCustomerNum());
        ebsBalanceDtoReq.setOrgCode(String.valueOf(user.getVisitShop().getShop().getEbsOrgId()));
        logger.info("调用integration获取ebs余额请求参数：{}", JSON.toJSONString(ebsBalanceDtoReq));
        BaseResponse<EbsBalanceDtoResult> ebsBalanceFeignResult = customerBalanceFeignClient.ebsBalanceQuery(ebsBalanceDtoReq);
        if (!ebsBalanceFeignResult.isSuccess()){
        	result.setCode(ebsBalanceFeignResult.getCode());
            result.setMsg(ebsBalanceFeignResult.getMsg());
			return result;
		}
        final List<EbsBalanceDto> dataList = ebsBalanceFeignResult.getData().getDataList();
        
		//支付
		OrderPayByEBSDtoReq orderPayByEBSDtoReq = new OrderPayByEBSDtoReq();
		BeanUtils.copyProperties(req, orderPayByEBSDtoReq);
		orderPayByEBSDtoReq.setDeposit(dataList.stream().map(EbsBalanceDto::getDeposit).reduce(BigDecimal.ZERO, BigDecimal::add));
		
		BaseResponse<DtoResult> orderPayByEBSResult = orderPayFeignClient.orderPayByEBS(orderPayByEBSDtoReq);
		BeanUtils.copyProperties(orderPayByEBSResult, result);
		return result;
	}
	
	public PayByEBSValidateResult orderPayByEBSValidate(Long orderId){
		PayByEBSValidateResult result = new PayByEBSValidateResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		
		ClientUserCacheDtoResult currentUser = getCurrentUser();
		//验证是否设置了密码
        BaseResponse<PasswordQueryDtoResult> feignResult = passwordManageFeignClient.query(currentUser.getUser().getId());
        if (!feignResult.isSuccess()) {
        	result.setCode(feignResult.getCode());
            result.setMsg(feignResult.getMsg());
            return result;
        }
        if (!feignResult.getData().isHasPassword()) {
        	result.setPhone(feignResult.getData().getPhone());
        	result.setCode(ResultCode.USER_PAY_PASS_UN_EXIST.getCode());
            result.setMsg(ResultCode.USER_PAY_PASS_UN_EXIST.getDesc());
        	return result;
		}

    	//验证库存, 当天拉货验证库存
		OrderInfoQueryDtoReq orderInfoQueryDtoReq = new OrderInfoQueryDtoReq();
		orderInfoQueryDtoReq.setId(orderId);
		orderInfoQueryDtoReq.setPage(null);
		BaseResponse<OrderListDtoResult> orderInfoQueryResponse = orderFeignClient.queryOrderInfoList(orderInfoQueryDtoReq);
		if (!orderInfoQueryResponse.isSuccess()){
        	result.setCode(orderInfoQueryResponse.getCode());
            result.setMsg(orderInfoQueryResponse.getMsg());
			return result;
		}
		if (null == orderInfoQueryResponse.getData() || CollectionUtils.isEmpty(orderInfoQueryResponse.getData().getOrderList())) {
			result.setCode(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode());
			result.setMsg(ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
			return result;
		}
		OrderDtoResult orderDtoResult = orderInfoQueryResponse.getData().getOrderList().get(0);
		//拉料是今天，验证库存
		if (DateUtils.isSameDay(new Date(), orderDtoResult.getPlanTransportTime())) {
			BaseResponse<OrderValidateRepositoryDtoResult> repositoryFeignResult = orderIntegrationFeignClient.validateRepository(orderId);
	        if (!repositoryFeignResult.isSuccess()){
	        	result.setCode(repositoryFeignResult.getCode());
	            result.setMsg(repositoryFeignResult.getMsg());
				return result;
			}
	        OrderValidateRepositoryDtoResult orderValidateRepositoryDtoResult = repositoryFeignResult.getData();
	        if(orderValidateRepositoryDtoResult.getRepositoryLowFlag()){
	        	result.setCode(ResultCode.OC_ORDER_REPOSITORY_LOW.getCode());
	        	result.setMsg(ResultCode.OC_ORDER_REPOSITORY_LOW.getDesc() + ",请修改拉料日期:" + JSON.toJSONString(orderValidateRepositoryDtoResult.getMaterialName()));
	    		return result;
	    	}
		}

		return result;
	}
	private ClientUserCacheDtoResult getCurrentUser() {
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        try {
            // 检查是否非法登录，如果是非法登录，下面两句代码会出现异常
            currentUser.getCustomer().getCustomer().getEbsCustomerNum();
            currentUser.getVisitShop().getShop().getEbsOrgId();
        } catch (Exception e) {
            throw new BizException(ResultCode.USER_CUSTOMER_OR_SHOP_NOT_EXIST.getCode(), "登录用户无对应客户或未选择对应的商铺");
        }
        return currentUser;
    }
}
