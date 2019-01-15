package com.newhope.openapi.web.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.request.user.UserRemoveDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserRoleModel;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserRoleModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmUserRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.platform.ResourceQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RolePostDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RoleQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserRoleListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.UcClientUserRoleListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.PmResourceListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmUserRoleListDtoResult;
import com.newhope.moneyfeed.user.api.enums.platform.SysSourceTypeEnums;
import com.newhope.moneyfeed.user.api.exbean.platform.UcPmResourceExModel;
import com.newhope.openapi.api.rest.user.PmRoleOpenAPI;
import com.newhope.openapi.api.vo.request.user.PmRoleQueyReq;
import com.newhope.openapi.api.vo.request.user.RolePostReq;
import com.newhope.openapi.api.vo.response.user.PmResourceResult;
import com.newhope.openapi.api.vo.response.user.PmRoleIntroListResult;
import com.newhope.openapi.api.vo.response.user.PmRoleIntroResult;
import com.newhope.openapi.biz.rpc.feign.user.BaseUserFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.BmUserFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.ClientUserFeginClient;
import com.newhope.openapi.biz.rpc.feign.user.PmRoleFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.PmUserFeignClient;
import com.newhope.openapi.biz.service.user.PmRoleService;

@RestController
public class PmRoleOpenController extends AbstractController implements PmRoleOpenAPI {

	@Autowired
	PmRoleFeignClient pmRoleFeignClient;
	
	@Autowired
	PmRoleService pmRoleService;
	
	@Autowired
	BaseUserFeignClient baseUserFeignClient;
	
	@Autowired
	PmUserFeignClient pmUserFeignClient;
	
	@Autowired
	ClientUserFeginClient clientUserFeginClient;
	
	@Autowired
	BmUserFeignClient bmUserFeignClient;
	
	@Override
	public BaseResponse<PmRoleIntroListResult> getRole(@RequestBody PmRoleQueyReq requestBody) {
		RoleQueryDtoReq queryParam = new RoleQueryDtoReq();
		queryParam.setPage(requestBody.getPage());
		queryParam.setPageSize(requestBody.getPageSize());
		queryParam.setSourceType(requestBody.getSourceType());
		queryParam.setId(requestBody.getId());
		queryParam.setName(requestBody.getName());
		PmRoleIntroListResult result = new PmRoleIntroListResult();
		BaseResponse<UcPmRoleListDtoResult> feignResult = pmRoleFeignClient.getRole(queryParam);
		if(feignResult.isSuccess()&&feignResult.getData()!=null
				&&CollectionUtils.isNotEmpty(feignResult.getData().getRoleList())){
			for(UcPmRoleModel role:feignResult.getData().getRoleList()){
				PmRoleIntroResult intro = new PmRoleIntroResult();
				intro.setCode(role.getCode());
				intro.setComment(role.getComment());
				intro.setEnable(role.getEnable());
				intro.setId(role.getId());
				intro.setName(role.getName());
				intro.setSourceType(role.getSourceType());
				result.getRoles().add(intro);
			}
			result.setPage(feignResult.getData().getPage());
			result.setTotalPage(feignResult.getData().getTotalPage());
			result.setTotalCount(feignResult.getData().getTotalCount());
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result);
	}
	
	@Override
	public BaseResponse<PmRoleIntroListResult> getRoleList(@RequestBody PmRoleQueyReq requestBody) {
		RoleQueryDtoReq queryParam = new RoleQueryDtoReq();
		queryParam.setPage(requestBody.getPage());
		queryParam.setPageSize(requestBody.getPageSize());
		queryParam.setSourceType(requestBody.getSourceType());
		queryParam.setId(requestBody.getId());
		queryParam.setName(requestBody.getName());
		PmRoleIntroListResult result = new PmRoleIntroListResult();
		BaseResponse<UcPmRoleListDtoResult> feignResult = pmRoleFeignClient.getRoleList(queryParam);
		if(feignResult.isSuccess()&&feignResult.getData()!=null
				&&CollectionUtils.isNotEmpty(feignResult.getData().getRoleList())){
			for(UcPmRoleModel role:feignResult.getData().getRoleList()){
				PmRoleIntroResult intro = new PmRoleIntroResult();
				intro.setCode(role.getCode());
				intro.setComment(role.getComment());
				intro.setEnable(role.getEnable());
				intro.setId(role.getId());
				intro.setName(role.getName());
				intro.setSourceType(role.getSourceType());
				result.getRoles().add(intro);
			}
			result.setPage(feignResult.getData().getPage());
			result.setTotalPage(feignResult.getData().getTotalPage());
			result.setTotalCount(feignResult.getData().getTotalCount());
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result);
	}

	@Override
	public BaseResponse<PmResourceResult> getRoleResource(@RequestParam(name="sourceType",required=true) String sourceType,
			@RequestParam(name="roleId",required=false) Long roleId) {
		PmResourceResult result = pmRoleService.getRoleResource(sourceType, roleId);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> addRole(@Valid @RequestBody RolePostReq requestBody) {
		RolePostDtoReq postParam = new RolePostDtoReq();
		postParam.setComment(requestBody.getComment());
		postParam.setEnable(requestBody.getEnable());
		postParam.setName(requestBody.getName());
		postParam.setResourceIdList(requestBody.getResourceIdList());
		postParam.setSourceType(requestBody.getSourceType());
		BaseResponse<DtoResult> feignResult = pmRoleFeignClient.addRole(postParam);
		Result result = new Result();
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> modifyRole(@Valid @RequestBody RolePostReq requestBody) {
		Result result = new Result();

		ResourceQueryDtoReq resourceParam = new ResourceQueryDtoReq();
		resourceParam.setEnable(true);
		resourceParam.setSourceType(requestBody.getSourceType());
		resourceParam.setRoleId(requestBody.getId());
		BaseResponse<PmResourceListDtoResult> feignResultResource = pmRoleFeignClient.getResource(resourceParam);
		List<Long> resourceList = new ArrayList<>();
		if(feignResultResource.isSuccess()) {
			for (UcPmResourceExModel ucPmResourceExModel : feignResultResource.getData().getResourceList()) {
				resourceList.add(ucPmResourceExModel.getId());
			}
		}

		RolePostDtoReq postParam = new RolePostDtoReq();
		postParam.setComment(requestBody.getComment());
		postParam.setEnable(true);
		postParam.setId(requestBody.getId());
		postParam.setName(requestBody.getName());
		postParam.setResourceIdList(requestBody.getResourceIdList());
		postParam.setSourceType(requestBody.getSourceType());
		BaseResponse<DtoResult> feignResult = pmRoleFeignClient.modifyRole(postParam);
		
		if(feignResult.isSuccess() && (!postParam.getEnable() || !resourceList.equals(requestBody.getResourceIdList()))) {//禁用角色，删除对应人员的登录缓存
			UserRemoveDtoReq userRemoveDtoReq = new UserRemoveDtoReq();
			List<Long> userIds = new ArrayList<>();
			
			//删除商户端用户缓存
			if(SysSourceTypeEnums.valueOf(requestBody.getSourceType()) == SysSourceTypeEnums.BUSINESS) {
				userIds.clear();
				BaseResponse<UcBmUserRoleListDtoResult> userRoleFeignResult = bmUserFeignClient.getUserIdByRole(requestBody.getId());
				if(userRoleFeignResult.isSuccess() && CollectionUtils.isNotEmpty(userRoleFeignResult.getData().getUserRoleList())) {
					for (UcBmUserRoleModel userRoleModel : userRoleFeignResult.getData().getUserRoleList()) {
						userIds.add(userRoleModel.getUserId());
					}
					userRemoveDtoReq.setThirdSource(SysSourceTypeEnums.BUSINESS.name());
				}
			}
			//删除微信端用户缓存
			if(SysSourceTypeEnums.valueOf(requestBody.getSourceType()) == SysSourceTypeEnums.CLIENT) {
				userIds.clear();
				
				BaseResponse<UcClientUserRoleListDtoResult> userRoleFeignResult = clientUserFeginClient.getUserIdByRole(requestBody.getId());
				if(userRoleFeignResult.isSuccess() && CollectionUtils.isNotEmpty(userRoleFeignResult.getData().getUserRoleList())) {
					for (UcClientUserRoleModel userRoleModel : userRoleFeignResult.getData().getUserRoleList()) {
						userIds.add(userRoleModel.getUserId());
					}
					userRemoveDtoReq.setThirdSource(CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
				}
			}
			//删除平台端用户缓存
			if(SysSourceTypeEnums.valueOf(requestBody.getSourceType()) == SysSourceTypeEnums.PLATFORM) {
				userIds.clear();
				BaseResponse<UcPmUserRoleListDtoResult> userRoleFeignResult = pmUserFeignClient.getUserIdByRole(requestBody.getId());
				if(userRoleFeignResult.isSuccess() && CollectionUtils.isNotEmpty(userRoleFeignResult.getData().getUserRoleList())) {
					for (UcPmUserRoleModel userRoleModel : userRoleFeignResult.getData().getUserRoleList()) {
						userIds.add(userRoleModel.getUserId());
					}
					userRemoveDtoReq.setThirdSource(SysSourceTypeEnums.PLATFORM.name());
				}
			}
			if(!userIds.isEmpty()) {
				userRemoveDtoReq.setUserIds(userIds);
				baseUserFeignClient.removeLoginInfo(userRemoveDtoReq);
			}
		}
		
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}

	@Override
	public void exportRole(PmRoleQueyReq req, HttpServletResponse response) {
		pmRoleService.exportRole(req, response);
	}
	
}
