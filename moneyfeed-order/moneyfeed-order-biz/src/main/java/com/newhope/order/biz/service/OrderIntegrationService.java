package com.newhope.order.biz.service;


import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCreateDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBalanceTypeDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderToEbsDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.IntegrationTimeInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ReceiveOrderCreateInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.RepositoryLowDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.PayResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderValidateRepositoryDtoResult;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;

/**  
* @ClassName: OrderIntegrationService  
* @Description: 订单-中台交互接口
* @author luoxl
* @date 2018年11月20日 下午2:38:09  
*    
*/
public interface OrderIntegrationService {
	
	/**
	 * 商城向中台同步订单创建信息
	 * @param dtoReq
	 * @return
	 */
	DtoResult sendOrderCreateInfo(MoneyfeedToEbsOrderCreateDtoReq dtoReq);
	
	/**
	 * 中台更新商城订单创建信息
	 * @param dtoReq
	 * @return
	 */
	DtoResult receiveOrderCreateInfo(ReceiveOrderCreateInfoDtoReq dtoReq);
	
	/**
	 * 商城向中台同步订单支付信息
	 * @param dtoReq
	 * @return
	 */
	DtoResult sendOrderPayInfo(MoneyfeedToEbsOrderPayInBalanceTypeDtoReq dtoReq);
	
	/**
	 * 中台更新商城订单支付信息
	 * @param dtoReq
	 * @return
	 */
	DtoResult receiveOrderPayInfo(PayResultDtoReq dtoReq);
	
	/**  
	* @Title: reciveIntegrationTimeInfo  
	* @Description: 接收中台时间变更事件及带入的内容修改订单信息
	*/
	DtoResult reciveIntegrationTimeInfo(IntegrationTimeInfoDtoReq integrationTimeInfoDtoReq);
	
	/**  
	* @Title: reciveCancelOrderResult  
	* @Description: integration调用商城更新“取消订单”结果
	*/
	DtoResult reciveCancelOrderResult(CancelOrderResultDtoReq cancelOrderResultDtoReq);
	
	/**  
	* @Title: sendCancelOrderForEbs  
	* @Description: 商城调用integration取消订单
	*/
	DtoResult sendCancelOrderToEbs(CancelOrderToEbsDtoReq cancelOrderToEbsDtoReq);
	
	/**  
	* @Title: sendTransportInfoToEbs  
	* @Description: 商城调用integration更新拉料信息
	*/
	DtoResult sendTransportInfoToEbs(TransportInfoToEbsDtoReq transportInfoToEbsDtoReq);
	
	/**  
	* @Title: reciveTransportInfoToEbsResult  
	* @Description: integration调用商城更新 拉料信息结果
	*/
	DtoResult reciveTransportInfoToEbsResult(TransportInfoToEbsResultDtoReq transportInfoToEbsResultDtoReq);
	
	/**  
	* @Title: reciveRepositoryLow  
	* @Description: 接收中台库存不足请求
	*/
	DtoResult reciveRepositoryLow(RepositoryLowDtoReq repositoryLowDtoReq);
	
	/**  
	* @Title: pullOrderEventInfo  
	* @Description: 拉取中间层的EBS更新后的数据更新订单中心的数据
	*/
	void pullOrderEventInfo();
	
	/**  
	* @Title: reciveIntegrationOrderInfo  
	* @Description: 处理物料信息
	*/
	DtoResult reciveIntegrationOrderInfo(IntegrationTimeInfoDtoReq integrationTimeInfoDtoReq);
	
	/**  
	* @Title: reciveNotifyEbsPay  
	* @Description: 接受EBS返回支付充值结果
	*/
	DtoResult reciveNotifyEbsPay(ReciveNotifyEbsPayDtoReq dtoReq);
	
	/**  
	* @Title: validateRepository  
	* @Description: 验证订单锁库的库存是否足够
	*/
	OrderValidateRepositoryDtoResult validateRepository(Long orderId);

}








