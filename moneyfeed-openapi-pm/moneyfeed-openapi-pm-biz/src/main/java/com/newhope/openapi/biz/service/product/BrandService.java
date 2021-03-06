package com.newhope.openapi.biz.service.product;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.util.ExportMultipleSheetExcel;
import com.newhope.moneyfeed.goods.api.dto.request.BrandQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.BrandUpdateDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.BrandDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.BrandListDtoResult;
import com.newhope.openapi.api.vo.request.product.BrandQueryReq;
import com.newhope.openapi.api.vo.request.product.BrandUpdateReq;
import com.newhope.openapi.api.vo.response.product.BrandListResult;
import com.newhope.openapi.api.vo.response.product.BrandResult;
import com.newhope.openapi.biz.rpc.feign.product.BrandFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : tom
 * @project: moneyfeed-openapi-pm
 * @date : 2018/12/24 08:55
 */
@Service
public class BrandService {

    private static final Logger logger = LoggerFactory.getLogger(BrandService.class);

    @Autowired
    private BrandFeignClient brandFeignClient;

    public BrandListResult queryBrandList(BrandQueryReq req) {
        BrandListResult result = new BrandListResult();
        BrandQueryDtoReq dtoReq = new BrandQueryDtoReq();
        BeanUtils.copyProperties(req, dtoReq);

        BaseResponse<BrandListDtoResult> feignResp = brandFeignClient.queryBrandList(dtoReq);
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());

        if (!feignResp.isSuccess() || null == feignResp.getData()
                || CollectionUtils.isEmpty(feignResp.getData().getBrandDtoResults())) {
            return result;
        }

        result.setPage(dtoReq.getPage());
        result.setTotalCount(feignResp.getData().getTotalCount());
        result.setTotalPage(feignResp.getData().getTotalPage());

        List<BrandResult> brandResultList = new ArrayList<>();
        for (BrandDtoResult brandDtoResult : feignResp.getData().getBrandDtoResults()) {
            BrandResult brandResult = new BrandResult();
            BeanUtils.copyProperties(brandDtoResult, brandResult);
            brandResultList.add(brandResult);
        }
        result.setBrandResults(brandResultList);
        return result;
    }

    public Result addBrand(BrandUpdateReq req) {
        Result result = new Result();
        BrandUpdateDtoReq dtoReq = new BrandUpdateDtoReq();
        BeanUtils.copyProperties(req, dtoReq);

        BaseResponse<DtoResult> feignResp = brandFeignClient.addBrand(dtoReq);
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());
        return result;
    }

    public Result updateBrand(BrandUpdateReq req) {

        Result result = new Result();
        BrandUpdateDtoReq dtoReq = new BrandUpdateDtoReq();
        BeanUtils.copyProperties(req, dtoReq);

        BaseResponse<DtoResult> feignResp = brandFeignClient.updateBrand(dtoReq);
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());
        return result;
    }

    public Result deleteBrand(Long id) {
        Result result = new Result();
        BaseResponse<DtoResult> feignResp = brandFeignClient.deleteBrand(id);
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());
        return result;
    }


    public Result brandExport(BrandQueryReq req, HttpServletResponse response) {
        Result result = new Result();
        req.setPageSize(Integer.MAX_VALUE);
        BrandQueryDtoReq dtoReq = new BrandQueryDtoReq();
        BeanUtils.copyProperties(req, dtoReq);

        BaseResponse<BrandListDtoResult> feignResp = brandFeignClient.queryBrandList(dtoReq);
        List<BrandDtoResult> brandDtoResults = feignResp.getData().getBrandDtoResults();

        List<BrandResult> excelDataList = new ArrayList<>();
        for (BrandDtoResult brandDtoResult : brandDtoResults) {
            BrandResult brandResult = new BrandResult();
            BeanUtils.copyProperties(brandDtoResult, brandResult);
            excelDataList.add(brandResult);
        }

        Map<String, Class<?>> map = new HashMap<String, Class<?>>();
        map.put("品牌列表", BrandResult.class);
        Map exDataList = new LinkedHashMap();
        exDataList.put("品牌列表", excelDataList);
        ExportMultipleSheetExcel exportMultipleSheetExcel = new ExportMultipleSheetExcel(map, exDataList);
        String fileName = "品牌列表" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx";
        try {
            exportMultipleSheetExcel.write(response, fileName).dispose();
        } catch (IOException e) {
            logger.error("[BrandService.brandExport", e);
            e.printStackTrace();
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(ResultCode.FAIL.getDesc());
        }
        return result;
    }

    public BrandListResult queryBrandList() {
        BrandListResult result = new BrandListResult();
        BaseResponse<BrandListDtoResult> feignResp = brandFeignClient.queryBrandList();
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());

        if (!feignResp.isSuccess() || null == feignResp.getData()
                || CollectionUtils.isEmpty(feignResp.getData().getBrandDtoResults())) {
            return result;
        }
        List<BrandResult> brandResultList = new ArrayList<>();
        for (BrandDtoResult brandDtoResult : feignResp.getData().getBrandDtoResults()) {
            BrandResult brandResult = new BrandResult();
            BeanUtils.copyProperties(brandDtoResult, brandResult);
            brandResultList.add(brandResult);
        }
        result.setBrandResults(brandResultList);
        return result;
    }
}
