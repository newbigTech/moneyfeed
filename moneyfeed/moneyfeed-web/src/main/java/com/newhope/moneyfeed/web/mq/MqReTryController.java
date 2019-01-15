package com.newhope.moneyfeed.web.mq;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.rest.mq.MqRetryAPI;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.biz.sys.ReSendMqMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liming on 2018/12/19.
 */
@RestController
public class MqReTryController  extends AbstractController implements MqRetryAPI{

    @Autowired
    private ReSendMqMessageService reSendMqMessageService;

    @Override
    public BaseResponse<DtoResult> mqRetry() {
        reSendMqMessageService.reSendMqMessage();
        return buildJson(DtoResult.success());
    }
}