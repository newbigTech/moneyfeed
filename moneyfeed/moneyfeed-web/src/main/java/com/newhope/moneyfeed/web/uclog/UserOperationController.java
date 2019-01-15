package com.newhope.moneyfeed.web.uclog;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.api.rest.uclog.UserOperationAPI;
import com.newhope.moneyfeed.api.service.uclog.UserOperationService;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by liming on 2018/11/29.
 */
@RestController
public class UserOperationController extends AbstractController implements UserOperationAPI {

    private final static Logger logger= LoggerFactory.getLogger(UserOperationController.class);


    @Autowired
    private UserOperationService userOperationService;


    @Override
    public BaseResponse<DtoResult> recordingUserOperation(@Valid @RequestBody  UserOperationLogDtoReq userOperationLogDtoReq) {
        logger.info("----------------------");
        return buildJson(userOperationService.saveUserOperation(userOperationLogDtoReq));
    }
}