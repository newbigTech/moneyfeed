package com.newhope.moneyfeed.integration.dal.dao.ebs.baseData;

import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCustomerModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EbsCustomerDao extends BaseDao<EbsCustomerModel> {

    Integer selectExist(@Param("companyShortCode") String companyShortCode,
                        @Param("cumstomerNum") String cumstomerNum,
                        @Param("personId") String personId);

}