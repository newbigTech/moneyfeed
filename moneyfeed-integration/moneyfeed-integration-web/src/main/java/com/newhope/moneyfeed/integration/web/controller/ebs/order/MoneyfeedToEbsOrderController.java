package com.newhope.moneyfeed.integration.web.controller.ebs.order;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCancelDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCreateDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBalanceTypeDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBankCardTypeDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderReCreateDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderRechargeDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderUpdateInfoDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.QueryEbsOrderDetailInfoDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderDetailInfoDtoResult;
import com.newhope.moneyfeed.integration.api.rest.ebs.order.MoneyfeedToEbsOrderAPI;
import com.newhope.moneyfeed.integration.api.service.ebs.order.MoneyfeedToEbsOrderService;

/**
 * Created by yuyanlin on 2018/11/20
 */
@RestController
public class MoneyfeedToEbsOrderController extends AbstractController implements MoneyfeedToEbsOrderAPI {

    @Autowired
    private MoneyfeedToEbsOrderService moneyfeedToEbsOrderService;

    @Override
    public BaseResponse<DtoResult> moneyfeedToEbsOrderCreate(@Valid @RequestBody MoneyfeedToEbsOrderCreateDtoReq moneyfeedToEbsOrderCreateDtoReq) {
        DtoResult result = new DtoResult();
        try {
            result = moneyfeedToEbsOrderService.moneyfeedToEbsOrderCreate(moneyfeedToEbsOrderCreateDtoReq);
        } catch (BizException e) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.FAIL.getCode());
        }

        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> moneyfeedToEbsOrderCancel(@Valid @RequestBody MoneyfeedToEbsOrderCancelDtoReq moneyfeedToEbsOrderCancelDtoReq) {
        DtoResult result = moneyfeedToEbsOrderService.moneyfeedToEbsOrderCancel(moneyfeedToEbsOrderCancelDtoReq);

        return buildJson(result);
    }

    @Override
    public BaseResponse<QueryEbsOrderDetailInfoDtoResult> moneyfeedQueryEbsOrder(@RequestBody QueryEbsOrderDetailInfoDtoReq search) {

        QueryEbsOrderDetailInfoDtoResult result = moneyfeedToEbsOrderService.queryEbsOrderDetailInfo(search);
        return buildJson(result);

        /*
        QueryEbsOrderDetailInfoDtoResult result =new QueryEbsOrderDetailInfoDtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setData(null);

        result.setOrderInfos(new ArrayList<QueryEbsOrderDetailInfoItemDtoResult>());

        for (String id : search.getMoneyfeedOrderIds() ) {

            QueryEbsOrderDetailInfoItemDtoResult item =new QueryEbsOrderDetailInfoItemDtoResult();

            result.getOrderInfos().add(item);

            item.setMoneyfeedOrderId(search.getMoneyfeedOrderIds().get(0).toString());

            item.setCancleTime(null);
            item.setCarNo("川A98H7H");
            item.setCheckinTime(new Date());
            item.setCompanyShortCode("1498");
            item.setDealTime(new Date());
            item.setEbsOrderStatus(EbsOrderStatusEnum.CLOSED.getDesc());
            item.setCloseTime(new Date());
            item.setPlanTransportTime(new Date());
            item.setTransportTime(new Date());
            item.setPlanTransportTime(new Date());
        }


         return buildJson(result);
         */

    }

    @Override
    public BaseResponse<DtoResult> moneyfeedToEbsOrderUpdateInfo(@Valid @RequestBody MoneyfeedToEbsOrderUpdateInfoDtoReq moneyfeedToEbsOrderUpdateInfoDtoReq) {
        DtoResult result = new DtoResult();

        try {
            result = moneyfeedToEbsOrderService.moneyfeedToEbsOrderUpdateInfo(moneyfeedToEbsOrderUpdateInfoDtoReq);
        } catch (BizException e) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.FAIL.getCode());
        }

        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> updateOrderDetailFromEbs() {
        moneyfeedToEbsOrderService.queryEbsOrderDetailInfoJob();
        return buildSuccessJson();
    }

    @Override
    public BaseResponse<DtoResult> resendMQ() {
        moneyfeedToEbsOrderService.resendMQ();
        return buildSuccessJson();
    }

    @Override
    public BaseResponse<DtoResult> moneyfeedToEbsOrderPayInBalanceType(@Valid @RequestBody MoneyfeedToEbsOrderPayInBalanceTypeDtoReq balancePayDtoReq) {
        DtoResult result = new DtoResult();
        try {
            result = moneyfeedToEbsOrderService.moneyfeedToEbsOrderPayInBalanceType(balancePayDtoReq);
        } catch (BizException e) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.FAIL.getCode());
        }

        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> moneyfeedToEbsOrderPayInBankCardType(@Valid @RequestBody MoneyfeedToEbsOrderPayInBankCardTypeDtoReq bankCardPayDtoReq) {
        DtoResult result = new DtoResult();
        try {
            result = moneyfeedToEbsOrderService.moneyfeedToEbsOrderPayInBankCardType(bankCardPayDtoReq);
        } catch (BizException e) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.FAIL.getCode());
        }

        return buildJson(result);
    }

	@Override
	public BaseResponse<DtoResult> moneyfeedToEbsOrderRecharge(
			@Valid @RequestBody MoneyfeedToEbsOrderRechargeDtoReq orderRechargeDtoReq) {
        DtoResult result = new DtoResult();
        try {
            result = moneyfeedToEbsOrderService.moneyfeedToEbsOrderRecharge(orderRechargeDtoReq);
        } catch (BizException e) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.FAIL.getCode());
        }

        return buildJson(result);
    }

	@Override
	public BaseResponse<DtoResult> moneyfeedToEbsOrderCreateAfterCancel(
			@Valid @RequestBody MoneyfeedToEbsOrderReCreateDtoReq orderReCreateDtoReq) {
		DtoResult result = new DtoResult();
        try {
            result = moneyfeedToEbsOrderService.moneyfeedToEbsOrderCreateAfterCancel(orderReCreateDtoReq);
        } catch (BizException e) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.FAIL.getCode());
        }

        return buildJson(result);
	}
}
