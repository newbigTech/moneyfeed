package com.newhope.openapi.web.controller.user;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.dto.request.client.PasswordManageDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.PasswordQueryDtoResult;
import com.newhope.openapi.api.rest.user.PasswordManageOpenAPI;
import com.newhope.openapi.api.vo.request.user.PasswordManageReq;
import com.newhope.openapi.api.vo.response.user.PasswordQueryResult;
import com.newhope.openapi.biz.rpc.feign.user.PasswordManageFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PasswordManageController extends AbstractController implements PasswordManageOpenAPI {
    @Autowired
    private PasswordManageFeignClient client;
    @Autowired
    private RSessionCache rSessionCache;

    @Override
    public BaseResponse<Result> create(@Valid @RequestBody PasswordManageReq request) {
        PasswordManageDtoReq params = wrapParams(request);
        BaseResponse<DtoResult> feignResult = client.create(params);
        return buildJson(toResult(feignResult));
    }

    @Override
    public BaseResponse<Result> update(@Valid @RequestBody PasswordManageReq request) {
        PasswordManageDtoReq params = wrapParams(request);
        BaseResponse<DtoResult> feignResult = client.update(params);
        return buildJson(toResult(feignResult));
    }

    @Override
    public BaseResponse<PasswordQueryResult> query() {
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        BaseResponse<PasswordQueryDtoResult> feignResult = client.query(currentUser.getUser().getId());
        PasswordQueryResult result = new PasswordQueryResult();
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        if (feignResult.isSuccess()) {
            result.setHasPassword(feignResult.getData().isHasPassword());
            result.setPhone(feignResult.getData().getPhone());
        }
        return buildJson(result);
    }


    private PasswordManageDtoReq wrapParams(PasswordManageReq request) {
        PasswordManageDtoReq params = new PasswordManageDtoReq();
        params.setCode(request.getCode());
        params.setPassword(request.getPassword());
        params.setRepeat(request.getRepeat());
        params.setPhone(request.getPhone());
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        params.setUserId(currentUser.getUser().getId());
        params.setCardNo(request.getCardNo());
        return params;
    }

    private Result toResult(BaseResponse<DtoResult> feignResult) {
        Result result = new Result();
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        return result;
    }
}
