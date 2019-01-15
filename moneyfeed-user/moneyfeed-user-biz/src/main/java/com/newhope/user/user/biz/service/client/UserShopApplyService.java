package com.newhope.user.user.biz.service.client;

import com.google.common.collect.Lists;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserShopApplyModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ModifyUserShopApplyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.UserShopApplyDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.UserApplyListResult;
import com.newhope.moneyfeed.user.api.enums.client.AllotCustomerStatusEnums;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcClientUserShopApplyDao;
import com.newhope.user.user.biz.service.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserShopApplyService extends BaseService<UcClientUserShopApplyModel> {
    @Autowired
    private UcClientUserShopApplyDao applyDao;

    public DtoResult create(UserShopApplyDtoReq request) {
        /// checkExist(request); 检查是否已经留有意向。可能会用到。
        UcClientUserShopApplyModel model = wrapModel(request);
        final long inserted = applyDao.insert(Lists.newArrayList(model));
        if (inserted > 0) {
            return DtoResult.success();
        } else {
            return DtoResult.fail(ResultCode.FAIL);
        }
    }

    public UserApplyListResult find(UserShopApplyDtoReq request) {
        UcClientUserShopApplyModel conditions = new UcClientUserShopApplyModel();
        conditions.setStatus(AllotCustomerStatusEnums.ALLOTTING.getValue());
        conditions.setEnable(true);
        conditions.setClientUserId(request.getUserId());
        conditions.setMobile(request.getPhone());
        List<UcClientUserShopApplyModel> queryResult = applyDao.select(conditions);
        UserApplyListResult result = new UserApplyListResult();
        result.setList(queryResult);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        return result;
    }


    @Override
    protected BaseDao<UcClientUserShopApplyModel> getDao() {
        return applyDao;
    }

    private void checkExist(UserShopApplyDtoReq request) {
        UcClientUserShopApplyModel conditions = new UcClientUserShopApplyModel();
        conditions.setIntentionShopId(request.getShopId());
        conditions.setClientUserId(request.getUserId());
        conditions.setStatus(AllotCustomerStatusEnums.ALLOTTING.getValue());
        conditions.setEnable(true);
        if (!applyDao.select(conditions).isEmpty()) {
            throw new BizException(ResultCode.FAIL.getCode(), "已经有您的注册信息，请不要重复提交");
        }
    }

    private UcClientUserShopApplyModel wrapModel(UserShopApplyDtoReq request) {
        UcClientUserShopApplyModel model = new UcClientUserShopApplyModel();
        model.setAddress(request.getAddress());
        model.setName(request.getName());
        model.setMobile(request.getPhone());
        model.setComment(request.getComment());
        model.setIntentionShopId(request.getShopId());
        model.setClientUserId(request.getUserId());
        model.setStatus(AllotCustomerStatusEnums.ALLOTTING.getValue());
        model.setEnable(true);
        model.setGmtCreated(new Date());
        return model;
    }

    @Transactional
    public DtoResult modify(ModifyUserShopApplyDtoReq modifyUserShopApplyDtoReq) {
        DtoResult result = new DtoResult();

        UcClientUserShopApplyModel model = new UcClientUserShopApplyModel();
        model.setId(modifyUserShopApplyDtoReq.getApplyId());
        model.setEnable(true);
        List<UcClientUserShopApplyModel> modelList = this.query(model);
        if (CollectionUtils.isEmpty(modelList)) {
            result.setCode(ResultCode.PARAM_ERROR.getCode());
            result.setMsg(ResultCode.PARAM_ERROR.getDesc());
            return result;
        }

        UcClientUserShopApplyModel newModel = new UcClientUserShopApplyModel();
        BeanUtils.copyProperties(modifyUserShopApplyDtoReq, newModel);
        if (null != modifyUserShopApplyDtoReq.getShopId()) {
            newModel.setAllotShopId(modifyUserShopApplyDtoReq.getShopId());
            newModel.setStatus(AllotCustomerStatusEnums.ALLOTTED.getValue());
        }
        boolean r = this.update(modelList.get(0), newModel);
        if (r) {
            return DtoResult.success();
        } else {
            return DtoResult.fail(ResultCode.FAIL);
        }
    }
}
