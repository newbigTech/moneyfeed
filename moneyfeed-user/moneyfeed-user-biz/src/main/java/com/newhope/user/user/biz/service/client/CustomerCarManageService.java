package com.newhope.user.user.biz.service.client;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerCarModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerCarDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerCarQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarListDtoResult;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcCustomerCarDao;
import com.newhope.user.user.biz.service.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerCarManageService extends BaseService<UcCustomerCarModel> {

    @Autowired
    private UcCustomerClientUserMappingService ucCustomerClientUserMappingService;
    @Autowired
    private UcCustomerCarDao carDao;


    public CustomerCarListDtoResult find(CustomerCarQueryDtoReq request) {
        CustomerCarListDtoResult result = new CustomerCarListDtoResult();
        UcCustomerCarModel conditions = new UcCustomerCarModel();
        conditions.setId(request.getId());
        conditions.setCustomerId(request.getCustomerId());
        conditions.setEnable(true);
        PageBounds pageBounds = new PageBounds(request.getPage().intValue(), request.getPageSize());
        PageList<UcCustomerCarModel> pageList = carDao.select(conditions, pageBounds);
        List<CustomerCarDtoResult> cars = pageList.stream().map(m -> {
            CustomerCarDtoResult car = new CustomerCarDtoResult();
            car.setId(m.getId());
            car.setCarNum(m.getCarNum());
            car.setDriverName(m.getDriverName());
            car.setDriverMobile(m.getDriverMobile());
            car.setDriverCardNum(m.getDriverCardNum());
            car.setCustomerId(m.getCustomerId());
            car.setDefaultFlag(m.getDefaultFlag());
            car.setEnable(m.getEnable());
            return car;
        }).collect(Collectors.toList());
        result.setCars(cars);
        Paginator paginator = pageList.getPaginator();
        if (paginator != null) {
            result.setPage((long) paginator.getPage());
            result.setTotalCount((long) paginator.getTotalCount());
            result.setTotalPage((long) paginator.getTotalPages());
        }
        return result;
    }

    public DtoResult saveOrUpdate(CustomerCarDtoReq request) {
        UcCustomerClientUserMappingModel ucCustomerClientUserMappingModel = ucCustomerClientUserMappingService.getCustomerRelationOfUser(request.getUserId());
        checkExist(request, ucCustomerClientUserMappingModel);
        UcCustomerCarModel model = wrapModel(request, ucCustomerClientUserMappingModel);
        if (model.getDefaultFlag()) {
            UcCustomerCarModel conditionsOfDefault = new UcCustomerCarModel();
            conditionsOfDefault.setCustomerId(ucCustomerClientUserMappingModel.getCustomerId());
            UcCustomerCarModel modelOfDefault = new UcCustomerCarModel();
            modelOfDefault.setDefaultFlag(false);
            super.update(conditionsOfDefault, modelOfDefault);
        }
        if (request.getId() != null && request.getId().longValue() > 0) {
            UcCustomerCarModel conditions = new UcCustomerCarModel();
            conditions.setId(request.getId());
            //如果设置为默认车辆，则需要先把其余的设置为非默认
            super.update(conditions, model);
        } else {
            super.save(Lists.newArrayList(model));
        }
        return DtoResult.success();
    }

    public CustomerCarDtoResult findDefaultCar(CustomerCarQueryDtoReq request) {
        UcCustomerCarModel conditions = new UcCustomerCarModel();
        conditions.setCustomerId(request.getCustomerId());
        conditions.setDefaultFlag(true);
        conditions.setEnable(true);
        List<UcCustomerCarModel> ucCustomerCarModels = carDao.select(conditions);
        CustomerCarDtoResult result = new CustomerCarDtoResult();
        if (CollectionUtils.isNotEmpty(ucCustomerCarModels)) {
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMsg(ResultCode.SUCCESS.getDesc());
            final UcCustomerCarModel model = ucCustomerCarModels.get(0);
            result.setCarNum(model.getCarNum());
            result.setCustomerId(model.getCustomerId());
            result.setDefaultFlag(model.getDefaultFlag());
            result.setDriverCardNum(model.getDriverCardNum());
            result.setDriverMobile(model.getDriverMobile());
            result.setEnable(model.getEnable());
            result.setId(model.getId());
            result.setDriverName(model.getDriverName());
        } else {
            result.setCode(ResultCode.USER_CAR_NOT_FOUND.getCode());
            result.setMsg(ResultCode.USER_CAR_NOT_FOUND.getDesc());
        }
        return result;
    }

    private void checkExist(CustomerCarDtoReq request, UcCustomerClientUserMappingModel clientUserMappingModel) {
        UcCustomerCarModel conditions = new UcCustomerCarModel();
        conditions.setCarNum(request.getCarNum());
        conditions.setCustomerId(clientUserMappingModel.getCustomerId());
        conditions.setEnable(true);
        final List<UcCustomerCarModel> select = carDao.select(conditions);
        if (request.getId() == null || request.getId().intValue() == 0 ) {
            if (!select.isEmpty()) {
                throw new BizException(ResultCode.USER_CAR_EXIST);
            }
        } else {
            UcCustomerCarModel updateConditions = new UcCustomerCarModel();
            updateConditions.setId(request.getId());
            // 修改之前的对象
            final List<UcCustomerCarModel> before = carDao.select(updateConditions);
            if (!select.isEmpty()) {
                if (select.size() == 1 && !select.get(0).getCarNum().equals(before.get(0).getCarNum())) {
                    throw new BizException(ResultCode.USER_CAR_EXIST);
                }
            }
        }

    }

    private UcCustomerCarModel wrapModel(CustomerCarDtoReq request, UcCustomerClientUserMappingModel ucCustomerClientUserMappingModel) {
        UcCustomerCarModel model = new UcCustomerCarModel();
        model.setCarNum(request.getCarNum());
        model.setCustomerId(ucCustomerClientUserMappingModel.getCustomerId());
        model.setDefaultFlag(request.getDefaultFlag());
        model.setDriverCardNum(request.getDriverCardNum());
        model.setDriverMobile(request.getDriverMobile());
        model.setDriverName(request.getDriverName());
        model.setEnable(true);
        return model;
    }
    
    public DtoResult remove(CustomerCarDtoReq request) {
        UcCustomerClientUserMappingModel ucCustomerClientUserMappingModel = ucCustomerClientUserMappingService.getCustomerRelationOfUser(request.getUserId());
        UcCustomerCarModel conditions = new UcCustomerCarModel();
        conditions.setId(request.getId());
        conditions.setCustomerId(ucCustomerClientUserMappingModel.getCustomerId());
        conditions.setEnable(true);
        final List<UcCustomerCarModel> select = carDao.select(conditions);
        if (select.isEmpty()) {
            throw new BizException(ResultCode.USER_CAR_NOT_FOUND);
        }
        
        UcCustomerCarModel oldModel = new UcCustomerCarModel();
        oldModel.setId(request.getId());
        oldModel.setCustomerId(ucCustomerClientUserMappingModel.getCustomerId());
        
        UcCustomerCarModel newModel = new UcCustomerCarModel();
        newModel.setEnable(false);
        
        super.update(oldModel, newModel);
        return DtoResult.success();
    }

    @Override
    protected BaseDao<UcCustomerCarModel> getDao() {
        return carDao;
    }
}
