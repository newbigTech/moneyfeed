package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.service.BaseService;
import com.newhope.goods.biz.service.CustomerMaterielRelationService;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.goods.api.bean.CustomerMaterielRelationModel;
import com.newhope.moneyfeed.goods.api.dto.request.CustomerMaterielRelationDtoReq;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.CustomerMaterielRelationDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/29 15:24
 */
@Service
public class CustomerMaterielRelationServiceImpl extends BaseService<CustomerMaterielRelationModel> implements CustomerMaterielRelationService {

    @Autowired
    private CustomerMaterielRelationDao customerMaterielRelationDao;


    @Override
    protected BaseDao<CustomerMaterielRelationModel> getDao() {
        return customerMaterielRelationDao;
    }

    @Override
    public DtoResult syncCustomerMaterielRelation(List<CustomerMaterielRelationDtoReq> dtoReqList) {
        DtoResult result = new DtoResult();
        if (CollectionUtils.isEmpty(dtoReqList)) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(ResultCode.FAIL.getDesc());
            return result;
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        List<CustomerMaterielRelationModel> addModel = new ArrayList<>(dtoReqList.size());
        List<CustomerMaterielRelationModel> updateModel = new ArrayList<>(dtoReqList.size());
        for (CustomerMaterielRelationDtoReq relationDtoReq : dtoReqList) {
            CustomerMaterielRelationModel relationModel = new CustomerMaterielRelationModel();
            relationModel.setCustomerNo(relationDtoReq.getCustomerNo());
            relationModel.setMaterielNo(relationDtoReq.getMaterielNo());
            relationModel.setOrgId(relationDtoReq.getOrgId());
            relationModel.setOrganizationCode(relationDtoReq.getOrganizationCode());
            List<CustomerMaterielRelationModel> materielRelationModelList = customerMaterielRelationDao.select(relationModel);
            BeanUtils.copyProperties(relationDtoReq, relationModel);
            if (CollectionUtils.isEmpty(materielRelationModelList)) {
                addModel.add(relationModel);
                continue;
            }
            relationModel.setId(materielRelationModelList.get(0).getId());
            relationModel.setGmtModified(new Date());
            updateModel.add(relationModel);
        }
        if (CollectionUtils.isNotEmpty(addModel)) {
            customerMaterielRelationDao.insert(addModel);
        }

        if (CollectionUtils.isNotEmpty(updateModel)) {
            customerMaterielRelationDao.batchUpdate(updateModel);
        }
        return result;
    }

}
