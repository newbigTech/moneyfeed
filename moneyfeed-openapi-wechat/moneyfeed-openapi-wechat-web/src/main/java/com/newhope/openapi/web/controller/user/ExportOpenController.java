package com.newhope.openapi.web.controller.user;

import com.google.common.io.Files;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.openapi.api.rest.user.ExportOpenAPI;
import com.newhope.openapi.biz.service.helper.FileNameEncoder;
import com.newhope.openapi.biz.service.user.ExportBillToPdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class ExportOpenController implements ExportOpenAPI {
    private static final Logger logger = LoggerFactory.getLogger(ExportOpenController.class);
    @Autowired
    private ExportBillToPdfService pdfService;

    @Override
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam Integer year,
                                           @RequestParam Integer month,
                                           @RequestParam(name = "orgId", required = false) String orgId,
                                           @RequestParam(name = "customerNo", required = false) String customerNo
    ) {
        ResponseEntity<byte[]> response;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            String filename = pdfService.exportBillToPdf(year, month, orgId, customerNo);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, FileNameEncoder.getDownloadHeader(request, filename));
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            response = new ResponseEntity<>(Files.toByteArray(new File(filename)), headers, HttpStatus.OK);
            new File(filename).delete();
        } catch (Exception e) {
            logger.error("下载电子账单失败", e);
            throw new BizException(ResultCode.FAIL.getCode(), e.getMessage());
        }
        return response;
    }


    @Override
    public ResponseEntity<byte[]> preview(HttpServletRequest request,
                                          @RequestParam Integer year,
                                          @RequestParam Integer month,
                                          @RequestParam(name = "orgId", required = false) String orgId,
                                          @RequestParam(name = "customerNo", required = false) String customerNo
    ) {
        ResponseEntity<byte[]> response;
        try {
            String filename = pdfService.exportBillToPdf(year, month, orgId, customerNo);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.add(HttpHeaders.CONTENT_DISPOSITION, FileNameEncoder.getPreviewHeader(request, filename));
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            response = new ResponseEntity<>(Files.toByteArray(new File(filename)), headers, HttpStatus.OK);
            new File(filename).delete();
        } catch (Exception e) {
            logger.error("预览电子账单失败", e);
            throw new BizException(ResultCode.FAIL.getCode(), e.getMessage());
        }
        return response;
    }
}
