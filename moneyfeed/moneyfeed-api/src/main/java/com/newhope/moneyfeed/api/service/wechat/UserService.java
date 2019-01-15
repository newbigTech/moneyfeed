package com.newhope.moneyfeed.api.service.wechat;

import com.newhope.moneyfeed.api.dto.request.user.UserLoginDtoReq;
import com.newhope.moneyfeed.api.dto.request.user.UserRemoveDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.response.LoginDtoResult;

/**
 * Created by liming on 2018/11/24.
 */
public interface UserService {

    LoginDtoResult loginByOauth(UserLoginDtoReq request);

    DtoResult loginInfoRemove(UserRemoveDtoReq userRemoveDtoReq);

}