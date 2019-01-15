package com.newhope.openapi.web.controller.user;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserShopApplyModel;
import com.newhope.moneyfeed.user.api.dto.request.client.UserShopApplyDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.UserApplyListResult;
import com.newhope.openapi.api.rest.user.UserShopApplyOpenAPI;
import com.newhope.openapi.api.vo.request.user.UserShopApplyReq;
import com.newhope.openapi.api.vo.response.user.ShopApplyCheckResult;
import com.newhope.openapi.biz.rpc.feign.user.UserShopApplyFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShopApplyController extends AbstractController implements UserShopApplyOpenAPI {

    @Autowired
    private UserShopApplyFeignClient client;
    @Autowired
    private RSessionCache rSessionCache;

    @Override
    public BaseResponse<Result> create(@Valid @RequestBody UserShopApplyReq request) {
        BaseResponse<DtoResult> feignResult = client.create(wrapParams(request));
        Result result = new Result();
        result.setMsg(feignResult.getMsg());
        result.setCode(feignResult.getCode());
        return buildJson(result);
    }

    @Override
    public BaseResponse<ShopApplyCheckResult> check() {
        BaseResponse<ShopApplyCheckResult> result = new BaseResponse<>();
        UserShopApplyDtoReq params = new UserShopApplyDtoReq();
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        params.setUserId(currentUser.getUser().getId());
        BaseResponse<UserApplyListResult> feignResult = client.find(params);
        if (feignResult.isSuccess() && feignResult.getData() != null) {
            result.setMsg(ResultCode.SUCCESS.getDesc());
            result.setCode(ResultCode.SUCCESS.getCode());
            if (CollectionUtils.isNotEmpty(feignResult.getData().getList())) {
                List<UcClientUserShopApplyModel> sorted = feignResult.getData().getList().stream().sorted(
                        Comparator.comparing(UcClientUserShopApplyModel::getGmtCreated).reversed()).collect(Collectors.toList());
                result.setSuccess(true);
                ShopApplyCheckResult checkResult = new ShopApplyCheckResult();
                checkResult.setDate(new DateTime(sorted.get(0).getGmtCreated()).toString("yyyy-MM-dd HH:mm:ss"));
                result.setData(checkResult);
            } else {
                result.setSuccess(false);
            }
        } else {
            result.setCode(feignResult.getCode());
            result.setMsg(feignResult.getMsg());
        }
        return result;
    }

    private UserShopApplyDtoReq wrapParams(@Valid @RequestBody UserShopApplyReq request) {
        UserShopApplyDtoReq params = new UserShopApplyDtoReq();
        params.setAddress(request.getAddress());
        params.setComment(request.getComment());
        params.setPhone(request.getPhone());
        params.setShopId(request.getShopId());
        params.setName(request.getName());
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser != null) {
            params.setUserId(currentUser.getUser().getId());
        }
        return params;
    }
}
