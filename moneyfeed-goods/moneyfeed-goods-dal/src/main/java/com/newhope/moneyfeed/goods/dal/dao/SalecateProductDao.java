package com.newhope.moneyfeed.goods.dal.dao;

import com.newhope.moneyfeed.goods.api.bean.SalecateProductModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalecateProductDao extends BaseDao<SalecateProductModel> {

    Long batchUpdate(@Param("param") List<SalecateProductModel> updateSalecateProduct);
}