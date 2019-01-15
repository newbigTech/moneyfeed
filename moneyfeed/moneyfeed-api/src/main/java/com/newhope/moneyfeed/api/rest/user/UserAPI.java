package com.newhope.moneyfeed.api.rest.user;
import com.newhope.moneyfeed.api.dto.request.user.UserLoginDtoReq;
import com.newhope.moneyfeed.api.dto.request.user.UserRemoveDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.response.LoginDtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(value="User", description="用户中心API", protocols="http")
public interface UserAPI {


	@ApiOperation(value="用户统一登录接口", notes="用户统一登录接口", protocols="http")
	@RequestMapping(value = "/user/login", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<LoginDtoResult> login(@RequestBody UserLoginDtoReq requestBody);


	@ApiOperation(value="移除用户登录信息", notes="移除用户登录信息", protocols="http")
	@RequestMapping(value = "/user/remove", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<DtoResult> removeLoginInfo(@RequestBody UserRemoveDtoReq userRemoveDtoReq);

}
