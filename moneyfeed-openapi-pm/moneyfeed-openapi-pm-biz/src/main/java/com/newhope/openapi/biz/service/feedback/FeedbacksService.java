package com.newhope.openapi.biz.service.feedback;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.util.ContextHolderUtil;
import com.newhope.moneyfeed.common.util.ExportMultipleSheetExcel;
import com.newhope.moneyfeed.common.util.SessionUtil;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackDistributedDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackStatusDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.ShopPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.UserPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackDetailDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackItemDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackShopListDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackShopListDtoResult.ShopModel;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackUserListDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackUserListDtoResult.UserModel;
import com.newhope.openapi.api.vo.request.feedback.FeedbackDistributedReq;
import com.newhope.openapi.api.vo.request.feedback.FeedbackPageReq;
import com.newhope.openapi.api.vo.request.feedback.FeedbackStatusReq;
import com.newhope.openapi.api.vo.request.feedback.ShopPageReq;
import com.newhope.openapi.api.vo.request.feedback.UserPageReq;
import com.newhope.openapi.api.vo.response.feedback.FeedbackDetailResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackItemResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackShopListResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackUserListResult;
import com.newhope.openapi.biz.rpc.feign.feedback.FeedbackFeignClient;

@Service
public class FeedbacksService {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbacksService.class);
	
	@Autowired
	FeedbackFeignClient feedbackFeignClient;
	
	/**
	 * 留言记录列表查询
	 * @param req
	 * @return
	 */
	public FeedbackResult feedbackList(FeedbackPageReq req) {
		FeedbackResult result = new FeedbackResult();
		FeedbackPageDtoReq dtoReq = new FeedbackPageDtoReq();
		BeanUtils.copyProperties(req, dtoReq);
		BaseResponse<FeedbackDtoResult> feignResp = feedbackFeignClient.feedbackList(dtoReq );
		List<FeedbackItemResult> feedbackItemList = new ArrayList<FeedbackItemResult>();
		if(feignResp != null){
			result.setCode(feignResp.getCode());
			result.setMsg(feignResp.getMsg());
			if (null == feignResp.getData()) {
				result.setPage(0l);
				result.setTotalCount(0l);
				result.setTotalPage(0l);
				result.setFeedbackItemList(feedbackItemList);
				return result;
			}
			List<FeedbackItemDtoResult> feedbackItemDtoResultList = feignResp.getData().getFeedbackItemList();
			if(!CollectionUtils.isEmpty(feedbackItemDtoResultList)){
				for (FeedbackItemDtoResult feedbackItemDtoResult : feedbackItemDtoResultList) {
					FeedbackItemResult feedbackItemResult = new FeedbackItemResult();
					BeanUtils.copyProperties(feedbackItemDtoResult, feedbackItemResult);
					feedbackItemList.add(feedbackItemResult);
				}
				result.setPage(feignResp.getData().getPage());
				result.setTotalCount(feignResp.getData().getTotalCount());
				result.setTotalPage(feignResp.getData().getTotalPage());
			}
		}
		result.setFeedbackItemList(feedbackItemList);
		if(CollectionUtils.isEmpty(feedbackItemList)){
			result.setPage(0l);
			result.setTotalCount(0l);
			result.setTotalPage(0l);
		}
		return result;
	}
	
	/**
	 * 导出客户留言列表
	 * @param req
	 * @param response
	 * @return
	 */
	public Result feedbackExport(FeedbackPageReq req, HttpServletResponse response) {
		Result result = new Result();
		req.setPageSize(Integer.MAX_VALUE);
		FeedbackResult resultBase = feedbackList(req);
		
	    List<FeedbackItemResult> allTotal = resultBase.getFeedbackItemList();
	    
	    Map<String, Class<?>> map = new HashMap<String, Class<?>>();
	    map.put("意见反馈列表",FeedbackItemResult.class);
	    Map exDataList=new LinkedHashMap();
	    exDataList.put("意见反馈列表",allTotal);
	    ExportMultipleSheetExcel exportMultipleSheetExcel=new ExportMultipleSheetExcel(map,exDataList);
	    String fileName = "意见反馈列表" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx";
	    try {
			exportMultipleSheetExcel.write(response, fileName).dispose();
		} catch (IOException e) {
			logger.error("[FeedbacksService.feedbackExport意见反馈导出异常", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
	    return result;
    }
	
	/**
	 * 处理完成/关闭留言
	 * @param req
	 * @return
	 */
	public Result feedbackStatusModify(FeedbackStatusReq req) {
		Result result = new Result();
		
		FeedbackStatusDtoReq dtoReq = new FeedbackStatusDtoReq();
		BeanUtils.copyProperties(req, dtoReq);
		BaseResponse<DtoResult> feignResp = feedbackFeignClient.feedbackStatusModify(dtoReq);
		if(feignResp != null){
			result.setCode(feignResp.getCode());
			result.setMsg(feignResp.getMsg());
		}
		
		return result;
	}
	
	/**
	 * 详情页查询
	 * @param id
	 * @return
	 */
	public FeedbackDetailResult feedbackDetail(Long id) {
		FeedbackDetailResult result = new FeedbackDetailResult();
		
		BaseResponse<FeedbackDetailDtoResult> feignResp = feedbackFeignClient.feedbackDetail(id);
		if(feignResp != null){
			if(feignResp.getData() != null){
				BeanUtils.copyProperties(feignResp.getData(), result);
			}
		}
		
		return result;
	}
	
	/**
	 * 分配商户/关闭
	 * @param req
	 * @return
	 */
	public Result feedbackDistributedModify(FeedbackDistributedReq req) {
		Result result = new Result();
		
		FeedbackDistributedDtoReq dtoReq = new FeedbackDistributedDtoReq();
		BeanUtils.copyProperties(req, dtoReq);
		BaseResponse<DtoResult> feignResp = feedbackFeignClient.feedbackDistributedModify(dtoReq);
		if(feignResp != null){
			result.setCode(feignResp.getCode());
			result.setMsg(feignResp.getMsg());
		}
		
		return result;
	}
	
	/**
	 * 联系人模糊查询
	 * @param req
	 * @return
	 */
	public FeedbackUserListResult queryUserList(UserPageReq req) {
		FeedbackUserListResult result = new FeedbackUserListResult();
		UserPageDtoReq dtoReq = new UserPageDtoReq();
		BeanUtils.copyProperties(req, dtoReq);
		BaseResponse<FeedbackUserListDtoResult> feignResp = feedbackFeignClient.queryUserList(dtoReq);
		List<FeedbackUserListResult.UserModel> users = new ArrayList<FeedbackUserListResult.UserModel>();
		if(feignResp != null){
			result.setCode(feignResp.getCode());
			result.setMsg(feignResp.getMsg());
			if (null == feignResp.getData()) {
				return result;
			}
			List<UserModel> userModelList = feignResp.getData().getUsers();
			if(!CollectionUtils.isEmpty(userModelList)){
				for (UserModel userModel : userModelList) {
					FeedbackUserListResult.UserModel target = new FeedbackUserListResult.UserModel();
					BeanUtils.copyProperties(userModel, target);
					users.add(target);
				}
			}
		}
		result.setUsers(users);
		
		return result;
	}
	
	/**
	 * 商户模糊查询
	 * @param req
	 * @return
	 */
	public FeedbackShopListResult queryShopList(ShopPageReq req) {
		FeedbackShopListResult result = new FeedbackShopListResult();
		ShopPageDtoReq dtoReq = new ShopPageDtoReq();
		BeanUtils.copyProperties(req, dtoReq);
		BaseResponse<FeedbackShopListDtoResult> feignResp = feedbackFeignClient.queryShopList(dtoReq);
		List<FeedbackShopListResult.ShopModel> shops = new ArrayList<FeedbackShopListResult.ShopModel>();
		if(feignResp != null){
			result.setCode(feignResp.getCode());
			result.setMsg(feignResp.getMsg());
			if (null == feignResp.getData()) {
				return result;
			}
			List<ShopModel> shopModelList = feignResp.getData().getShopModels();
			if(!CollectionUtils.isEmpty(shopModelList)){
				for (ShopModel shopModel : shopModelList) {
					FeedbackShopListResult.ShopModel target = new FeedbackShopListResult.ShopModel();
					BeanUtils.copyProperties(shopModel, target);
					shops.add(target);
				}
			}
		}
		result.setShopModels(shops);
		
		return result;
	}

}
