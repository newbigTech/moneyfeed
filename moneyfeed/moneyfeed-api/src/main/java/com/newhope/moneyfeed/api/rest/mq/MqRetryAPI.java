package com.newhope.moneyfeed.api.rest.mq;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liming on 2018/12/19.
 */
@Api(value = "MqRetryAPI", description = "mq重试补偿", protocols = "http")
public interface MqRetryAPI {

    @ApiOperation(value = "mq重试补偿", notes = "mq重试补偿", protocols = "http")
    @RequestMapping(value = "/base/mq/retry", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
    BaseResponse<DtoResult> mqRetry();

}