package com.newhope.openapi.biz.service.helper;

import com.google.common.base.Charsets;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FileNameEncoder {

    private static final String IE = "IE";
    private static final String IE_VERSION = "RV:11";
    private static final String USER_AGENT = "User-Agent";

    private FileNameEncoder() {
    }

    public static String getPreviewHeader(HttpServletRequest request, String name) {
        return "inline; filename=" + encode(request, name);
    }

    public static String getDownloadHeader(HttpServletRequest request, String name) {
        return "attachment; filename=" + encode(request, name);
    }

    private static String encode(HttpServletRequest request, String name) {
        try {
            String userAgent = request.getHeader(USER_AGENT).toUpperCase();
            if (userAgent.indexOf(IE) <= 0 && !userAgent.contains(IE_VERSION)) {
                name = new String(name.getBytes(), Charsets.ISO_8859_1.name());
            } else {
                name = URLEncoder.encode(name, Charsets.UTF_8.name());
            }
            return name;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码格式不支持。" + e.getMessage(), e);
        }
    }
}
