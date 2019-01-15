package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderDetailModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderDetailExModel;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderDetailService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderDetailDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yuyanlin on 2018/11/20
 */
@Service
public class EbsOrderDetailServiceImpl extends BaseService<EbsOrderDetailModel> implements EbsOrderDetailService {

    @Autowired
    private EbsOrderDetailDao ebsOrderDetailDao;


    @Override
    protected BaseDao<EbsOrderDetailModel> getDao() {
        return ebsOrderDetailDao;
    }


    @Override
    public List<EbsOrderDetailExModel> queryEbsOrderDetailExModelList(EbsOrderDetailExModel ebsOrderDetailExSearch) {
        return ebsOrderDetailDao.queryEbsOrderDetailExModelList(ebsOrderDetailExSearch);
    }

    @Override
    public Map<String, List<String>> queryCompanyCodeEbsOrderNoListMapFromDetailExList(List<EbsOrderDetailExModel> ebsOrderDetailExList) {
        Map<String, List<String>> companyCodeEbsOrderNoListMap = Maps.newHashMap();

        if (CollectionUtils.isEmpty(ebsOrderDetailExList)) {
            return companyCodeEbsOrderNoListMap;
        }

        for (EbsOrderDetailExModel ebsOrderDetailEx : ebsOrderDetailExList) {
            if (null == companyCodeEbsOrderNoListMap.get(ebsOrderDetailEx.getCompanyCode())) {
                List<String> ebsOrderNoList = Lists.newArrayList();

                ebsOrderNoList.add(ebsOrderDetailEx.getEbsOrderNo());
            } else {
                companyCodeEbsOrderNoListMap.get(ebsOrderDetailEx.getCompanyCode()).add(ebsOrderDetailEx.getEbsOrderNo());
            }
        }

        return companyCodeEbsOrderNoListMap;
    }


	@Override
	public List<EbsOrderDetailExModel> queryLockWarehouseDetailList(String orgId, Date planDate) {
		return  ebsOrderDetailDao.queryLockWarehouseDetailList(orgId, planDate);
	}


}
