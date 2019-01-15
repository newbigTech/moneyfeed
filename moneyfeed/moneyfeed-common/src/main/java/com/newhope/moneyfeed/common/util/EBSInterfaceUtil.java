package com.newhope.moneyfeed.common.util;


import com.newhope.moneyfeed.common.vo.ebs.req.EBSRequestDataBean;
import com.newhope.moneyfeed.common.vo.ebs.req.EBSRequsetBaseBean;
import com.newhope.moneyfeed.common.vo.ebs.resp.EBSResponse;
import com.newhope.moneyfeed.common.vo.ebs.resp.EBSResponseMsg;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by liming on 2018/7/5.
 */
public class EBSInterfaceUtil {

        private final static Logger logger = LoggerFactory.getLogger(EBSInterfaceUtil.class);

        public final static String EBS_SUCCESS="S000A000";

        public static  String createSN() {
        Date date = new Date();// 创建一个时间对象，获取到当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 设置时间显示格式
        Random random = new Random(UUID.randomUUID().getMostSignificantBits());
        Integer randomNumber = random.nextInt(9999 - 1000 + 1) + 1000;
        // 将当前时间格式化为需要的类型
        String ESB_SN = "MF01" + sdf.format(date) + randomNumber.toString();
        return ESB_SN;
    }


        public static  <T> String getEbsRequestXmlString(String ifcode, T requsetParamObject){

                String snNum=EBSInterfaceUtil.createSN();
                //创建ebs请求头
                EBSRequsetBaseBean ebsRequestDataBean=new EBSRequsetBaseBean();
                //创建请求数据封装对象（泛型为请求的对象类）
                EBSRequestDataBean dataBean=new EBSRequestDataBean();
                //设置唯一的SN号
                dataBean.setBatchNum(snNum);
                //创建请求对象（每个接口不同）
                dataBean.setIfCode(ifcode);
                StringBuffer dataStrBuffer=new StringBuffer();
                //设置对象类到请求封装对象中
                if(requsetParamObject instanceof Collection){
                    Collection collection= (Collection) requsetParamObject;
                    for(Object obj:collection){
                        dataStrBuffer.append(XMLUtil.convertToXml(obj));
                    }
                }else {
                    dataStrBuffer.append(XMLUtil.convertToXml(requsetParamObject));
                }

                dataBean.setpRequestLine(dataStrBuffer.toString());
                /*dataBean.setRequestParamData(requsetParamObject);*/
                //把请求data数据转换为xml
                String dataParamStr=XMLUtil.convertToXml(dataBean);
                dataParamStr=dataParamStr.replace("&lt;","<").replace("&gt;",">");
                logger.info("------请求的数据被报文为;[{}]------",dataParamStr);

                //把请求data数据通过base64编码
                String base64Parm= Base64.encodeBase64String(dataParamStr.getBytes());
                //把base64编码的数据设置到ebs请求头中
                ebsRequestDataBean.setBase64RequestParam(base64Parm);
                ebsRequestDataBean.createUniqueSnNum(snNum);
                ebsRequestDataBean.getEbsBody().getEbsRequest().getEbsAttrs().setEsbIfaceCode(ifcode);
                //再把请求头转为xml
                 String ebsRequsetStr=XMLUtil.convertToXml(ebsRequestDataBean);

                logger.info("------请求EBS接口的报文为;[{}]------",ebsRequsetStr);

                return ebsRequsetStr;


        }


        public static  <T>  T convertEBSResponseXMLtoObject(String xml,Class<T> tClass) throws Exception {

            //新建Ebs返回数据报文头
            EBSResponse ebsCategoryEBSResponse= (EBSResponse) XMLUtil.convertXmlStrToObject(xml,EBSResponse.class);

            if(ebsCategoryEBSResponse==null || ebsCategoryEBSResponse.getEbsResponseBody()==null){
                logger.error("调用EBS接口出错,返回的ebs数据无法解析",xml);
                throw new Exception("调用EBS接口出错错误:"+xml);
            }

            if(!EBS_SUCCESS.equals(ebsCategoryEBSResponse.getEbsResponseBody().getEbsResponeseMsg().getReturnCode())){
                throw new Exception("调用EBS接口出错错误,原因:"+ebsCategoryEBSResponse.getEbsResponseBody().getEbsResponeseMsg().getReturnDesc());
            }

            //解析数据内容段
            String base64Str=ebsCategoryEBSResponse.getEbsResponseBody().getEbsResponeseMsg().getReturnData();

            //反解析加密段
            String dataStr=new String(org.apache.commons.codec.binary.Base64.decodeBase64(base64Str.getBytes()));


            logger.info("--加密数据内容为[{}]--",dataStr);

            dataStr=dataStr.replace("\n","").replace("&","&amp;");

            if(org.apache.commons.lang3.StringUtils.isEmpty(dataStr)){
                return (T) ebsCategoryEBSResponse;
            }

            T responeseListData=(T) XMLUtil.convertXmlStrToObject(dataStr, tClass);

            return responeseListData;
        }


    public static EBSResponseMsg convertEBSResponseXMLtoObject(String xml) throws Exception {

        //新建Ebs返回数据报文头
        EBSResponse ebsCategoryEBSResponse= (EBSResponse) XMLUtil.convertXmlStrToObject(xml,EBSResponse.class);

        if(!EBS_SUCCESS.equals(ebsCategoryEBSResponse.getEbsResponseBody().getEbsResponeseMsg().getReturnCode())){
            //解析数据内容段
            String base64Str=ebsCategoryEBSResponse.getEbsResponseBody().getEbsResponeseMsg().getReturnData();
            //反解析加密段
            String dataStr=new String(org.apache.commons.codec.binary.Base64.decodeBase64(base64Str.getBytes()));

            dataStr=dataStr.replace("\n","").replace("&","&amp;");

            ebsCategoryEBSResponse.getEbsResponseBody().getEbsResponeseMsg().setReturnData(dataStr);
        }

        return ebsCategoryEBSResponse.getEbsResponseBody().getEbsResponeseMsg();
    }


}