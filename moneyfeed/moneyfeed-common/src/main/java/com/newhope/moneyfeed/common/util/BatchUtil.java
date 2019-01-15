package com.newhope.moneyfeed.common.util;

import com.newhope.moneyfeed.common.vo.ebs.resp.EBSResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * *<p>Title:批量限制导入辅助类 </p>* 
	<p>Description: </p>* 
     @author liming
     @date 2017年11月2日
 */
public class BatchUtil {

	
	/**
	 * 
	 * desc:
	 * @param list 要导入的数据
	 * @param size 限制一次导入的长度
	 * @param ck  回调函数
	 * @author:liming 
	 * @date:2017年11月2日
	 */
	public static <T>  void  limitBatch(Collection<T> list, Integer size, final CallBackMethod<T> ck) throws Exception {
		
		List<T> newList =new ArrayList<T>(size);
		for(T t:list){
			newList.add(t);
			if(newList.size()==size){
				ck.doCallBack(newList);
				newList.clear();
			}
		}
		if(newList.size()!=0){
			ck.doCallBack(newList);
		}

	}
	public  interface CallBackMethod<T>{

		void doCallBack(List<T> list) throws Exception;

	}


	public  static void main(String[] args){

		String re="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
				"<soap:Header xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
				" </soap:Header><env:Body xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">\n" +
				" <esb:RESPONSE xmlns:esb=\"http://w3.ibm.com/gbs/ais/ei/esb\">\n" +
				" <esb:RETURN_CODE>S000A000</esb:RETURN_CODE>\n" +
				" <esb:RETURN_DATA/><esb:RETURN_DESC/></esb:RESPONSE></env:Body></soapenv:Envelope>";

		try {
			EBSResponse response=EBSInterfaceUtil.convertEBSResponseXMLtoObject(re, EBSResponse.class);
			System.out.println("------------");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}


