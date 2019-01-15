//package com.newhope.zhuxiaoer.trade.api;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.math.BigDecimal;
//import java.net.URI;
//
//
//public class TestEbs {
//
//    public static void main(String[] args) {
////		 String res = doPost("http://127.0.0.1:8600/third/ebs",packThirdData(EbsOperTypeEnums.HONGBAO_AMOUNT.getValue()));
////	     System.out.println(JSONObject.parse(res));
//    }
//
//    //组装三方平台数据
//    public static String packThirdData(String operTypes) {
//
//        String apiKey = "f64b62a24841f25059e9bb31e7be2184";
//        String secret_key = "06bffa7b22b4ec454f4a51bb870efadd";
//        Long timestamp = System.currentTimeMillis() / 1000;
//
//        //业务数据组装
//        JSONObject enterObj = new JSONObject();
//        enterObj.put("opertypes", operTypes);//
//        if ("USED_HONGBAO".equals(operTypes)) {
//            enterObj.put("data", packUsedHongBaoData());//使用红包业务数据组装
//        } else if ("HONGBAO_AMOUNT".equals(operTypes)) {
//            enterObj.put("data", packHongBaoAmountData());//查询红包余额业务数据组装
//        }
//
//        // 参数验签数据组装
//        String text = new StringBuilder().append(apiKey)
//                .append(timestamp).append(enterObj.toJSONString())
//                .append(secret_key)
//                .toString();
//
//        String sign = null;
//        try {
//            sign = DigestUtils.md5Hex(text.getBytes("utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//
//        //平台参数
//        JSONObject params = new JSONObject();
//        params.put("apikey", apiKey);
//        params.put("timestamp", timestamp);
//        params.put("sign", sign);
//        params.put("opertypes", "USED_HONGBAO");
//        params.put("data", enterObj);
//
//        return params.toJSONString();
//    }
//
//
//    //组装使用红包的业务数据
//	public static String packUsedHongBaoData() {
//		EbsHong req = new EbsHongBaoUsedDtoReq();
//		req.setAuthCode("8888");//假设红包验证码
//		req.setMobile("18788989878");
//		req.setOrderNumber("O920293930");//购买料的订单号
//		req.setOperType("USE_FODDER");//操作类型
//		//req.setThirdId(UUID.randomUUID().toString());//三方Id
//		req.setWeight("2000");//购买吨数
//		req.setUseAmount(new BigDecimal(10));//核销红金额
//		req.setTotalAmount(new BigDecimal(20));
//		return JSONObject.toJSONString(req);
//	}
//
//    //组装查询红包余额的业务数据
////	public static String packHongBaoAmountData() {
////		EbsHongbaoInfoDtoReq req = new EbsHongbaoInfoDtoReq();
////		req.setAuthCode("8888");//假设红包验证码
////		req.setMobile("18788989878");
////		return JSONObject.toJSONString(req);
////	}
//
//
//    /**
//     * post请求(用于key-value格式的参数)
//     *
//     * @param url
//     * @param params
//     * @return
//     */
//    public static String doPost(String url, String params) {
//
//        BufferedReader in = null;
//        // 定义HttpClient
//        @SuppressWarnings("deprecation")
//        HttpClient client = new DefaultHttpClient();
//        try {
//            // 实例化HTTP方法
//            HttpPost request = new HttpPost();
//            request.setHeader("Accept", "application/json");
//            request.setHeader("Content-Type", "application/json");
//            request.setURI(new URI(url));
//
//            //设置参数
//
//            StringEntity entity = new StringEntity(params, ContentType.APPLICATION_JSON);
//
//            request.setEntity(entity);
//
//            HttpResponse response = client.execute(request);
//            int code = response.getStatusLine().getStatusCode();
//            if (code == 200) {    //请求成功
//                in = new BufferedReader(new InputStreamReader(response.getEntity()
//                        .getContent(), "utf-8"));
//                StringBuffer sb = new StringBuffer("");
//                String line = "";
//                String NL = System.getProperty("line.separator");
//                while ((line = in.readLine()) != null) {
//                    sb.append(line + NL);
//                }
//
//                in.close();
//
//                return sb.toString();
//            } else {   //
//                System.out.println("状态码：" + code);
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
