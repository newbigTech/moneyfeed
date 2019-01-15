package com.newhope.moneyfeed.integration.dal.dao.ebs.baseData;

import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsMaterialModel;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EbsMaterialDao extends BaseDao<EbsMaterialModel> {

    Integer selectExist(@Param("itemId") String itemId,@Param("orgId")String orgId);

    void insertSingle(@Param("model") EbsMaterialModel ebsMaterialModel);
}