package com.newhope.moneyfeed.integration.biz.service.ebs.inv;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.common.config.HttpMessageSender;
import com.newhope.moneyfeed.common.util.EBSInterfaceUtil;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.inv.EbsItemOnhandDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.inv.EbsItemOnhandDtoResult;
import com.newhope.moneyfeed.integration.api.enums.common.EBSInterfaceIfcodeEnum;
import com.newhope.moneyfeed.integration.api.service.ebs.inv.EbsInvOnhandService;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.inv.EbsItemOnhandListData;

@Service
public class EbsInvItemOnhandServiceImpl implements EbsInvOnhandService {

	private final static Logger logger = LoggerFactory.getLogger(EbsInvItemOnhandServiceImpl.class);

	@Value("${ebs.integration.url}")
	private String ebsInterfaceUrl;

	@Autowired
	private HttpMessageSender httpMessageSender;

	@Override
	public EbsItemOnhandDtoResult ebsItemOnhandQuery(List<EbsItemOnhandDtoReq> reqList) {

		EbsItemOnhandDtoResult result = new EbsItemOnhandDtoResult();
		logger.info("请求参数：" + JSON.toJSONString(reqList));

		try {
			result = queryEbsData(reqList);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ex.getMessage());
		}
		return result;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public EbsItemOnhandDtoResult queryEbsData(List<EbsItemOnhandDtoReq> reqList) throws Exception {

		EbsItemOnhandDtoResult result = new EbsItemOnhandDtoResult();

		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil
				.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_ITEM_ONHAND_IFCODE.getIfcode(), reqList);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EbsItemOnhandListData listData = EBSInterfaceUtil.convertEBSResponseXMLtoObject(ebsResponseMsg,
				EbsItemOnhandListData.class);

		if (listData == null || CollectionUtils.isEmpty(listData.getEbsDataList())) {
			logger.info("--------ebs category return null-----------------------");
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("未获取到数据");
		} else {
			result.setDataList(listData.getEbsDataList());
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		}

		return result;
	}

}
