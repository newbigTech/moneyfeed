package com.newhope.moneyfeed.integration.biz.service.ebs.bill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.config.HttpMessageSender;
import com.newhope.moneyfeed.common.util.EBSInterfaceUtil;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCategoryModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillDetailModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDetailPageDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDetailPageDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDtoResult;
import com.newhope.moneyfeed.integration.api.service.ebs.bill.EBSCustomerBillService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.bill.CustomerBillDetailVoReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.bill.CustomerBillVoReq;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.EBSResponeseCategoryListData;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.bill.EbsCustomerBillDetailListData;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.bill.EbsCustomerBillListData;
import com.newhope.moneyfeed.integration.dal.dao.ebs.bill.EbsCustomerBillDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.bill.EbsCustomerBillDetailDao;
import com.newhope.moneyfeed.integration.api.enums.common.EBSInterfaceIfcodeEnum;

@Service
public class EBSCustomerBillServiceImpl implements EBSCustomerBillService {

	private final static Logger logger = LoggerFactory.getLogger(EBSCustomerBillServiceImpl.class);

	@Autowired
	private HttpMessageSender httpMessageSender;

	@Value("${ebs.integration.url}")
	private String ebsInterfaceUrl;

	//private final static String EBS_BILL_IFCODE = "JBZ005ITEMKIND001";

	//private final static String EBS_BILL_DETAIL_IFCODE = "JBZ005ITEMKIND001";

	@Autowired
	private EbsCustomerBillDao billDao;
	@Autowired
	private EbsCustomerBillDetailDao billDetailDao;

	@Override
	public EbsCustomerBillDtoResult ebsBillQuery(EbsCustomerBillDtoReq billReq) {

		logger.info("参数：" + billReq.getOrgId() + ";" + billReq.getCustomerNo() + ";" + billReq.getYear() + ";"
				+ billReq.getMonth());

		EbsCustomerBillDtoResult result = new EbsCustomerBillDtoResult();

		EbsCustomerBillModel billModel = new EbsCustomerBillModel();

		billModel.setOrgId(billReq.getOrgId());
		billModel.setCustomerNo(billReq.getCustomerNo());
		billModel.setYear(billReq.getYear());
		billModel.setMonth(billReq.getMonth());

		List<EbsCustomerBillModel> billModelList = billDao.select(billModel);

		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		result.setDataList(billModelList);

		return result;
	}

	@Override
	public EbsCustomerBillDetailPageDtoResult ebsBillDetailQuery(EbsCustomerBillDetailPageDtoReq billDetailReq) {

		logger.info("参数：" + billDetailReq.getOrgId() + ";" + billDetailReq.getCustomerNo() + ";"
				+ billDetailReq.getYear() + ";" + billDetailReq.getMonth());

		EbsCustomerBillDetailPageDtoResult result = new EbsCustomerBillDetailPageDtoResult();

		EbsCustomerBillDetailModel billDetailModel = new EbsCustomerBillDetailModel();
		billDetailModel.setOrgId(billDetailReq.getOrgId());
		billDetailModel.setCustomerCode(billDetailReq.getCustomerNo());
		billDetailModel.setYear(billDetailReq.getYear());
		billDetailModel.setMonth(billDetailReq.getMonth());

		PageBounds pageBounds = new PageBounds(billDetailReq.getPage().intValue(), billDetailReq.getPageSize(),
				Order.formString(""));

		PageList<EbsCustomerBillDetailModel> pageList = billDetailDao.queryPageList(billDetailModel, pageBounds);

		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		result.setDataList(pageList);

		Paginator paginator = pageList.getPaginator();
		if (paginator != null) {
			result.setPage((long) paginator.getPage());
			result.setTotalCount((long) paginator.getTotalCount());
			result.setTotalPage((long) paginator.getTotalPages());
		}

		return result;

	}

	@Override
	public DtoResult syncCustomerBill(CustomerBillVoReq req) {
		DtoResult result = new DtoResult();
		try {
			//同步客户账单汇总数据
			//syncBill(req);
			
			//同步客户账单明细数据
			//syncBillDetail(req);

			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} catch (Exception e) {
			logger.error("同步客户账单出错", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(e.getMessage());
		}

		return result;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void syncBill(CustomerBillVoReq req) throws Exception {

		logger.info("***********start 同步客户账单汇总,公司"+req.getOrgId());
		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_BILL_IFCODE.getIfcode(), req);

		// 发送请求
		final String responseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EbsCustomerBillListData listData = EBSInterfaceUtil
				.convertEBSResponseXMLtoObject(responseMsg, EbsCustomerBillListData.class);

		if (listData == null|| CollectionUtils.isEmpty(listData.getEbsDataList())) {
			logger.info("--------ebs customer bill return null-----------------------");
			return;
		}
		
		EbsCustomerBillModel oldModel=new EbsCustomerBillModel();
		List<EbsCustomerBillModel> insertModelList=new ArrayList<>();
		for(EbsCustomerBillModel model:listData.getEbsDataList())
		{
			EbsCustomerBillModel queryModel=new EbsCustomerBillModel();
			queryModel.setOrgId(model.getOrgId());
			queryModel.setCustomerNo(model.getCustomerNo());
			queryModel.setYear(model.getYear());
			queryModel.setMonth(model.getMonth());
			
			List<EbsCustomerBillModel> queryListModel=billDao.select(queryModel);
			if(queryListModel.size()>0)
			{
				oldModel.setId(queryListModel.get(0).getId());	
				
				model.setGmtModified(new Date());
				model.setSyncDetailFlag("N");
				
				billDao.update(oldModel, model);
			}
			else
			{
				model.setGmtCreated(new Date());
				model.setGmtModified(new Date());
				model.setCreator(0L);
				model.setModifier(0L);
				model.setDataStatus("normal");
				model.setSyncDetailFlag("N");
				
				insertModelList.add(model);
			}
		}
		
		if(insertModelList.size()>0)
		{
			billDao.insert(insertModelList);
		}
		
		logger.info("***********end 同步客户账单汇总,公司"+req.getOrgId());
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void syncBillDetail(CustomerBillVoReq req) throws Exception {

		logger.info("***********start 同步客户账单明细 ,公司"+req.getOrgId());
		
		//获取未同步对账单明细的对账单汇总
		EbsCustomerBillModel billQueryModel=new EbsCustomerBillModel();
		billQueryModel.setOrgId(req.getOrgId());
		billQueryModel.setSyncDetailFlag("N");
		List<EbsCustomerBillModel> billListModel=billDao.select(billQueryModel);
		
		EbsCustomerBillModel billOldModel=new EbsCustomerBillModel();
		EbsCustomerBillModel billNewModel=new EbsCustomerBillModel();
		for(EbsCustomerBillModel billModel:billListModel)
		{
			CustomerBillDetailVoReq detailReq=new CustomerBillDetailVoReq();
			detailReq.setOrgId(billModel.getOrgId());
			detailReq.setCustomerNo(billModel.getCustomerNo());
			detailReq.setYear(billModel.getYear());
			detailReq.setMonth(billModel.getMonth());
			
			// 获取ebs请求头报文
			final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_BILL_DETAIL_IFCODE.getIfcode(), req);

			// 发送请求
			final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

			// 解析请求
			final EbsCustomerBillDetailListData billDetailListData = EBSInterfaceUtil
					.convertEBSResponseXMLtoObject(ebsResponseMsg, EbsCustomerBillDetailListData.class);

			if (billDetailListData == null|| CollectionUtils.isEmpty(billDetailListData.getEbsDataList())) {
				logger.info("--------ebs bill detail return null-----------------------");
			}
			else
			{
				//删除原来对账单明细				
				EbsCustomerBillDetailModel deleteDetailModel=new EbsCustomerBillDetailModel();
				deleteDetailModel.setOrgId(billModel.getOrgId());
				deleteDetailModel.setCustomerCode(billModel.getCustomerNo());
				deleteDetailModel.setYear(billModel.getYear());
				deleteDetailModel.setMonth(billModel.getMonth());
				
				billDetailDao.delete(deleteDetailModel);
				
				//新增对账单明细数据
				List<EbsCustomerBillDetailModel> insertModelList=new ArrayList<>();
				for(EbsCustomerBillDetailModel model:billDetailListData.getEbsDataList())
				{
					model.setGmtCreated(new Date());
					model.setGmtModified(new Date());
					model.setCreator(0L);
					model.setModifier(0L);
					model.setDataStatus("normal");
					
					insertModelList.add(model);
				}
				
				if(insertModelList.size()>0)
				{
					billDetailDao.insert(insertModelList);
					
					//修改对账单头 “同步状态为 Y”
					billOldModel.setId(billModel.getId());	
					
					billNewModel.setGmtModified(new Date());
					billNewModel.setSyncDetailFlag("Y");
					
					billDao.update(billOldModel, billNewModel);				
				}
			}
		}
		
		logger.info("***********end 同步客户账单明细 ,公司"+req.getOrgId());
			
	}

}
