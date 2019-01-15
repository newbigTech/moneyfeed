package com.newhope.moneyfeed.user.dal.dao.client;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerCarModel;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcCustomerCarDao extends BaseDao<UcCustomerCarModel> {
    PageList<UcCustomerCarModel> select(UcCustomerCarModel model, PageBounds pageBounds);
}