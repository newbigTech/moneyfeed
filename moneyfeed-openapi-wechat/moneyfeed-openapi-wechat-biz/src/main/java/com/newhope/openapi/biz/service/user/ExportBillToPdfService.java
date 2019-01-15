package com.newhope.openapi.biz.service.user;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillDetailModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDetailPageDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDetailPageDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDtoResult;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoListResult;
import com.newhope.openapi.biz.rpc.feign.user.CustomerFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.EbsCustomerBillFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.ShopFeignClient;
import com.newhope.openapi.biz.service.user.value.Bill;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class ExportBillToPdfService {
    @Autowired
    private RSessionCache rSessionCache;
    @Autowired
    private SpringTemplateEngine springTemplateEngine;
    @Autowired
    private EbsCustomerBillFeignClient customerBillFeignClient;
    @Autowired
    private CustomerFeignClient customerFeignClient;
    @Autowired
    private ShopFeignClient shopFeignClient;

    public String exportBillToPdf(Integer year,
                                  Integer month,
                                  String orgId,
                                  String customerNo
    ) throws Exception {
        if (customerNo == null) {
            ClientUserCacheDtoResult user = getCurrentUser();
            customerNo = user.getCustomer().getCustomer().getEbsCustomerNum();
            orgId = user.getVisitShop().getShop().getEbsOrgId();
        }
        final UcCustomerExModel customer = getCustomer(customerNo);
        final String tempPath = String.format("%s%s年%s月%s%s账单.pdf", customer.getShortName(), year, month, customerNo, orgId);
        if (new File(tempPath).exists()) {
            return tempPath;
        }
        Map<String, Object> results = new HashMap<>(1);
        results.put("bill", getBillData(year, month, orgId, customer));
        String htmlText = springTemplateEngine.process("bill-report", new Context(Locale.getDefault(), results));
        Document document = new Document();
        document.setPageSize(PageSize.A3);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(tempPath));
        document.open();
        Reader htmlStringReader = new StringReader(htmlText);
        InputStream targetStream = new ByteArrayInputStream(CharStreams.toString(htmlStringReader).getBytes(Charsets.UTF_8));
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, targetStream, Charset.forName("UTF-8"), new AsianFontProvider());
        htmlStringReader.close();
        targetStream.close();
        document.close();
        return tempPath;
    }

    private UcCustomerExModel getCustomer(String customerNo) {
        CustomerQueryDtoReq customerQueryParams = new CustomerQueryDtoReq();
        customerQueryParams.setCustomerNum(customerNo);
        BaseResponse<CustomerDtoListResult> customerFeignResult = customerFeignClient.queryCustomer(customerQueryParams);
        if (!customerFeignResult.isSuccess()) {
            throw new BizException(customerFeignResult.getCode(), customerFeignResult.getMsg());
        }
        if (customerFeignResult.getData() != null && CollectionUtils.isNotEmpty(customerFeignResult.getData().getCustomers())) {
            return customerFeignResult.getData().getCustomers().get(0);
        }
        throw new BizException(ResultCode.USER_CUSTOMER_NOT_EXIST);
    }

    private Bill getBillData(Integer year, Integer month, String orgId, UcCustomerExModel customer) {
        final EbsCustomerBillDetailPageDtoReq params = wrapDetailParams(year, month, customer.getEbsCustomerNum(), orgId);
        BaseResponse<EbsCustomerBillDetailPageDtoResult> feignResult = customerBillFeignClient.ebsBillDetailQuery(params);
        EbsCustomerBillDetailPageDtoResult detail = feignResult.getData();
        List<EbsCustomerBillDetailModel> models = detail.getDataList();
        Bill bill = new Bill();

        // 查询商户信息及公司信息
        ShopQueryDtoReq shopQueryParams = new ShopQueryDtoReq();
        shopQueryParams.setEbsOrgId(orgId);
        BaseResponse<ShopDtoListResult> shopFeignResult = shopFeignClient.queryShop(shopQueryParams);
        if (shopFeignResult.getData() != null && CollectionUtils.isNotEmpty(shopFeignResult.getData().getShops())) {
            bill.setCompanyName(shopFeignResult.getData().getShops().get(0).getName());
        }
        bill.setCustomerName(customer.getName());
        bill.setDetails(models);
        bill.setDate(new DateTime().withYear(year).withMonthOfYear(month).dayOfMonth().withMaximumValue().toString("yyyy年MM月dd日"));
        bill.setStart(new DateTime().withYear(year).withMonthOfYear(month).dayOfMonth().withMinimumValue().toString("yyyy年MM月dd日"));
        bill.setEnd(new DateTime().withYear(year).withMonthOfYear(month).dayOfMonth().withMaximumValue().toString("yyyy年MM月dd日"));

        // 查询期初余额，期末余额
        BaseResponse<EbsCustomerBillDtoResult> overview = customerBillFeignClient.ebsBillQuery(wrapBillParams(year, month, customer.getEbsCustomerNum(), orgId));
        if (overview.getData() != null) {
            EbsCustomerBillDtoResult result = overview.getData();
            bill.setOpeningBalance(result.getDataList().stream().map(EbsCustomerBillModel::getBeginPlanPayment).reduce(BigDecimal.ZERO, BigDecimal::add));
            bill.setEndingBalance(result.getDataList().stream().map(EbsCustomerBillModel::getEndPlanPayment).reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        return bill;
    }

    private ClientUserCacheDtoResult getCurrentUser() {
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        return currentUser;
    }

    private EbsCustomerBillDetailPageDtoReq wrapDetailParams(Integer year, Integer month, String customerNo, String orgId) {
        EbsCustomerBillDetailPageDtoReq params = new EbsCustomerBillDetailPageDtoReq();
        params.setCustomerNo(customerNo);
        params.setOrgId(orgId);
        params.setYear(year);
        params.setMonth(month);
        return params;
    }

    private EbsCustomerBillDtoReq wrapBillParams(Integer year, Integer month, String customerNo, String orgId) {
        EbsCustomerBillDtoReq params = new EbsCustomerBillDtoReq();
        params.setCustomerNo(customerNo);
        params.setOrgId(orgId);
        params.setMonth(month);
        params.setYear(year);
        return params;
    }

    class AsianFontProvider extends XMLWorkerFontProvider {
        @Override
        public Font getFont(final String fontName, String encoding, float size, final int style) {
            BaseFont bfChinese;
            try {
                bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                return new Font(bfChinese, size, style);
            } catch (DocumentException | IOException ignore) {
            }
            return super.getFont(fontName, encoding, size, style);
        }
    }
}
