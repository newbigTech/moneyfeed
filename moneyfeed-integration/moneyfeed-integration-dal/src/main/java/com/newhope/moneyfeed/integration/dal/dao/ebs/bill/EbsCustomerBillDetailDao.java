package com.newhope.moneyfeed.integration.dal.dao.ebs.bill;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillDetailModel;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;

public interface EbsCustomerBillDetailDao extends BaseDao<EbsCustomerBillDetailModel> {
	
	public PageList<EbsCustomerBillDetailModel> queryPageList(EbsCustomerBillDetailModel search,PageBounds page);
}