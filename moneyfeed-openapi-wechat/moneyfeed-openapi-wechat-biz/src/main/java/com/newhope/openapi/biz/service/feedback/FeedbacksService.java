package com.newhope.openapi.biz.service.feedback;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.ShopPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackShopListDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackShopListDtoResult.ShopModel;
import com.newhope.openapi.api.vo.request.feedback.FeedbackReq;
import com.newhope.openapi.api.vo.request.feedback.ShopPageReq;
import com.newhope.openapi.api.vo.response.feedback.FeedbackShopListResult;
import com.newhope.openapi.biz.rpc.feign.feedback.FeedbackFeignClient;

@Service
public class FeedbacksService {
	
	@Autowired
	FeedbackFeignClient feedbackFeignClient;
	
	/**
	 * 新增留言记录
	 * @param req
	 * @return
	 */
	public Result feedbackReqAdd(FeedbackReq req) {
		Result result = new Result();
		
		FeedbackDtoReq dtoReq = new FeedbackDtoReq();
		BeanUtils.copyProperties(req, dtoReq);
		BaseResponse<DtoResult> feignResp = feedbackFeignClient.feedbackReqAdd(dtoReq );
		if(feignResp != null){
			result.setCode(feignResp.getCode());
			result.setMsg(feignResp.getMsg());
		}
		
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
