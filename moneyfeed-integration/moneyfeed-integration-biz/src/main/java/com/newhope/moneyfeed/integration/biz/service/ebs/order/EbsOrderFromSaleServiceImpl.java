package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromSaleModel;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderFromSaleService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderFromSaleDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuyanlin on 2018/11/22
 */
@Service
public class EbsOrderFromSaleServiceImpl extends BaseService<EbsOrderFromSaleModel> implements EbsOrderFromSaleService {

    @Autowired
    private EbsOrderFromSaleDao ebsOrderFromSaleDao;

    @Override
    protected BaseDao<EbsOrderFromSaleModel> getDao() {
        return ebsOrderFromSaleDao;
    }

    @Override
    public EbsOrderFromSaleModel queryOrderFromSaleByEbsOrderId(Long ebsOrderId) {
        if (null == ebsOrderId) {
            return null;
        }

        EbsOrderFromSaleModel ebsOrderFromSaleSearch = new EbsOrderFromSaleModel();
        ebsOrderFromSaleSearch.setOrderId(ebsOrderId);

        List<EbsOrderFromSaleModel> ebsOrderFromSaleList = query(ebsOrderFromSaleSearch);

        if (CollectionUtils.isEmpty(ebsOrderFromSaleList)) {
            return null;
        }

        return ebsOrderFromSaleList.get(0);
    }

}
