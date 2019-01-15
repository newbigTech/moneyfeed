package com.newhope.openapi.biz.service.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.AppSourceEnums;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderRuleDetailDtoResult;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.pay.api.bank.Config;
import com.newhope.moneyfeed.pay.api.bank.Merchant;
import com.newhope.moneyfeed.pay.api.bank.QueryResponseEntity;
import com.newhope.moneyfeed.pay.api.bank.SignUtil;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderCallbackDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PaySpecialDtoReq;
import com.newhope.moneyfeed.pay.api.dto.response.PayCallbackDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderCreateDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderListDtoResult;
import com.newhope.moneyfeed.pay.api.enums.PayStatusEnum;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerExDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.UserVisitShopDtoResult;
import com.newhope.openapi.api.vo.request.pay.PayOrderCallbackReq;
import com.newhope.openapi.api.vo.request.pay.PayOrderCustomerReq;
import com.newhope.openapi.biz.rpc.feign.order.OrderFeignClient;
import com.newhope.openapi.biz.rpc.feign.order.OrderPayFeignClient;
import com.newhope.openapi.biz.rpc.feign.order.OrderRuleFeignClient;
import com.newhope.openapi.biz.rpc.feign.pay.PayOrderFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.ClientUserFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.ShopFeignClient;

/**  
* @ClassName: PayOrderOpenService  
* @Description: 用户支付
* @author luoxl
* @date 2018年12月26日 上午10:05:23  
*    
*/
@Service
public class PayOrderOpenService {
	private final Logger logger = LoggerFactory.getLogger(PayOrderOpenService.class);

	@Autowired
	OrderFeignClient orderFeignClient;
	@Autowired
	OrderPayFeignClient orderPayFeignClient;
	@Autowired
	RSessionCache rSessionCache;
	@Autowired
	PayOrderFeignClient payOrderFeignClient;
	@Autowired
	OrderRuleFeignClient orderRuleFeignClient;
	@Autowired
	ShopFeignClient shopFeignClient;
	@Autowired
	ClientUserFeignClient clientUserFeignClient;
	
	
	/**  
	* @Title: createPayOrder  
	* @Description: 客户发起支付请求，创建支付订单
	*/
	public PayOrderCreateDtoResult createPayOrder(PayOrderCustomerReq payOrderCustomerReq) {
		PayOrderCreateDtoResult payOrderInfoDtoResult = new PayOrderCreateDtoResult();
		//从缓存中取用户
		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userInfo == null) {
			payOrderInfoDtoResult.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			payOrderInfoDtoResult.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return payOrderInfoDtoResult;
		}
		/**
		 * 用户所属客户model，拿客户号
		 */
		CustomerExDtoResult customer = userInfo.getCustomer();
		/**
		 * 用户当前访问商户model,拿商户账号,商户号
		 */
		UserVisitShopDtoResult visitShop = userInfo.getVisitShop();
		
		PayOrderInfoDtoReq payOrderInfoDtoReq = new PayOrderInfoDtoReq();
		payOrderInfoDtoReq.setAmount(payOrderCustomerReq.getAmount());
		payOrderInfoDtoReq.setOrderNo(payOrderCustomerReq.getOrderNo());
		payOrderInfoDtoReq.setShopId(visitShop.getShop().getId());
		payOrderInfoDtoReq.setStatus(PayStatusEnum.PAY_WAITTING.name());
		payOrderInfoDtoReq.setTradeTime(new Date());
		payOrderInfoDtoReq.setUserId(userInfo.getUser().getId());
		payOrderInfoDtoReq.setPlatformid(Config.PLATFORM_ID);
		payOrderInfoDtoReq.setMerchno(Config.MERCHANT_ACC);
		payOrderInfoDtoReq.setCusNo(customer.getCustomer().getEbsCustomerNum());
		payOrderInfoDtoReq.setOrgId(visitShop.getShop().getEbsOrgId());
		payOrderInfoDtoReq.setPayType(payOrderCustomerReq.getPayType());
		//生成支付订单
		BaseResponse<PayOrderCreateDtoResult> feginPayOrderInfoDtoResult = null;
		if(PayTypeEnums.ACC_PAY.name().equals(payOrderCustomerReq.getPayType())){
			feginPayOrderInfoDtoResult = payOrderFeignClient.createPayUser(payOrderInfoDtoReq);
		}else{
			feginPayOrderInfoDtoResult = payOrderFeignClient.createPayOrder(payOrderInfoDtoReq);
		}
		
		if(feginPayOrderInfoDtoResult.isSuccess()){
			return feginPayOrderInfoDtoResult.getData();
		}else{
			payOrderInfoDtoResult.setCode(feginPayOrderInfoDtoResult.getCode());
			payOrderInfoDtoResult.setMsg(feginPayOrderInfoDtoResult.getMsg());
			return payOrderInfoDtoResult;
		}
	}
	
	/**  
	* @Title: payCallback  
	* @Description: 支付平台回调
	*/
	public PayCallbackDtoResult payCallback (PayOrderCallbackReq payOrderCallbackReq) {
		PayCallbackDtoResult dtoResult = new PayCallbackDtoResult();
		//支付定单号必传
		if(StringUtils.isEmpty(payOrderCallbackReq.getOrderNo())){
			dtoResult.setCode(ResultCode.PAY_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.PAY_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		
		PayOrderCallbackDtoReq payOrderCallbackDtoReq = new PayOrderCallbackDtoReq();
		BeanUtils.copyProperties(payOrderCallbackReq, payOrderCallbackDtoReq);
		
		//查询支付订单取数据
		PayOrderDtoReq payOrderDtoReq = new PayOrderDtoReq();
		payOrderDtoReq.setPayOrderNo(payOrderCallbackReq.getOrderNo());
		BaseResponse<PayOrderListDtoResult> feginPayOrderListDtoResult = payOrderFeignClient.queryPayOrderList(payOrderDtoReq);
		if(!feginPayOrderListDtoResult.isSuccess() || CollectionUtils.isEmpty(feginPayOrderListDtoResult.getData().getPayOrderDtoResult())){
			dtoResult.setCode(ResultCode.PAY_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.PAY_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		PayOrderDtoResult payOrderDtoResult = feginPayOrderListDtoResult.getData().getPayOrderDtoResult().get(0);
		
		//查询店铺数据
		ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
		queryDtoReq.setId(payOrderDtoResult.getShopId());
		BaseResponse<ShopDtoListResult> feginShopDtoListResult = shopFeignClient.queryShop(queryDtoReq);
		if(!feginShopDtoListResult.isSuccess() || CollectionUtils.isEmpty(feginShopDtoListResult.getData().getShops())){
			dtoResult.setCode(ResultCode.PAY_SHOP_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.PAY_SHOP_NOT_EXIST.getDesc());
			return dtoResult;
		}
		UcShopModel ucShopModel = feginShopDtoListResult.getData().getShops().get(0);
		
		//查询订单规则，取店铺联系电话
		OrderRuleQueryDtoReq requestBody = new OrderRuleQueryDtoReq();
		requestBody.setUcShopId(ucShopModel.getId());
		BaseResponse<OrderRuleDetailDtoResult> feginOrderRuleDetailDtoResult = orderRuleFeignClient.queryOrderRuleDetail(requestBody);
		if(!feginOrderRuleDetailDtoResult.isSuccess()){
			dtoResult.setCode(ResultCode.PAY_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.PAY_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		
		//查询用户
		ClientUserQueryDtoReq userDtoReq = new ClientUserQueryDtoReq();
		userDtoReq.setId(payOrderDtoResult.getUserId());
		BaseResponse<ClientUserListDtoResult> feginClientUserListDtoResult = clientUserFeignClient.queryUserInfos(userDtoReq);
		if(!feginClientUserListDtoResult.isSuccess() || CollectionUtils.isEmpty(feginClientUserListDtoResult.getData().getUsers())){
			dtoResult.setCode(ResultCode.USER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		UcClientUserModel ucClientUserModel = feginClientUserListDtoResult.getData().getUsers().get(0);
		//查询三方账号
		BaseResponse<ClientUserThirdAccountDtoResult> clientUserThirdAccountDtoResult = clientUserFeignClient.queryUserThirdAccount(payOrderDtoResult.getUserId(), null, AppSourceEnums.WECHAT.name());
		if(clientUserThirdAccountDtoResult.isSuccess() && clientUserThirdAccountDtoResult.getData().getAccount() != null){
			payOrderCallbackDtoReq.setThirdAcct(clientUserThirdAccountDtoResult.getData().getAccount().getThirdAccount());
		}
		
		payOrderCallbackDtoReq.setOrgId(ucShopModel.getEbsOrgId());
		payOrderCallbackDtoReq.setUserId(ucClientUserModel.getId());
		payOrderCallbackDtoReq.setUserMobile(ucClientUserModel.getMobile());
		payOrderCallbackDtoReq.setUserName(ucClientUserModel.getName());
		payOrderCallbackDtoReq.setShopName(ucShopModel.getName());
		payOrderCallbackDtoReq.setShopMobile(feginOrderRuleDetailDtoResult.getData().getUcShopMobile());
		
//		{   
//		channelCode:“完成支付所选择通道代码”,
//			submerchid:“完成支付所选择通道子商户账户号”,
//			waterNo:“通道交易流水号”，
//			bankName：“银行名称”，
//			bankCardNo:“银行卡号(后四位)”｝
//		}
		String s = new String(Base64.decodeBase64(payOrderCallbackReq.getMerchParam()));
        
        String channelCode = JSONObject.parseObject(s).get("channelCode").toString();
        String submerchid = JSONObject.parseObject(s).get("submerchid").toString();
        String waterNo = JSONObject.parseObject(s).get("waterNo").toString();
        String bankName = JSONObject.parseObject(s).get("bankName").toString();
        String bankCardNo = JSONObject.parseObject(s).get("bankCardNo").toString();
		
        payOrderCallbackDtoReq.setBankCardNo(bankCardNo);
        payOrderCallbackDtoReq.setChannelCode(channelCode);
        payOrderCallbackDtoReq.setSubmerchid(submerchid);
        payOrderCallbackDtoReq.setWaterNo(waterNo);
        payOrderCallbackDtoReq.setBankName(bankName);
//        payOrderCallbackDtoReq.setBankCardNo(bankCardNo);
        //--------测试数据todo-------------------------------
        payOrderCallbackDtoReq.setBankCardNo("951008010003326733");
        
		BaseResponse<PayCallbackDtoResult> feginDtoResult = payOrderFeignClient.payCallback(payOrderCallbackDtoReq);
		if(feginDtoResult.isSuccess()){
			return feginDtoResult.getData();
		}else{
			dtoResult.setCode(ResultCode.FAIL.getCode());
			dtoResult.setMsg(ResultCode.FAIL.getDesc());
			return dtoResult;
		}
	}
	
	/**  
	* @Title: paySpecial  
	* @Description: 特殊的数据处理
	*/
	public DtoResult paySpecial(String type, String orderNo, String payNo, String code, String payType) {
		DtoResult dtoResult = new DtoResult();
		if(!code.equals("allIneed")){
			return dtoResult.fail(ResultCode.FAIL);
		}
		
		PaySpecialDtoReq paySpecialDtoReq = new PaySpecialDtoReq();
		paySpecialDtoReq.setCode(code);
		paySpecialDtoReq.setOrderNo(orderNo);
		paySpecialDtoReq.setPayNo(payNo);
		paySpecialDtoReq.setType(type);
		paySpecialDtoReq.setPayType(payType);
		
		//支付订单状态已支付成功但没有传给EBS,重新传给EBS
		//非余额支付情况
		if(type.equals("1")){
			payOrderFeignClient.paySpecial(paySpecialDtoReq);
		}
		//商城有支付订单，但支付平台不存在，置为无效的订单
		//非余额支付情况
		else if(type.equals("2")){
			payOrderFeignClient.paySpecial(paySpecialDtoReq);
		}
		//EBS接受支付回调成功,但是商城没有改状态
		//非余额支付情况
		else if(type.equals("3")){
			if (PayTypeEnums.BAL_PAY.equals(paySpecialDtoReq.getPayType())) {
				orderPayFeignClient.paySpecial(paySpecialDtoReq);
			} else {
				payOrderFeignClient.paySpecial(paySpecialDtoReq);
			}
		}
		//EBS接受支付回调失败,但是商城没有改状态
		else if(type.equals("4")){
			if (PayTypeEnums.BAL_PAY.equals(paySpecialDtoReq.getPayType())) {
				orderPayFeignClient.paySpecial(paySpecialDtoReq);
			} else {
				payOrderFeignClient.paySpecial(paySpecialDtoReq);
			}
		}
		else if(type.equals("5")){
			orderPayFeignClient.paySpecial(paySpecialDtoReq);
		}else if(type.equals("6")){
			orderPayFeignClient.paySpecial(paySpecialDtoReq);
		}else if(type.equals("7")){
			BaseResponse<DtoResult> feginDtoResult = orderPayFeignClient.paySpecial(paySpecialDtoReq);
			dtoResult.setMsg(JSON.toJSONString(feginDtoResult));
		}else if(type.equals("8")){
			try {
				// 签名初始化
				SignUtil.init();
				// 准备输入数据
				Map<String, String> requestMap = new HashMap<String, String>();
				requestMap.put("apiName", Config.APINAME_QUERY);
				requestMap.put("apiVersion", Config.API_VERSION);
				requestMap.put("platformID", Config.PLATFORM_ID);
				requestMap.put("merchNo", Config.MERCHANT_ACC);
				
				requestMap.put("orderNo", "631205001012");
				requestMap.put("tradeDate", "20190103");
				requestMap.put("amt", "25200");
				
				String paramsStr = Merchant.generateQueryRequest(requestMap);
				String signStr = SignUtil.signData(paramsStr);
				paramsStr = paramsStr + "&signMsg=" + signStr;
				 
				// 发送请求并接收返回
				String responseMsg = Merchant.transact(paramsStr, Config.PAY_GETWAY);
				responseMsg=responseMsg.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
				
				dtoResult.setMsg(responseMsg);
//				String s = "<saasAccount><respData><respCode>00</respCode><respDesc>成功</respDesc><accDate/><accNo>101080748245040234496</accNo><status>0</status></respData><signMsg>f7eeb3a89e1d9e940632a3646f20a6ee</signMsg></saasAccount>";
				QueryResponseEntity queryResponseEntity = new QueryResponseEntity();
				queryResponseEntity.parse(responseMsg.replaceAll("", ""));
				System.out.println(queryResponseEntity.getRespCode());
				System.out.println(queryResponseEntity.getStatus());
			} catch (Exception e) {
				e.printStackTrace();
				dtoResult.setCode(ResultCode.FAIL.getCode());
				dtoResult.setMsg(e.getMessage());
				return dtoResult;
			}
		}
		dtoResult.setCode(ResultCode.SUCCESS.getCode());
		logger.info(">>>>>>>>特殊处理返回的数据>>>>>>>>>:" + JSON.toJSONString(dtoResult));
		return dtoResult;
	}
}












