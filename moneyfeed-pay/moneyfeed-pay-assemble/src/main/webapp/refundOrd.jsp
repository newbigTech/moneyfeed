<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.newhope.moneyfeed.pay.api.bank.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>支付商户接口范例-退款</title>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<link href="css/mobaopay.css" type="text/css" rel="stylesheet" />
	-->
	</head>
	<body>
		<table width="50%" border="0" align="center" cellpadding="0"
			cellspacing="0" style="border: solid 1px #107929">
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellpadding="5"
						cellspacing="1">
						<tr>
							<td height="30" colspan="2" bgcolor="#6BBE18">
								<span style="color: #FFFFFF"><a href="index.jsp">感谢您使用支付平台</a>
								</span>
							</td>
						</tr>
						<tr>
							<td colspan="2" bgcolor="#CEE7BD">
								支付订单退款回复结果展示：
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%">
									<%
										try {
											// 签名初始化
											SignUtil.init();

											// 组织输入数据
											Map<String, String> requestMap = new HashMap<String, String>();
											request.setCharacterEncoding("utf-8");
											requestMap.put("apiName", Config.APINAME_REFUND);
											requestMap
													.put("apiVersion", Config.API_VERSION);
											requestMap.put("platformID", Config.PLATFORM_ID);
											requestMap.put("merchNo", Config.MERCHANT_ACC);
											requestMap.put("orderNo", request.getParameter("orderNo"));
											requestMap.put("tradeDate", request.getParameter("tradeDate"));
											requestMap.put("amt", request.getParameter("amt"));
											requestMap.put("tradeSummary", request
													.getParameter("tradeSummary"));
											String paramsStr = Merchant.generateRefundRequest(requestMap);
											String signStr = SignUtil.signData(paramsStr);
											paramsStr = paramsStr + "&signMsg=" + signStr;

											// 发起请求并获取返回数据
											String responseMsg = Merchant.transact(paramsStr,
													Config.PAY_GETWAY);
											responseMsg=responseMsg.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
											out.println(responseMsg);
										} catch (Exception e) {
											out.println(e.getMessage());
										}
									%>
								</table>
							</td>
						</tr>
						<tr>
							<td height="5" bgcolor="#6BBE18" colspan="2">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
