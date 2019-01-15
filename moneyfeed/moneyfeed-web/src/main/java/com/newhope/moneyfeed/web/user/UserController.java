package com.newhope.moneyfeed.web.user;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.request.user.UserLoginDtoReq;
import com.newhope.moneyfeed.api.dto.request.user.UserRemoveDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.response.LoginDtoResult;
import com.newhope.moneyfeed.api.rest.user.UserAPI;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.biz.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController implements UserAPI {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserServiceImpl userServiceImpl;

	@Override
	public BaseResponse<LoginDtoResult> login(@RequestBody UserLoginDtoReq requestBody) {
		LoginDtoResult result = userServiceImpl.login(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> removeLoginInfo(@RequestBody UserRemoveDtoReq userRemoveDtoReq) {
		return buildJson(userServiceImpl.loginInfoRemove(userRemoveDtoReq));
	}
}
