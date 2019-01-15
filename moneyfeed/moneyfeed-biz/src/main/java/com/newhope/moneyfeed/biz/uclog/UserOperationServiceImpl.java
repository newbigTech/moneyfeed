package com.newhope.moneyfeed.biz.uclog;

import com.google.common.collect.Lists;
import com.newhope.moneyfeed.dal.dao.uc.UcLogDao;
import com.newhope.moneyfeed.api.bean.UcLogModel;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.service.uclog.UserOperationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.awt.image.DataBufferByte;

/**
 * Created by liming on 2018/11/29.
 */
@Service
public class UserOperationServiceImpl implements UserOperationService {


    @Autowired
    private UcLogDao ucLogDao;

    /**
     * 保存用户操作记录
     *
     * @param userOperationLogDtoReq
     * @return
     */
    @Override
    public DtoResult saveUserOperation(UserOperationLogDtoReq userOperationLogDtoReq) {

        UcLogModel ucLogModel=new UcLogModel();
        BeanUtils.copyProperties(userOperationLogDtoReq,ucLogModel);
        if(userOperationLogDtoReq.getUserOperationEnums() == null){
            return DtoResult.fail(ResultCode.FAIL.getCode(),"操作类型不能为空");
        }
        ucLogModel.setEventType(userOperationLogDtoReq.getUserOperEventType().name());
        ucLogModel.setEventOperationType(userOperationLogDtoReq.getUserOperationEnums().getDesc());
        ucLogModel.setSource(userOperationLogDtoReq.getSource().getDesc());
        ucLogModel.setDataStatus("NORMAL");
        final long insert = ucLogDao.insert(Lists.newArrayList(ucLogModel));
        if(insert==1){
            return  DtoResult.success();
        }
        return DtoResult.fail(ResultCode.FAIL);
    }

}