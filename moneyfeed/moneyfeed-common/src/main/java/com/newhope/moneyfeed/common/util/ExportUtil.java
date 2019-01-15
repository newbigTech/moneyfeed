package com.newhope.moneyfeed.common.util;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportUtil {
	
	public static Logger logger = LoggerFactory.getLogger(ExportUtil.class);
	
	/**
	 * 写文件到客户端
	 * 
	 * @param dataList	集合数据
	 * @param colNames	表头部数据
	 * @param separator	列分隔符
	 * @param rn		列换行符
	 * @param mapKey	map映射内容key,see {@PutawayDetailModel} and {@PutawayDetailsImpl.downloadPutawayDetails}
	 * @param os
	 */
	public static void doExport(List<Map<String, Object>> dataList, String colNames, 
			String separator, String rn, String mapKey, OutputStream os) throws Exception {
		try {
			StringBuilder buf = new StringBuilder();
			String[] colNamesArr = colNames.split(",");
			String[] mapKeyArr = mapKey.split(",");
			// 完成数据csv文件的封装
            for (int i = 0; i < colNamesArr.length; i++) {
                buf.append(colNamesArr[i]).append(separator);
            }
            buf.append(rn);
            // 输出数据
            if (null != dataList) { 
                for (int i = 0; i < dataList.size(); i++) {
                    for (int j = 0; j < mapKeyArr.length; j++) {
                        buf.append("\"").append(dataList.get(i).get(mapKeyArr[j]).toString().replaceAll("\"","")).append("\"").append(separator);
                    }
                    buf.append(rn);
                }
            }
            // 写出响应
			//添加bom
			os.write(new   byte []{( byte ) 0xEF ,( byte ) 0xBB ,( byte ) 0xBF });
			os.write(buf.toString().getBytes("UTF-8"));
            os.flush();
		} catch (Exception e) {
			logger.error("doExport错误...", e);
			throw e;
		}finally {
			os.close();
		}
	}
	
	/**
	 * 设置http header
	 * @param fileName
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public static void responseSetProperties(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
		// 设置文件后缀
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fn = fileName + sdf.format(new Date()).toString() + ".csv";
		// 读取字符编码
		String encode = "GBK";
		// 设置响应
		response.setContentType("application/ms-txt.numberformat:@");
		response.setCharacterEncoding(encode);
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30");	
		response.setHeader("Content-Disposition", "attachment; filename=" + new String(fn.getBytes(encode), "ISO8859-1"));
	}
}
