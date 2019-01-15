package com.newhope.openapi.web.controller.user;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerCarDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerCarQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarListDtoResult;
import com.newhope.openapi.api.rest.user.CustomerCarManageOpenAPI;
import com.newhope.openapi.api.vo.request.user.CustomerCarReq;
import com.newhope.openapi.api.vo.response.user.CustomerCarListResult;
import com.newhope.openapi.api.vo.response.user.CustomerCarResult;
import com.newhope.openapi.biz.rpc.feign.user.CustomerCarManageFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerCarManageController extends AbstractController implements CustomerCarManageOpenAPI {
    @Autowired
    private CustomerCarManageFeignClient client;
    @Autowired
    private RSessionCache rSessionCache;

    @Override
    public BaseResponse<Result> saveOrUpdate(@Valid @RequestBody CustomerCarReq request) {
        CustomerCarDtoReq params = new CustomerCarDtoReq();
        params.setCarNum(request.getCarNum());
        params.setDefaultFlag(request.getDefaultFlag());
        params.setDriverCardNum(request.getDriverCardNum());
        params.setDriverMobile(request.getDriverMobile());
        params.setId(request.getId());
        params.setDriverName(request.getDriverName());
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        params.setUserId(currentUser.getUser().getId());
        BaseResponse<DtoResult> feignResult = client.saveOrUpdate(params);
        Result result = new Result();
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        return buildJson(result);
    }

    @Override
    public BaseResponse<CustomerCarListResult> find(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        CustomerCarQueryDtoReq params = new CustomerCarQueryDtoReq();
        params.setPage((long) page);
        params.setPageSize(pageSize);
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        params.setCustomerId(currentUser.getCustomer().getCustomer().getId());
        BaseResponse<CustomerCarListDtoResult> feignResult = client.find(params);
        CustomerCarListDtoResult data = feignResult.getData();
        CustomerCarListResult result = new CustomerCarListResult();
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        if (data != null) {
            result.translate(data.getCars());
            result.setPage(feignResult.getData().getPage());
            result.setTotalCount(feignResult.getData().getTotalCount());
            result.setTotalPage(feignResult.getData().getTotalPage());
        }
        return buildJson(result);
    }

    @Override
    public BaseResponse<CustomerCarResult> detail(@PathVariable("id") Long id) {
        CustomerCarQueryDtoReq params = new CustomerCarQueryDtoReq();
        params.setPage(1L);
        params.setPageSize(1);
        params.setId(id);
        BaseResponse<CustomerCarListDtoResult> feignResult = client.find(params);
        CustomerCarListDtoResult data = feignResult.getData();
        CustomerCarResult result = new CustomerCarResult();
        if (data != null && CollectionUtils.isNotEmpty(data.getCars())) {
            result = result.translate(data.getCars().get(0));
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMsg(ResultCode.SUCCESS.getDesc());
        } else {
            result.setCode(ResultCode.USER_CAR_NOT_FOUND.getCode());
            result.setMsg(ResultCode.USER_CAR_NOT_FOUND.getDesc());
        }
        return buildJson(result);
    }

    @Override
    public BaseResponse<CustomerCarResult> defaultCar() {
        CustomerCarQueryDtoReq params = new CustomerCarQueryDtoReq();
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        params.setCustomerId(currentUser.getCustomer().getCustomer().getId());
        BaseResponse<CustomerCarDtoResult> feignResult = client.defaultCar(params);
        CustomerCarResult result = new CustomerCarResult();
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        if (feignResult.isSuccess() && feignResult.getData() != null) {
            result.setEnable(feignResult.getData().getEnable());
            result.setDriverName(feignResult.getData().getDriverName());
            result.setDriverCardNum(feignResult.getData().getDriverCardNum());
            result.setDriverMobile(feignResult.getData().getDriverMobile());
            result.setDefaultFlag(feignResult.getData().getDefaultFlag());
            result.setCustomerId(feignResult.getData().getCustomerId());
            result.setCarNum(feignResult.getData().getCarNum());
            result.setId(feignResult.getData().getId());
        }
        return buildJson(result);
    }
    
    @Override
    public BaseResponse<Result> remove(@PathVariable("id") Long id) {
        CustomerCarDtoReq params = new CustomerCarDtoReq();
        params.setId(id);
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        params.setUserId(currentUser.getUser().getId());
        BaseResponse<DtoResult> feignResult = client.remove(params);
        Result result = new Result();
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        return buildJson(result);
    }
    
}
