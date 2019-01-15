package com.newhope.openapi.biz.service.shop;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.constant.StatisticsConstant;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.common.util.ExportUtil;
import com.newhope.moneyfeed.user.api.constant.CommonConstant;
import com.newhope.moneyfeed.user.api.dto.request.platform.ShopDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.ShopModifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.ShopPageDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopListDtoResult;
import com.newhope.moneyfeed.user.api.enums.platform.ShopStatusEnums;
import com.newhope.openapi.api.vo.request.shop.PmShopPageReq;
import com.newhope.openapi.api.vo.request.shop.ShopModifyReq;
import com.newhope.openapi.api.vo.request.shop.ShopReq;
import com.newhope.openapi.api.vo.response.shop.UcPmShopListResult;
import com.newhope.openapi.api.vo.response.shop.UcPmShopResult;
import com.newhope.openapi.biz.rpc.feign.shop.PmShopFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoListResult;
import com.newhope.openapi.api.vo.request.shop.ShopQueryReq;
import com.newhope.openapi.api.vo.response.shop.ShopListResult;
import com.newhope.openapi.api.vo.response.shop.ShopResult;
import com.newhope.openapi.biz.rpc.feign.shop.ShopFeignClient;

import javax.servlet.http.HttpServletResponse;

@Service
public class ShopService {

    private Logger logger = Logger.getLogger(ShopService.class);
    @Autowired
    ShopFeignClient shopFeignClient;
    @Autowired
    PmShopFeignClient pmShopFeignClient;

    public ShopListResult queryShop(ShopQueryReq shopQueryReq) {
        ShopListResult result = new ShopListResult();

        ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
        BeanUtils.copyProperties(shopQueryReq, queryDtoReq);
        BaseResponse<ShopDtoListResult> feignResp = shopFeignClient.queryShop(queryDtoReq);

        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());

        if (!feignResp.isSuccess() || null == feignResp.getData()
                || CollectionUtils.isEmpty(feignResp.getData().getShops())) {
            return result;
        }

        List<ShopResult> shopList = new ArrayList<>();
        ShopResult shopResult = null;
        for (UcShopModel ucShopModel : feignResp.getData().getShops()) {
            shopResult = new ShopResult();
            BeanUtils.copyProperties(ucShopModel, shopResult);
            shopList.add(shopResult);
        }
        result.setShopList(shopList);

        result.setPage(shopQueryReq.getPage());
        result.setTotalCount(feignResp.getData().getTotalCount());
        result.setTotalPage(feignResp.getData().getTotalPage());
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());

        result.setCode(ResultCode.SUCCESS.getCode());
        return result;
    }

    public Result createShop(ShopReq requestBody) {
        Result result = new Result();
        ShopDtoReq request = new ShopDtoReq();
        BeanUtils.copyProperties(requestBody, request);
        BaseResponse<DtoResult> feignResp = pmShopFeignClient.createShop(request);
        BeanUtils.copyProperties(feignResp, result);
        return result;
    }

    public UcPmShopListResult shopList(PmShopPageReq requestBody) {
        UcPmShopListResult result = new UcPmShopListResult();
        ShopPageDtoReq request = new ShopPageDtoReq();
        BeanUtils.copyProperties(requestBody, request);
        try {
            if (StringUtils.isNotEmpty(requestBody.getStartDate())) {
                request.setStartDate(DateUtils.datetimeFormat.parse(requestBody.getStartDate()));
            }
            if(StringUtils.isNotEmpty(requestBody.getEndDate())){
                request.setEndDate(DateUtils.datetimeFormat.parse(requestBody.getEndDate()));
            }

        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("日期格式转换错误");
        }
        BaseResponse<UcPmShopListDtoResult> feignResp = pmShopFeignClient.shopList(request);
        BeanUtils.copyProperties(feignResp.getData(), result);
        return result;
    }

    public Result modifyShop(ShopModifyReq requestBody) {
        Result result = new Result();
        ShopModifyDtoReq request = new ShopModifyDtoReq();
        BeanUtils.copyProperties(requestBody, request);
        BaseResponse<DtoResult> feignResp = pmShopFeignClient.modifyShop(request);
        BeanUtils.copyProperties(feignResp, result);
        return result;
    }

    public UcPmShopResult shopDetail(Long shopId) {
        UcPmShopResult result = new UcPmShopResult();
        BaseResponse<UcPmShopDtoResult> feignResp = pmShopFeignClient.shopDetail(shopId);
        BeanUtils.copyProperties(feignResp.getData(), result);
        return result;
    }

    public Result exportShopList(PmShopPageReq requestBody, HttpServletResponse response) {
        requestBody.setPage(null);
        requestBody.setPageSize(null);
        UcPmShopListResult result = shopList(requestBody);
        if (ResultCode.SUCCESS.getCode().equals(result.getCode()) && CollectionUtils.isNotEmpty(result.getDataList())) {
            {
                List<UcPmShopResult> dataList = result.getDataList();
                List<Map<String, Object>> content = new ArrayList<>();
                Map<String, Object> map;
                for (Object dataResult : dataList) {
                    UcPmShopDtoResult data = new UcPmShopDtoResult();
                    BeanUtils.copyProperties(dataResult, data);
                    map = new HashMap<>();
                    map.put("shopName", StringUtils.isEmpty(data.getShopName()) ? CommonConstant.BLANK_STRING : data.getShopName());
                    map.put("orgName", StringUtils.isEmpty(data.getOrgName()) ? CommonConstant.BLANK_STRING : data.getOrgName());
                    map.put("shortCode", StringUtils.isEmpty(data.getShortCode()) ? CommonConstant.BLANK_STRING : data.getShortCode());
                    map.put("contactPerson", StringUtils.isEmpty(data.getContactPerson()) ? CommonConstant.BLANK_STRING : data.getContactPerson());
                    map.put("contactTel", StringUtils.isEmpty(data.getContactTel()) ? CommonConstant.BLANK_STRING : data.getContactTel());
                    map.put("status", StringUtils.isEmpty(data.getStatus()) ? CommonConstant.BLANK_STRING : ShopStatusEnums.valueOf(data.getStatus()).getDesc());
                    map.put("gmtCreated", data.getGmtCreated() == null ? CommonConstant.BLANK_STRING : DateUtils.formatDate(data.getGmtCreated()));
                    content.add(map);
                }

                // map映射key
                String mapKey = "shopName,orgName,shortCode,status,contactPerson,contactTel,gmtCreated";
                try {
                    final OutputStream os = response.getOutputStream();
                    ExportUtil.responseSetProperties(StatisticsConstant.PM_EXPORT_SHOP, response);
                    ExportUtil.doExport(content, StatisticsConstant.COLUMNS_PM_EXPORT_SHOP,
                            StatisticsConstant.CSV_COLUMN_SEPARATOR, StatisticsConstant.CSV_RN, mapKey, os);
                } catch (Exception e) {
                    logger.error("[ShopService.exportShopList]:下载文件异常", e);
                }
            }
        }

        return Result.success();

    }
}
