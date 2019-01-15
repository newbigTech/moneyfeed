package com.newhope.moneyfeed.integration.web.controller.ebs;

import java.util.Date;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.concurrent.ManagedThreadPool;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.integration.biz.thread.EBSSyncCustomerThread;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.integration.api.rest.ebs.EbsSyncDataAPI;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.CategoryReq;
import com.newhope.moneyfeed.integration.biz.thread.EBSSyncMaterialThread;
import com.newhope.moneyfeed.integration.biz.thread.EBSyncCategoryThread;

/**
 *
 * @author liming
 * @date 2018/7/9
 */
@RestController
public class SyncEBSDataController extends AbstractController implements EbsSyncDataAPI {

	private final static Logger logger = LoggerFactory.getLogger(SyncEBSDataController.class);

	@Autowired
	private CacheData cacheData;

	@Autowired
	private ManagedThreadPool managedThreadPool;

	/**
	 * 同步物料类别
	 * 
	 * @return
	 */
	@Override
	public BaseResponse<DtoResult> syncEBSCategory() {
		DtoResult result = new DtoResult();
		try {
			/*
			 * String getCategoryTime =
			 * (String)cacheData.getData("get_category_time");
			 * if(StringUtils.isEmpty(getCategoryTime)){
			 * getCategoryTime="19000101000000"; }
			 */
			String nowDate = DateUtil.getDateStr(new Date(), DateUtil.YYYYMMDDHHMMSS);
			CategoryReq categoryReq = new CategoryReq();
			categoryReq.setBeginTime("19000101000000");// 改成全量同步
			categoryReq.setEndTime(nowDate);
			// 异步执行，防止超时
			EBSyncCategoryThread ebSyncCategoryThread = new EBSyncCategoryThread(categoryReq);
			managedThreadPool.submit(ebSyncCategoryThread);
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} catch (Exception e) {
			logger.error("同步ebs物料品类出错：", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}

		return buildJson(result);
	}

	/**
	 * 同步物料
	 * 
	 * @return
	 */
	@Override
	public BaseResponse<DtoResult> syncEBSMaterial(@RequestBody String orgId) {

		DtoResult result = new DtoResult();
		try {
			// 获取物料接口同步状态  
			// 运行中(RUNNING) 直接返回
			// 已完成(COMPLETE) 状态改成 运行中
			if("ALL_ORG".equals(orgId))//查询所有公司
			{
				orgId="";
			}
			
			String syncItemStatus = (String)cacheData.getData("IntegrationSyncEBSItemStatus"+orgId);
			
			logger.info("物料接口同步状态："+syncItemStatus+";Redis Key:"+"IntegrationSyncEBSItemStatus"+orgId);
			
			if (StringUtils.isEmpty(syncItemStatus) || "COMPLETE".equals(syncItemStatus)) {
				cacheData.setData("IntegrationSyncEBSItemStatus"+orgId, "RUNNING");//将物料接口同步状态改为运行中	
				EBSSyncMaterialThread ebsSyncMaterialThread = new EBSSyncMaterialThread(orgId);
				managedThreadPool.submit(ebsSyncMaterialThread);
			}

			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} catch (Exception e) {
			logger.error("同步ebs物料出错：", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
		return buildJson(result);
	}

	/**
	 * 同步客户
	 * 
	 * @return
	 */
	@Override
	public BaseResponse<DtoResult> syncEBSCustomer() {
		DtoResult result = new DtoResult();
		try {
			EBSSyncCustomerThread ebsSyncCustomerThread = new EBSSyncCustomerThread();
			managedThreadPool.submit(ebsSyncCustomerThread);
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} catch (Exception e) {
			logger.error("同步ebs客户出错：", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
		return buildJson(result);
	}

}