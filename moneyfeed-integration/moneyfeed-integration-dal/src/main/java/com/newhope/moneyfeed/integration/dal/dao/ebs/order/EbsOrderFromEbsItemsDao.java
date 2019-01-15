package com.newhope.moneyfeed.integration.dal.dao.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsItemsModel;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;

import java.util.List;

public interface EbsOrderFromEbsItemsDao extends BaseDao<EbsOrderFromEbsItemsModel> {
     public List<EbsOrderFromEbsItemsModel> exSelect(EbsOrderFromEbsItemsModel search);
 }