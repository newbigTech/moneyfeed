package com.newhope.moneyfeed.mq.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.newhope.moneyfeed.api.bean.BaseModel;
import org.apache.commons.collections.CollectionUtils;



/**  
* @ClassName: BizUtils  
* @Description: 消息引擎工具类
*    
*/
public class BizUtils {

	/**  
	* @Title: getTxnBeginTime  
	* @Description: 获取BaseModel中的修改时间最小的一个
	* @param @param modelList
	* @param @return    设定文件  
	* @return long    返回类型  
	* //单个model
		String txnBeginTime = String.valueOf(CommonHelper.getTxnBeginTime(model));
		//取系统时间
		String txnBeginTime = String.valueOf(CommonHelper.getTxnBeginTime());
		//多个model
		String txnBeginTime = String.valueOf(CommonHelper.getTxnBeginTime(new BaseModel[]{model, model}));
		//list
		String txnBeginTime = String.valueOf(CommonHelper.getTxnBeginTime(list.toArray(new BaseModel[list.size()])));
	* @throws  
	*/
	public static long getTxnBeginTime(BaseModel... modelList) {
		List<BaseModel> list = new ArrayList<BaseModel>();
		if(modelList!=null){
			list = Arrays.asList(modelList);
		}
		long txnBeginTime = System.currentTimeMillis();
		if (modelList == null || modelList.length == 0) {
		} else if (modelList.length == 1) {
			txnBeginTime = modelList[0].getGmtModified().getTime();
		} else {
			Collections.sort(list, new Comparator<BaseModel>() {
				@Override
				public int compare(BaseModel o1, BaseModel o2) {
					Date gmtModified0 = o1.getGmtModified();
					Date gmtModified1 = o2.getGmtModified();
					if(gmtModified0 == null && gmtModified1 == null){
						return 0;
					}else if(gmtModified0 == null){
						return -1;
					}else if(gmtModified1 == null){
						return 1;
					}
					
					if (gmtModified0.getTime() > gmtModified1.getTime()) {
						return 1;
					} else if (gmtModified0.getTime() == gmtModified1.getTime()) {
						return 0;
					} else {
						return -1;
					}
				}
			});
			txnBeginTime = list.get(0).getGmtModified().getTime();
		}
		return txnBeginTime;
	}
	
	/**
	 * 二分法获取modelList中对象最小创建时间
	 * @param modelList
	 * @return
	 */
	public static long getTxnBeginTime(List<BaseModel> modelList) {
		if(CollectionUtils.isEmpty(modelList)){
			return new Date().getTime();
		}
		Collections.sort(modelList, new Comparator<BaseModel>() {
			@Override
			public int compare(BaseModel o1, BaseModel o2) {
				Date gmtModified0 = o1.getGmtModified();
				Date gmtModified1 = o2.getGmtModified();
				if(gmtModified0 == null && gmtModified1 == null){
					return 0;
				}else if(gmtModified0 == null){
					return -1;
				}else if(gmtModified1 == null){
					return 1;
				}
				if (gmtModified0.getTime() > gmtModified1.getTime()) {
					return 1;
				} else if (gmtModified0.getTime() == gmtModified1.getTime()) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		return modelList.get(0).getGmtModified().getTime();
	}
}
