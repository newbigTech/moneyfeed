package com.newhope.moneyfeed.user.web.controller.platform;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmResourceModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.platform.ResourceQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RolePostDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RoleQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.PmResourceListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleListDtoResult;
import com.newhope.moneyfeed.user.api.exbean.platform.UcPmResourceExModel;
import com.newhope.moneyfeed.user.api.rest.platform.PmRoleAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.platform.UcPmResourceService;
import com.newhope.user.user.biz.service.platform.UcPmRoleService;

@RestController
public class PmRoleController extends AbstractController implements PmRoleAPI {
	
	@Autowired
	UcPmResourceService ucPmResourceService;
	
	@Autowired
	UcPmRoleService ucPmRoleService;
	
	@Override
	public BaseResponse<UcPmRoleListDtoResult> getRole(@RequestBody RoleQueryDtoReq requestBody) {
		UcPmRoleListDtoResult result = new UcPmRoleListDtoResult();
		PageList<UcPmRoleModel> roleList = ucPmRoleService.queryRole(requestBody);
		Paginator paginator = roleList.getPaginator();
		if(paginator!=null){
			result.setRoleList(roleList);
			result.setPage((long) paginator.getPage());
            result.setTotalCount((long) paginator.getTotalCount());
            result.setTotalPage((long) paginator.getTotalPages());
		}else{
			result.setRoleList(roleList);
			result.setPage(1L);
			result.setTotalCount(0L);
			result.setTotalPage(0L);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<PmResourceListDtoResult> getResource(@RequestBody ResourceQueryDtoReq requestBody) {
		PmResourceListDtoResult result = new PmResourceListDtoResult();
		UcPmResourceModel queryParam = new UcPmResourceModel();
		queryParam.setSourceType(requestBody.getSourceType());
		queryParam.setEnable(true);
		List<UcPmResourceModel> resourceList = ucPmResourceService.query(queryParam);
		if(CollectionUtils.isNotEmpty(resourceList)){
			for(UcPmResourceModel resource :resourceList){
				UcPmResourceExModel ex = new UcPmResourceExModel();
				ex.setName(resource.getName());
				ex.setId(resource.getId());
				ex.setParentId(resource.getParentId());
				ex.setSourceType(resource.getSourceType());
				ex.setType(resource.getType());
				result.getResourceList().add(ex);
			}
		}
		return buildJson(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), result);
	
	}

	@Override
	public BaseResponse<PmResourceListDtoResult> getResourceByRole(@RequestParam(name="sourceType",required=true) String sourceType,
			@RequestParam(name="roleId",required=true) Long roleId) {
		PmResourceListDtoResult result = new PmResourceListDtoResult();
		ResourceQueryDtoReq queryParam = new ResourceQueryDtoReq();
		queryParam.setSourceType(sourceType);
		queryParam.setRoleId(roleId);
		queryParam.setEnable(true);
		result.setResourceList(ucPmResourceService.searchRoleResource(queryParam));
		return buildJson(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), result);
	}

	@Override
	public BaseResponse<DtoResult> addRole(@Valid @RequestBody RolePostDtoReq requestBody) {
		ucPmRoleService.addRole(requestBody);
		return buildJson(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), null);
	}

	@Override
	public BaseResponse<DtoResult> modifyRole(@Valid @RequestBody RolePostDtoReq requestBody) {
		ucPmRoleService.modifyRole(requestBody);
		return buildJson(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), null);
	}
	
	@Override
	public BaseResponse<UcPmRoleListDtoResult> getRoleList(@RequestBody RoleQueryDtoReq requestBody) {
		UcPmRoleListDtoResult result = new UcPmRoleListDtoResult();
		PageList<UcPmRoleModel> roleList = ucPmRoleService.getRoleList(requestBody);
		Paginator paginator = roleList.getPaginator();
		if(paginator!=null){
			result.setRoleList(roleList);
			result.setPage((long) paginator.getPage());
            result.setTotalCount((long) paginator.getTotalCount());
            result.setTotalPage((long) paginator.getTotalPages());
		}else{
			result.setRoleList(roleList);
			result.setPage(1L);
			result.setTotalCount(0L);
			result.setTotalPage(0L);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}
	
}
