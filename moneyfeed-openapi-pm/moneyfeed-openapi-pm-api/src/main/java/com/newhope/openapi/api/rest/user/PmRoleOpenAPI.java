package com.newhope.openapi.api.rest.user;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.user.PmRoleQueyReq;
import com.newhope.openapi.api.vo.request.user.RolePostReq;
import com.newhope.openapi.api.vo.response.user.PmResourceResult;
import com.newhope.openapi.api.vo.response.user.PmRoleIntroListResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "role", description = "系统权限中心", protocols = "http")
public interface PmRoleOpenAPI {
	
	@ApiOperation(value = "查询平台权限", notes = "查询平台权限", protocols = "http")
	@RequestMapping(value = "/role/query", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<PmRoleIntroListResult> getRole(@Valid @RequestBody PmRoleQueyReq requestBody);
	
	@ApiOperation(value = "查询平台权限列表", notes = "查询平台权限列表", protocols = "http")
	@RequestMapping(value = "/role/list", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<PmRoleIntroListResult> getRoleList(@Valid @RequestBody PmRoleQueyReq requestBody);

	@ApiOperation(value = "平台权限列表导出", notes = "平台权限列表导出", protocols = "http")
	@RequestMapping(value = "/role/export", method = RequestMethod.GET)
	public void exportRole( PmRoleQueyReq req, HttpServletResponse response);

	@ApiOperation(value = "添加平台权限", notes = "添加平台权限", protocols = "http")
	@RequestMapping(value = "/role/add", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> addRole(@Valid @RequestBody RolePostReq requestBody);
	
	@ApiOperation(value = "编辑平台权限", notes = "编辑平台权限", protocols = "http")
	@RequestMapping(value = "/role/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> modifyRole(@Valid @RequestBody RolePostReq requestBody);
	
	@ApiOperation(value = "查询平台角色对应资源列表", notes = "查询平台角色对应资源列表", protocols = "http")
	@RequestMapping(value = "/resource/query", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<PmResourceResult> getRoleResource(@RequestParam(name="sourceType",required=true) String sourceType,
			@RequestParam(name="roleId",required=false) Long roleId);
}
