package com.newhope.moneyfeed.integration.biz.service.ebs;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.config.HttpMessageSender;
import com.newhope.moneyfeed.common.util.EBSInterfaceUtil;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCategoryModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCustomerModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsMaterialModel;
import com.newhope.moneyfeed.integration.api.enums.common.EBSInterfaceIfcodeEnum;
import com.newhope.moneyfeed.integration.api.service.SyncEBSDataService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.CategoryReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.CustomerReq;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.MaterialReq;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.EBSResponeseCategoryListData;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.EBSResponseMaterialListData;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.EbsCustomerList;
import com.newhope.moneyfeed.integration.biz.rpc.feign.base.UserFeignClient;
import com.newhope.moneyfeed.integration.biz.util.StringConvertUtils;
import com.newhope.moneyfeed.integration.dal.dao.ebs.baseData.EbsCategoryDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.baseData.EbsCustomerDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.baseData.EbsMaterialDao;
import com.newhope.moneyfeed.user.api.dto.request.client.EbsCustomerListPostDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.EbsCustomerPostDtoReq;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by liming on 2018/7/9.
 */
@Service
public class SyncEBSDataServiceImpl implements SyncEBSDataService {

	private final static Logger logger = LoggerFactory.getLogger(SyncEBSDataServiceImpl.class);

	@Autowired
	private HttpMessageSender httpMessageSender;

	@Autowired
	private EbsCategoryDao ebsCategoryDao;

	@Autowired
	private EbsMaterialDao ebsMaterialDao;

	@Autowired
	private EbsCustomerDao ebsCustomerDao;

	@Autowired
	private UserFeignClient userFeignClient;

	//private final static String EBS_CATEGORY_IFCODE = "JBZ005ITEMKIND001";

	//private final static String EBS_MATERIAL_IFCODE = "JBZ003ITEM001";

	//private final static String EBS_CUSTOMER_IFCODE = "JBZ004CUSTOMER001";

	@Value("${ebs.integration.url}")
	private String ebsInterfaceUrl;

	@Override
	public Result syncEbsCategoryCheckParam(CategoryReq req) {

		Result result = new Result();
		try {
			// 本地保存源数据
			syncEBSCategory(req);

			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} catch (Exception e) {
			logger.error("同步ebs物料品类出错", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(e.getMessage());
		}

		return result;
	}

	/**
	 * 同步ebs品类
	 * 
	 * @param req
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void syncEBSCategory(CategoryReq req) throws Exception {

		// 获取ebs请求头报文
		//final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(EBS_CATEGORY_IFCODE, req);
		final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_CATEGORY_IFCODE.getIfcode(), req);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EBSResponeseCategoryListData ebsResponeseCategoryListData = EBSInterfaceUtil
				.convertEBSResponseXMLtoObject(ebsResponseMsg, EBSResponeseCategoryListData.class);

		if (ebsResponeseCategoryListData == null
				|| CollectionUtils.isEmpty(ebsResponeseCategoryListData.getDataList())) {
			logger.info("--------ebs category return null-----------------------");
			return;
		}
		// 本地保存源数据
		checkCategorySaveOrUpdate(ebsResponeseCategoryListData.getDataList());
	}

	/**
	 * 判断品类是新增还是更新
	 * 
	 * @param list
	 */
	private void checkCategorySaveOrUpdate(List<EbsCategoryModel> list) {
		logger.info("----category--开始判断增量数据，长度为[{}]--------", list.size());
		Date currentDate = new Date();
		EbsCategoryModel old = new EbsCategoryModel();
		for (EbsCategoryModel ebsCategoryModel : list) {
			Integer i = ebsCategoryDao.selectExist(ebsCategoryModel.getCategoryId());
			if (i > 0) {
				// 存在物料，修改
				old.setCategoryId(ebsCategoryModel.getCategoryId());
				ebsCategoryDao.update(old, ebsCategoryModel);
			} else {
				// 不存在新增
				ebsCategoryModel.setGmtCreated(currentDate);
				ebsCategoryDao.insertSingle(ebsCategoryModel);
			}
		}
	}

	@Override
	public Result syncEBSMaterialCheckParam(MaterialReq materialReq) {

		Result result = new Result();
		try {
			syncEBSMaterial(materialReq);
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		} catch (Exception e) {
			logger.error("同步ebs物料出错：", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}

		return result;
	}

	/**
	 * 同步ebs物料
	 * 
	 * @param req
	 * @throws Exception
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void syncEBSMaterial(MaterialReq req) throws Exception {

		// 获取ebs请求头报文
		//final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(EBS_MATERIAL_IFCODE, req);
		final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_MATERIAL_IFCODE.getIfcode(), req);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EBSResponseMaterialListData ebsResponseMaterialListData = EBSInterfaceUtil
				.convertEBSResponseXMLtoObject(ebsResponseMsg, EBSResponseMaterialListData.class);

		if (ebsResponseMaterialListData == null || CollectionUtils.isEmpty(ebsResponseMaterialListData.getDataList())) {
			logger.info("--------ebs material return null-----------------------");
			return;
		}

		// 本地保存源数据
		checkMaterialSaveOrUpdate(ebsResponseMaterialListData.getDataList());

		logger.info("-------------------同步[{}]公司物料完成------------------------------------", req.getCompanyCode());

	}

	/**
	 * 判断物料是新增还是删除
	 * 
	 * @param list
	 */
	private void checkMaterialSaveOrUpdate(List<EbsMaterialModel> list) {
		logger.info("----Material--开始判断增量数据，长度为[{}]--------", list.size());
		Date currentDate = new Date();
		EbsMaterialModel old = new EbsMaterialModel();
		for (EbsMaterialModel materialModel : list) {
			Integer i = ebsMaterialDao.selectExist(materialModel.getItemId(), materialModel.getOrgId());
			if (i > 0) {
				// 存在物料，修改
				old.setItemId(materialModel.getItemId());
				old.setOrgId(materialModel.getOrgId());
				ebsMaterialDao.update(old, materialModel);
			} else {
				// 不存在新增
				materialModel.setGmtCreated(currentDate);
				ebsMaterialDao.insertSingle(materialModel);
			}
		}
	}

	public DtoResult syncEBSCustomerCheckParam(CustomerReq customerReq) {

		DtoResult result = new DtoResult();
		try {
			result = syncEbsCustomer(customerReq);
		} catch (Exception e) {
			logger.error("同步ebs客户出错：", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}

		return result;
	}

	/**
	 * xml方式请求ebs
	 * 
	 * @param customerReq
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DtoResult syncEbsCustomer(CustomerReq customerReq) throws Exception {
		DtoResult result = new DtoResult();

		// 获取ebs请求头报文
		//final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(EBS_CUSTOMER_IFCODE, customerReq);
		final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_CUSTOMER_IFCODE.getIfcode(), customerReq);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		final EbsCustomerList ebsCustomerList = EBSInterfaceUtil.convertEBSResponseXMLtoObject(ebsResponseMsg,
				EbsCustomerList.class);

		if (ebsCustomerList == null || CollectionUtils.isEmpty(ebsCustomerList.getDataList())) {
			logger.info("--------未获取到客户数据--------------------公司编码：" + customerReq.getCompanyCode());
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
			return result;
		}

		result = checkCustomerSaveOrUpdate(ebsCustomerList.getDataList());

		if (result.getCode().equals(ResultCode.FAIL.getCode())) {
			return result;
		}

		// 同步客户数据至用户中心
		result = postCustomerToUser(ebsCustomerList.getDataList());

		return result;
	}

	/**
	 * 判断ebs客户新增或者修改
	 * 
	 * @param list
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	private DtoResult checkCustomerSaveOrUpdate(List<EbsCustomerModel> list) {

		logger.info("*******start保存客户数据至中台,公司：" + list.get(0).getCompanyName());

		DtoResult result = new DtoResult();

		try {
			EbsCustomerModel oldCustomer = new EbsCustomerModel();

			list.stream().forEach(ebsCustomerModel -> {

				ebsCustomerModel = StringConvertUtils.convertNullToEmptyString(ebsCustomerModel);

				final Integer num = ebsCustomerDao.selectExist(ebsCustomerModel.getCompanyShortCode(),
						ebsCustomerModel.getCustomerNum(), ebsCustomerModel.getPersonId());
				if (num > 0) {
					oldCustomer.setCustomerNum(ebsCustomerModel.getCustomerNum());
					oldCustomer.setCompanyShortCode(ebsCustomerModel.getCompanyShortCode());
					oldCustomer.setPersonId(ebsCustomerModel.getPersonId());
					ebsCustomerDao.update(oldCustomer, ebsCustomerModel);
				} else {
					ebsCustomerDao.insert(Arrays.asList(ebsCustomerModel));
				}
			});

			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());

			logger.info("***end保存客户数据至中台结束*******");

		} catch (Exception e) {
			logger.error("保存客户数据至中台异常：" + e.getMessage());
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
		return result;
	}

	/**
	 * 同步客户数据至用户中心
	 **/
	DtoResult postCustomerToUser(List<EbsCustomerModel> list) {

		DtoResult result = new DtoResult();
		
		try {
			
			logger.info("********start同步客户数据至用户中心,公司：" + list.get(0).getCompanyName());
			
			Long count = 0L;
			EbsCustomerListPostDtoReq listDtoReq = new EbsCustomerListPostDtoReq();
			List<EbsCustomerPostDtoReq> dtoListReq = new ArrayList<EbsCustomerPostDtoReq>();

			for (EbsCustomerModel model : list) {
				EbsCustomerPostDtoReq dtoReq = new EbsCustomerPostDtoReq();
				dtoReq.setOrgId(model.getOrgId());
				dtoReq.setOrgName(model.getCompanyName());
				dtoReq.setCustomerNum(model.getCustomerNum());
				dtoReq.setCustomerName(model.getCustomerName());
				dtoReq.setCustomerAsName(model.getCustomerKnowAs());
				dtoReq.setCustomerType(model.getCategory3());
				dtoReq.setCustomerCardNum(model.getTaxReference());
				dtoReq.setContactName(model.getContactPerson());
				dtoReq.setContactPhone(model.getPhoneNumber());
				dtoReq.setCustomerAddr(model.getAddress());
				dtoReq.setContactId(model.getPersonId());
				if (model.getStatus().equals("A")) {
					dtoReq.setEnable(true);
				} else {
					dtoReq.setEnable(false);
				}

				dtoListReq.add(dtoReq);

				// 50条数据传一次用户中心
				if (dtoListReq.size() >= 20) {
					count = count + 1;
					listDtoReq.setDataList(dtoListReq);

					logger.info("开始同步客户数据至用户中心,第" + count.toString() + "次,共"+dtoListReq.size()+"个客户。" + "\n" + JSON.toJSONString(listDtoReq));

					BaseResponse<DtoResult> response = userFeignClient.modifyEbsCustomerList(listDtoReq);

					logger.info("同步客户数据至用户中心完成,第" + count.toString() + "次" + "\n" + response.getData().getCode()
							+ "----" + response.getData().getMsg());

					dtoListReq.clear();
				}
			}

			if (dtoListReq.size() > 0) {
				count = count + 1;
				listDtoReq.setDataList(dtoListReq);

				logger.info("开始同步客户数据至用户中心,第" + count.toString() + "次,共"+dtoListReq.size()+"个客户。" + "\n" + JSON.toJSONString(listDtoReq));

				BaseResponse<DtoResult> response = userFeignClient.modifyEbsCustomerList(listDtoReq);

				logger.info("同步客户数据至用户中心完成,第" + count.toString() + "次" + "\n" + response.getData().getCode() + "----"
						+ response.getData().getMsg());
			}

			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
			
			logger.info("*********end同步客户数据至用户中心,公司：" + list.get(0).getCompanyName());

		} catch (Exception e) {
			logger.error("同步客户数据至用户中心异常：" + e.getMessage());
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}

		return result;
	}

}