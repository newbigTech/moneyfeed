package com.newhope.order.biz.utils;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.newhope.commons.lang.http.HttpClientUtils;

public class SinaApiUtils {

	private static final String AND = "&";

	private static final String QUESTION_MARK = "?";

	private static final String EQUALS = "=";

	public static class CompressUrl {

		static final String API_URL = "http://api.t.sina.com.cn/short_url/shorten.json?source=3271760578&url_long=";

		static final String URL_SHORT = "url_short";
		
		static final String ERROR_CODE = "error_code";

		public static String compressUrl(String targetUri, Map<String, String> params)throws RuntimeException {

			if (StringUtils.isBlank(targetUri)) {
				throw new NullPointerException("target uri is null");
			}
			StringBuilder apiUrl = new StringBuilder();
			apiUrl.append(API_URL).append(targetUri);
			if (MapUtils.isNotEmpty(params)) {
				int c = 0;
				for (Map.Entry<String, String> entry : params.entrySet()) {
					if (c == 0) {
						apiUrl.append(QUESTION_MARK);
					} else {
						apiUrl.append(AND);
					}
					c++;
					apiUrl.append(entry.getKey()).append(EQUALS).append(entry.getValue());
				}
			}
			String response = HttpClientUtils.get(apiUrl.toString());
			if( StringUtils.isBlank(response) || response.contains(ERROR_CODE)){
				throw new RuntimeException("call sina short url api exception");
			}
			return JSON.parseArray(response).getJSONObject(0).getString(URL_SHORT);
		}
	}
}
