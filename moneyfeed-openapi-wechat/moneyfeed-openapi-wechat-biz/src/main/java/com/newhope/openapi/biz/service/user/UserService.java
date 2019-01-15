package com.newhope.openapi.biz.service.user;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ModifyUserInfoBySmscodeDtoReq;
import com.newhope.openapi.api.vo.request.user.ModifyUserInfoBySmscodeReq;
import com.newhope.openapi.biz.rpc.feign.user.UserFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.util.ContextHolderUtil;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.openapi.biz.rpc.feign.user.ClientUserFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.ShopFeignClient;

@Service
public class UserService {

	@Autowired
	RSessionCache rSessionCache;

	@Autowired
	ShopFeignClient shopFeignClient;

	@Autowired
	ClientUserFeignClient clientUserFeignClient;
	@Autowired
	UserFeignClient userFeignClient;
	/**
	 * 缓存用户聚合信息
	 * 
	 * @param req
	 * @throws BizException
	 */
	public ClientUserCacheDtoResult cacheUserAggrInfo(Long userId, String appSource,Integer timeout) throws BizException {
		ClientUserQueryDtoReq userQuery = new ClientUserQueryDtoReq();
		userQuery.setId(userId);
		BaseResponse<ClientUserCacheDtoResult> feignResult = clientUserFeignClient.assemblyUserCache(userId);
		if (feignResult.isSuccess() && feignResult.getData() != null) {
			if(timeout==null){
				rSessionCache.setUserInfo(ContextHolderUtil.getResponse(), feignResult.getData(), String.valueOf(userId), appSource);
			}else{
				rSessionCache.setUserInfo(ContextHolderUtil.getResponse(), feignResult.getData(), String.valueOf(userId), appSource, timeout);
			}
			
		}
		return feignResult.getData();
	}

	public Result modifyUserInfo(ModifyUserInfoBySmscodeReq requestBody) {
		Result result = new Result();
		ModifyUserInfoBySmscodeDtoReq request = new ModifyUserInfoBySmscodeDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<DtoResult> feignResp = clientUserFeignClient.modifyUserInfo(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}
}
