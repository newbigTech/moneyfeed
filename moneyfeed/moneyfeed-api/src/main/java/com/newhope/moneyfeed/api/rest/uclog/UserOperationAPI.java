package com.newhope.moneyfeed.api.rest.uclog;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by liming on 2018/11/29.
 */
public interface UserOperationAPI {


    @ApiOperation(value="记录用户操作信息", notes="记录用户操作信息", protocols="http")
    @RequestMapping(value = "base/log/save", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<DtoResult> recordingUserOperation(@Valid @RequestBody  UserOperationLogDtoReq userOperationLogDtoReq);


}