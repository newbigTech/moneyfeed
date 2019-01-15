package com.newhope.openapi.web.controller.pay;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.pay.api.bank.Config;
import com.newhope.moneyfeed.pay.api.bank.Merchant;
import com.newhope.moneyfeed.pay.api.bank.SignUtil;
import com.newhope.moneyfeed.pay.api.dto.response.PayCallbackDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderCreateDtoResult;
import com.newhope.openapi.api.rest.pay.PayOrderOpenAPI;
import com.newhope.openapi.api.vo.request.pay.MerchParamReq;
import com.newhope.openapi.api.vo.request.pay.PayOrderCallbackReq;
import com.newhope.openapi.api.vo.request.pay.PayOrderCustomerReq;
import com.newhope.openapi.api.vo.request.pay.SubMerchInfoReq;
import com.newhope.openapi.biz.service.pay.PayOrderOpenService;

@RestController
public class PayOrderOpenController extends AbstractController implements PayOrderOpenAPI {
	private final Logger logger = LoggerFactory.getLogger(PayOrderOpenController.class);

	@Autowired
	PayOrderOpenService payOrderOpenService;
	
	@Override
	public void payCustomer(HttpServletRequest request, HttpServletResponse response, Writer writer, @ModelAttribute("form") PayOrderCustomerReq reqBody) {
		//创建支付订单
		PayOrderCreateDtoResult payOrderInfoDtoResult = payOrderOpenService.createPayOrder(reqBody);
		//如果创建支付订单出错，跳到错误提示页面
		if(!ResultCode.SUCCESS.getCode().equals(payOrderInfoDtoResult.getCode())){
			logger.error("[PayOrderOpenController.payCustomer创建支付订单时出错]" + JSON.toJSONString(payOrderInfoDtoResult));
			String uri = "/static/html/wechat/msgToClose.html?referer=1&title="+ URLEncoder.encode("提示") +"&msg="+ URLEncoder.encode(payOrderInfoDtoResult.getMsg());
			response.addHeader("location", uri);
			response.setStatus(302);
			return;
		}
		//创建支付订单成功后,请求支付网关，跳转到支付页面
		try {
			// 初始化签名
			SignUtil.init();
			// 组织请求数据
			Map<String, String> paramsMap = new HashMap<String, String>();
			request.setCharacterEncoding("UTF-8");
			//接口名字
			paramsMap.put("apiName", Config.APINAME_PAY);
			//接口版本
			paramsMap.put("apiVersion", Config.API_VERSION);
			//商户(合作伙伴)ID
			paramsMap.put("platformID", Config.PLATFORM_ID);
			//商户账号
			paramsMap.put("merchNo", Config.MERCHANT_ACC);
			//商户支付订单号
			paramsMap.put("orderNo", payOrderInfoDtoResult.getPayOrderNo());
			//交易日期 必输，由商户系统生成 YYYYMMDD年月日
			paramsMap.put("tradeDate", DateUtil.getDateStr(new Date(), DateUtil.YYYYMMDD));
			//订单金额
			paramsMap.put("amt", reqBody.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			//支付结果通知地址
			paramsMap.put("merchUrl", Config.MERCHANT_NOTIFY_URL);
			
			//商户参数todo----------------------------------------------------------------
			MerchParamReq merchParamReq = new MerchParamReq();
			merchParamReq.setMerchName("测试商户");
			merchParamReq.setCustomerId(payOrderInfoDtoResult.getCusNo());
			List<SubMerchInfoReq> subMerchInfos = new ArrayList<SubMerchInfoReq>();
			SubMerchInfoReq subMerchInfoReq1 = new SubMerchInfoReq();
			subMerchInfoReq1.setSubMerchId("123456");
			subMerchInfoReq1.setChannelCode("PSBC");
			subMerchInfos.add(subMerchInfoReq1);
			
			SubMerchInfoReq subMerchInfoReq2 = new SubMerchInfoReq();
			subMerchInfoReq2.setSubMerchId("987654");
			subMerchInfoReq2.setChannelCode("YBPAY");
			subMerchInfos.add(subMerchInfoReq2);
			merchParamReq.setSubMerchInfos(subMerchInfos);
			
			String merchParam = JSON.toJSONString(merchParamReq);
			String base64 = Base64.encodeBase64String(merchParam.getBytes());
			paramsMap.put("merchParam", base64);
			//商户参数-------------------------------------------------------------------
			
			//交易摘要
			paramsMap.put("tradeSummary", "支付");
			String paramsStr = Merchant.generatePayRequest(paramsMap); // 签名源数据
			//签名
			String signMsg = SignUtil.signData(paramsStr); // 签名数据
			String epayUrl = Config.PAY_GETWAY; //支付网关地址
			paramsMap.put("signMsg", signMsg);
			
			// 生成表单并自动提交到支付网关。
			StringBuffer sbHtml = new StringBuffer();
			sbHtml.append("<html><form id='paysubmit' name='paysubmit' action='" + epayUrl + "' method='post'>");
			for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
				sbHtml.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
			}
			sbHtml.append("</form>");
			sbHtml.append("<script>document.forms['paysubmit'].submit();</script></html>");
			writer.write(sbHtml.toString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error("[PayOrderOpenController.payCustomer出错]", e);
			String uri = "/static/html/wechat/msgToClose.html?referer=1&title="+ URLEncoder.encode("提示") +"&msg="+ URLEncoder.encode(e.getMessage());
			response.addHeader("location", uri);
			response.setStatus(302);
			return;
		}
	}

	@Override
	public String payCallback(HttpServletRequest request, HttpServletResponse response, Writer writer) {
//		if(1 == 1){
//			try {
//				writer.write("SUCCESS");
//				return null;
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		try {
			// 签名初始化
			SignUtil.init();
			// 获取请求参数，并将数据组织成前面验证源字符串
			request.setCharacterEncoding("utf-8");
			//接口名称
			String apiName = request.getParameter("apiName");
			//通知时间
			String notifyTime = request.getParameter("notifyTime");
			//支付金额
			String tradeAmt = request.getParameter("tradeAmt");
			//商户号
			String merchNo = request.getParameter("merchNo");
			//商户参数
			String merchParam = request.getParameter("merchParam");
			//商户订单号
			String orderNo = request.getParameter("orderNo");
			//商户交易日期
			String tradeDate = request.getParameter("tradeDate");
			//支付平台订单号
			String accNo = request.getParameter("accNo");
			//支付平台订单日期
			String accDate = request.getParameter("accDate");
			//订单状态 0 未支付，1 成功，2失败
			String orderStatus = request.getParameter("orderStatus");
			//验签结果描述
			String signMsg = request.getParameter("signMsg");
			
			String notifyType = request.getParameter("notifyType");
			
			logger.info("[PayOrderOpenController.payCallback]的签名是" + signMsg);
			//signMsg.replaceAll(" ", "\\+");
			String srcMsg = String.format(
					"apiName=%s&notifyTime=%s&tradeAmt=%s&merchNo=%s&merchParam=%s&orderNo=%s&tradeDate=%s&accNo=%s&accDate=%s&orderStatus=%s", 
					apiName,notifyTime, tradeAmt, merchNo, merchParam, orderNo, tradeDate, accNo, accDate, orderStatus);
			logger.info("[PayOrderOpenController.payCallback]srcMsg是" + srcMsg);
			
			// 验证签名
			boolean verifyRst = SignUtil.verifyData(signMsg, srcMsg);
			String verifyStatus = "未验证";
			if (verifyRst) {
				verifyStatus = "验签通过";
				/**
				 * 验证通过后，请在这里加上商户自己的业务逻辑处理代码.
				 * 取出商城订单,支付订单
				 * 根据返回的订单状态修改商城订单和支付订单，记录日志，向EBS发起请求
				 * 0 未支付，1 成功，2失败
				 * 0-支付订单状态改为未支付
				 * 1-支付订单状态改为已支付，商城订单状态为已支付,向EBS发起支付或充值请求
				 * 2-支付订单状态改为未支付，记录支付失败原因
				 */
				PayOrderCallbackReq payOrderCallbackReq = new PayOrderCallbackReq();
				payOrderCallbackReq.setApiName(apiName);
				payOrderCallbackReq.setNotifyTime(notifyTime);
				payOrderCallbackReq.setTradeAmt(tradeAmt);
				payOrderCallbackReq.setMerchNo(merchNo);
				payOrderCallbackReq.setOrderNo(orderNo);
				payOrderCallbackReq.setTradeDate(tradeDate);
				payOrderCallbackReq.setAccNo(accNo);
				payOrderCallbackReq.setOrderStatus(orderStatus);
				payOrderCallbackReq.setMerchParam(merchParam);
				
				//----------------------------------测试数据----------------------------------
				payOrderCallbackReq.setApiName(apiName);
				payOrderCallbackReq.setNotifyTime(notifyTime);
				payOrderCallbackReq.setMerchNo(Config.MERCHANT_ACC);
				payOrderCallbackReq.setMerchParam(merchParam);
				payOrderCallbackReq.setTradeDate("20181229");
				payOrderCallbackReq.setAccNo(String.valueOf(new Date().getTime()));
				payOrderCallbackReq.setOrderStatus("1");
				payOrderCallbackReq.setAccDate("20181229173030");
				payOrderCallbackReq.setShopBankAcco("951008010003326733");
				notifyType = "0";
				String s = "{channelCode:\"完成支付所选择通道代码\",submerchid:\"完成支付所选择通道子商户账户号\",waterNo:\"通道交易流水号\",bankName:\"银行名称\",bankCardNo:\"银行卡号(后四位)\"}";
				String base64 = Base64.encodeBase64String(s.getBytes());
				payOrderCallbackReq.setMerchParam(base64);
				
				payOrderCallbackReq.setOrderNo(request.getParameter("orderNoTemp"));
				payOrderCallbackReq.setTradeAmt(request.getParameter("amtTemp"));
				//--------------------------------------------------------------------------------------
				
				//操作业务数据
				PayCallbackDtoResult payCallbackDtoResult = payOrderOpenService.payCallback(payOrderCallbackReq);
				//如果出现错误，跳到错误页面
				if(!ResultCode.SUCCESS.getCode().equals(payCallbackDtoResult.getCode())){
					logger.error("[PayOrderOpenController.payCallback验签失败]");
					String uri = "/static/html/wechat/msgToClose.html?referer=1&title="+ URLEncoder.encode("提示") +"&msg="+ URLEncoder.encode(payCallbackDtoResult.getMsg())+"&payType="+payCallbackDtoResult.getPayType();
					response.addHeader("location", uri);
					response.setStatus(302);
					return null;
				}
				
				// 判断通知类型，若为后台通知需要回写"SUCCESS"给支付系统表明已收到支付通知
				// 否则支付系统将按一定的时间策略在接下来的24小时内多次发送支付通知。
				if (notifyType.equals("1")) {
					writer.write("SUCCESS");
					return null;
				}else if(notifyType.equals("0")){
					String uri = "";
					if(PayTypeEnums.ACC_PAY.name().equals(payCallbackDtoResult.getPayType())){
						uri = "/static/html/pay/complete.html?referer=1&title="+ URLEncoder.encode(PayTypeEnums.valueOf(payCallbackDtoResult.getPayType()).getDesc()) +"&msg="+ URLEncoder.encode(tradeAmt)+"&payType="+PayTypeEnums.CARD_PAY.name();
					}else{
						uri = "/static/html/pay/success.html?referer=1&title="+ URLEncoder.encode(PayTypeEnums.valueOf(payCallbackDtoResult.getPayType()).getDesc()) +"&msg="+ URLEncoder.encode(tradeAmt)+"&payType="+PayTypeEnums.CARD_PAY.name()+"&orderId="+ payCallbackDtoResult.getOrderId() +"&orderNo=" + payCallbackDtoResult.getOrderNo();
					}
					response.addHeader("location", uri);
					response.setStatus(302);
					return null;
				}
			} else{
				verifyStatus = "验签失败";
				logger.error("[PayOrderOpenController.payCallback验签失败]");
				String uri = "/static/html/wechat/msgToClose.html?referer=1&title="+ URLEncoder.encode("提示") +"&msg="+ URLEncoder.encode(verifyStatus)+"&payType="+PayTypeEnums.CARD_PAY.name();
				response.addHeader("location", uri);
				response.setStatus(302);
				return null;
			}
		} catch (Exception e) {
			logger.error("[PayOrderOpenController.payCallback出错]", e);
			String uri = "/static/html/wechat/msgToClose.html?referer=1&title="+ URLEncoder.encode("提示") +"&msg="+ URLEncoder.encode(e.getMessage());
			response.addHeader("location", uri);
			response.setStatus(302);
			return null;
		}
		return null;
	}

	@Override
	public String paySpecial(HttpServletRequest request, HttpServletResponse response, Writer writer) {
		//操作业务数据
		String type = request.getParameter("type");
		String orderNo = request.getParameter("orderNo");
		String payNo = request.getParameter("payNo");
		String code = request.getParameter("code");
		String payType = request.getParameter("payType");
		try {
			//控response以什么码表写数据
			response.setCharacterEncoding("utf-8");
			//指定浏览器以什么码表解码服务器发送的数据  
			//response.setHeader("Content-Type", "text/html; charset=UTF-8");  
			response.setContentType("text/html; charset=UTF-8");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logger.info(type +" - " + orderNo +" - " + payNo +" - " + code + " - " + payType);
		DtoResult dtoResult = payOrderOpenService.paySpecial(type, orderNo, payNo, code, payType);
		try {
			writer.write(JSON.toJSONString(dtoResult));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}


