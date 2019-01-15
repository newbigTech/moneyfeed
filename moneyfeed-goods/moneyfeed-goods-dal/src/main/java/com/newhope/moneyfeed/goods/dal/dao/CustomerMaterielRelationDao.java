package com.newhope.moneyfeed.goods.dal.dao;

import com.newhope.moneyfeed.goods.api.bean.CustomerMaterielRelationModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMaterielRelationDao extends BaseDao<CustomerMaterielRelationModel> {

    Long batchUpdate(@Param("param") List<CustomerMaterielRelationModel> updateModel);
}