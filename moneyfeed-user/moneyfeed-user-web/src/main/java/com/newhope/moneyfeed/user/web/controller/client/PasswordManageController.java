package com.newhope.moneyfeed.user.web.controller.client;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.PasswordManageDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.PayPasswordVerifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.PasswordQueryDtoResult;
import com.newhope.moneyfeed.user.api.rest.client.PasswordManageAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.client.PasswordManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PasswordManageController extends AbstractController implements PasswordManageAPI {

    @Autowired
    private PasswordManageService service;

    @Override
    public BaseResponse<DtoResult> create(@Valid @RequestBody PasswordManageDtoReq request) {
        DtoResult result = service.save(request);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<DtoResult> update(@Valid @RequestBody PasswordManageDtoReq request) {
        DtoResult result = service.update(request);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<PasswordQueryDtoResult> query(@RequestParam("userId") Long userId) {
        PasswordQueryDtoResult result = service.query(userId);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

	@Override
	public BaseResponse<DtoResult> verify(@Valid @RequestBody PayPasswordVerifyDtoReq request) {
		DtoResult result = service.verify(request);
		return buildJsonWithData(result.getCode(), result.getMsg(), result);
	}
}
