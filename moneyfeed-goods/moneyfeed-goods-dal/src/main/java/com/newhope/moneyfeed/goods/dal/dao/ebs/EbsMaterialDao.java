package com.newhope.moneyfeed.goods.dal.dao.ebs;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newhope.moneyfeed.goods.api.bean.ebs.EbsMaterialModel;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;

public interface EbsMaterialDao extends BaseDao<EbsMaterialModel> {

    /**
     * 根据状态统计商品
     *
     * @param oneCat
     * @param orgId
     * @return
     */
    Long queryByEbsMaterialStatusCount(@Param("oneCat") String oneCat, @Param("orgId") String orgId);

    /**
     * 查询商品数据
     *
     * @param start
     * @param pageSize
     * @param orgId
     * @return
     */
    List<EbsMaterialModel> queryEbsMaterialList(@Param("start") Long start, @Param("pageSize") Long pageSize,
                                                @Param("oneCat") String oneCat, @Param("orgId") String orgId);

    List<String> selectWeightSukList(@Param("fromDate") Date fromDate);
}