<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.newhope.moneyfeed.pay.api.bank.*"%>
<%@page import="java.io.PrintWriter"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>跳转中...</title>
	</head>
	<%
		try {
			// 初始化签名
			SignUtil.init();
			// 组织请求数据
			Map<String, String> paramsMap = new HashMap<String, String>();
			request.setCharacterEncoding("UTF-8");
			paramsMap.put("apiName", Config.APINAME_PAY);
			paramsMap.put("apiVersion", Config.API_VERSION);
			paramsMap.put("platformID", Config.PLATFORM_ID);
			paramsMap.put("merchNo", Config.MERCHANT_ACC);
			paramsMap.put("orderNo", request.getParameter("orderNo"));
			paramsMap.put("tradeDate", request.getParameter("tradeDate"));
			paramsMap.put("amt", request.getParameter("amt"));
			paramsMap.put("merchUrl", Config.MERCHANT_NOTIFY_URL);
			paramsMap.put("merchParam", request.getParameter("merchParam"));
			paramsMap.put("tradeSummary", request.getParameter("tradeSummary"));
			String paramsStr = Merchant.generatePayRequest(paramsMap);	// 签名源数据
			String signMsg = SignUtil.signData(paramsStr);	// 签名数据
			String epayUrl = Config.PAY_GETWAY;	//支付网关地址
			paramsMap.put("signMsg", signMsg);

			// 生成表单并自动提交到支付网关。
			StringBuffer sbHtml = new StringBuffer();
			sbHtml
					.append("<form id='paysubmit' name='paysubmit' action='"
							+ epayUrl + "' method='post'>");
			for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
				sbHtml.append("<input type='hidden' name='"
						+ entry.getKey() + "' value='" + entry.getValue()
						+ "'/>");
			}
			sbHtml.append("</form>");
			sbHtml
					.append("<script>document.forms['paysubmit'].submit();</script>");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.print(sbHtml.toString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	%>
	<body>
	</body>
</html>


