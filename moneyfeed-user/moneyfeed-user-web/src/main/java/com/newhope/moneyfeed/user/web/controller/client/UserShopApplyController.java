package com.newhope.moneyfeed.user.web.controller.client;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.ModifyUserShopApplyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.UserShopApplyDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.UserApplyListResult;
import com.newhope.moneyfeed.user.api.rest.client.UserShopApplyAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.client.UserShopApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserShopApplyController extends AbstractController implements UserShopApplyAPI {
    @Autowired
    private UserShopApplyService applyService;

    @Override
    public BaseResponse<DtoResult> create(@Valid @RequestBody UserShopApplyDtoReq request) {
        DtoResult result = applyService.create(request);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<DtoResult> allotIntentionCustomer(
            @Valid @RequestBody ModifyUserShopApplyDtoReq modifyUserShopApplyDtoReq) {
        DtoResult result = applyService.modify(modifyUserShopApplyDtoReq);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<DtoResult> modifyIntentionCustomerShopDealMsg(
            @Valid @RequestBody ModifyUserShopApplyDtoReq modifyUserShopApplyDtoReq) {
        DtoResult result = applyService.modify(modifyUserShopApplyDtoReq);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<UserApplyListResult> find(@RequestBody UserShopApplyDtoReq request) {
        UserApplyListResult result = applyService.find(request);
        return buildJson(result.getCode(), result.getMsg(), result);
    }
}
