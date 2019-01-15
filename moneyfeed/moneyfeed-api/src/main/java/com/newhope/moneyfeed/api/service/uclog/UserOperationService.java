package com.newhope.moneyfeed.api.service.uclog;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;

/**
 * Created by liming on 2018/11/29.
 */
public interface UserOperationService {

    /**
     * 保存用户操作记录
     * @param userOperationLogDtoReq
     * @return
     */
    DtoResult saveUserOperation(UserOperationLogDtoReq userOperationLogDtoReq);

}