package com.newhope.moneyfeed.integration.biz.service.ebs.balance;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.common.config.HttpMessageSender;
import com.newhope.moneyfeed.common.util.EBSInterfaceUtil;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.balance.EbsBalanceDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDto;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDtoResult;
import com.newhope.moneyfeed.integration.api.enums.common.EBSInterfaceIfcodeEnum;
import com.newhope.moneyfeed.integration.api.service.ebs.balance.EBSCustomerBalanceService;
import com.newhope.moneyfeed.integration.api.vo.response.ebs.balance.EbsBalanceListData;

@Service
public class EbsCustomerBalanceServiceImpl implements EBSCustomerBalanceService {

	@Autowired
	private HttpMessageSender httpMessageSender;

	private final static Logger logger = LoggerFactory.getLogger(EbsCustomerBalanceServiceImpl.class);

	@Value("${ebs.integration.url}")
	private String ebsInterfaceUrl;

	@Override
	public EbsBalanceDtoResult ebsBalanceQuery(EbsBalanceDtoReq req) {

		EbsBalanceDtoResult result = new EbsBalanceDtoResult();
		logger.info("公司编码：" + req.getOrgCode() + ";客户编码:" + req.getCustomerCode());
		try {

			/*List<EbsBalanceDto> resList = new ArrayList<>();			
			EbsBalanceDto res = new EbsBalanceDto();
			res.setOrgCode(req.getOrgCode());
			res.setCustomerCode(req.getCustomerCode());
			res.setAvailableBalance(new BigDecimal("100"));
			res.setAvailableCredit(new BigDecimal("2000"));
			res.setDeposit(new BigDecimal("3000"));
			res.setBalance(new BigDecimal("500"));
			res.setWhetherInOwe("Y");			
			resList.add(res);
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
			result.setDataList(resList);*/

			 result=queryEbsData(req);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(e.getMessage());
		}

		return result;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public EbsBalanceDtoResult queryEbsData(EbsBalanceDtoReq req) throws Exception {

		EbsBalanceDtoResult result = new EbsBalanceDtoResult();

		// 获取ebs请求头报文
		final String ebsRequestXmlString = EBSInterfaceUtil.getEbsRequestXmlString(EBSInterfaceIfcodeEnum.EBS_BALANCE_IFACE_CODE.getIfcode(), req);

		// 发送请求
		final String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

		// 解析请求
		final EbsBalanceListData listData = EBSInterfaceUtil.convertEBSResponseXMLtoObject(ebsResponseMsg,
				EbsBalanceListData.class);

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
