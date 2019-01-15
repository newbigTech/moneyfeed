package com.newhope.moneyfeed.user.web.controller.client;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerCarDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerCarQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarListDtoResult;
import com.newhope.moneyfeed.user.api.rest.client.CustomerCarManageAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.client.CustomerCarManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerCarManageController extends AbstractController implements CustomerCarManageAPI {
    @Autowired
    private CustomerCarManageService service;

    @Override
    public BaseResponse<DtoResult> saveOrUpdate(@RequestBody CustomerCarDtoReq request) {
        DtoResult result = service.saveOrUpdate(request);
        return buildJson(result.getCode(), result.getMsg(), null);
    }

    @Override
    public BaseResponse<CustomerCarListDtoResult> find(@RequestBody CustomerCarQueryDtoReq request) {
        return buildJson(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), service.find(request));
    }

    @Override
    public BaseResponse<CustomerCarDtoResult> defaultCar(@RequestBody CustomerCarQueryDtoReq request) {
        CustomerCarDtoResult result = service.findDefaultCar(request);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

	@Override
	public BaseResponse<DtoResult> remove(@RequestBody CustomerCarDtoReq request) {
		DtoResult result = service.remove(request);
        return buildJson(result.getCode(), result.getMsg(), null);
	}
}
