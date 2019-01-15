package com.newhope.moneyfeed.pay.dal.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.pay.api.bean.PayOrderInfoModel;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoQueryDtoReq;
import com.newhope.moneyfeed.pay.dal.BaseDao;
import org.apache.ibatis.annotations.Param;

public interface PayOrderInfoDao extends BaseDao<PayOrderInfoModel> {

    PageList<PayOrderInfoModel> queryList(@Param("param") PayOrderInfoQueryDtoReq queryReq, PageBounds pageBounds);
}