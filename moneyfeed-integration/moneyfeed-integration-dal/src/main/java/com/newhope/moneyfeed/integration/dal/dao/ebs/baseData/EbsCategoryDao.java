package com.newhope.moneyfeed.integration.dal.dao.ebs.baseData;

import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCategoryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EbsCategoryDao extends BaseDao<EbsCategoryModel> {


    Integer selectExist(@Param("categoryId") String categoryId);


    void insertSingle(@Param("model") EbsCategoryModel ebsCategoryModel);

}