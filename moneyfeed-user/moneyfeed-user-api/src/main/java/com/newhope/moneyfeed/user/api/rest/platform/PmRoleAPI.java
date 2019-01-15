package com.newhope.moneyfeed.user.api.rest.platform;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.platform.ResourceQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RolePostDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RoleQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.PmResourceListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleListDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "PmRole", description = "平台中心REST API", protocols = "http")
public interface PmRoleAPI {
	
	@ApiOperation(value = "查询系统权限", notes = "查询系统权限", protocols = "http")
	@RequestMapping(value = "/pm/role/query", method = RequestMethod.POST)
	public BaseResponse<UcPmRoleListDtoResult> getRole(@RequestBody RoleQueryDtoReq requestBody);
	
	
	@ApiOperation(value = "添加平台权限", notes = "添加平台权限", protocols = "http")
	@RequestMapping(value = "/role/add", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<DtoResult> addRole(@Valid @RequestBody RolePostDtoReq requestBody);
	
	@ApiOperation(value = "修改平台权限", notes = "修改平台权限", protocols = "http")
	@RequestMapping(value = "/role/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<DtoResult> modifyRole(@Valid @RequestBody RolePostDtoReq requestBody);
	
	
	@ApiOperation(value = "查询系统资源", notes = "查询系统资源", protocols = "http")
	@RequestMapping(value = "/pm/resource/query", method = RequestMethod.POST)
	public BaseResponse<PmResourceListDtoResult> getResource(@RequestBody ResourceQueryDtoReq requestBody); 

	@ApiOperation(value = "根据权限查询系统资源", notes = "根据权限查询系统资源", protocols = "http")
	@RequestMapping(value = "/pm/resource/query/role", method = RequestMethod.GET)
	public BaseResponse<PmResourceListDtoResult> getResourceByRole(@RequestParam(name="sourceType",required=true) String sourceType,
			@RequestParam(name="roleId",required=true) Long roleId); 
	
	@ApiOperation(value = "查询系统权限列表", notes = "查询系统权限列表", protocols = "http")
	@RequestMapping(value = "/pm/role/list", method = RequestMethod.POST)
	public BaseResponse<UcPmRoleListDtoResult> getRoleList(@RequestBody RoleQueryDtoReq requestBody);

}
