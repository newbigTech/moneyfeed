package com.newhope.moneyfeed.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * 导出Excel文件（导出“XLSX”格式，支持大数据量导出   @see org.apache.poi.ss.SpreadsheetVersion）
 * 
 */
public class ExportMultipleSheetExcel {
	
	private static Logger log = LoggerFactory.getLogger(ExportMultipleSheetExcel.class);
			
	/**
	 * 工作薄对象
	 */
	private SXSSFWorkbook wb;

	
	/**
	 * 注解列表（Object[]{ ExcelField, Field/Method }）
	 */
	List<Object[]> annotationList = Lists.newArrayList();
	
	/**
	 * 构造函数
	 * @param <E>
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param cls 实体对象，通过annotation.ExportField获取标题
	 */
	public <E> ExportMultipleSheetExcel(Map<String, Class<?>> map, Map<String, List<E>> data){
		this.wb = new SXSSFWorkbook(500);
		for (String key : map.keySet()) {
			new ExportExcel(wb, key, map.get(key)).setDataList(data.get(key));
		}
	}
	

	
	/**
	 * 输出数据流
	 * @param os 输出数据流
	 */
	public ExportMultipleSheetExcel write(OutputStream os) throws IOException{
		wb.write(os);
		return this;
	}
	
	/**
	 * 输出到客户端
	 * @param fileName 输出文件名
	 */
	public ExportMultipleSheetExcel write(HttpServletResponse response, String fileName) throws IOException{
		response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        String filename = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename="+filename+"; filename*=utf-8''" + filename);
		write(response.getOutputStream());
		return this;
	}
	
	/**
	 * 输出到文件
	 * @param fileName 输出文件名
	 */
	public ExportMultipleSheetExcel writeFile(String name) throws FileNotFoundException, IOException{
		FileOutputStream os = new FileOutputStream(name);
		this.write(os);
		return this;
	}
	
	/**
	 * 清理临时文件
	 */
	public ExportMultipleSheetExcel dispose(){
		wb.dispose();
		return this;
	}
	
	/**
	 * 导出测试
	 * @param <E>
	 */
	public static void main(String[] args) throws Throwable {
		
//		ApprovalCountExcel ace = new ApprovalCountExcel();
//		ace.setParticipantComName("新希望");
//		ace.setParticipantDeptName("夏津");
//		ace.setActionTime(new Date());
//		
//		ApprovalTimeExcel ate = new ApprovalTimeExcel();
//		ate.setParticipantComName("新希望_1");
//		ate.setParticipantDeptName("龙口");
//		ate.setTotal(300);
//		
//		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
//		map.put("报表1", ApprovalCountExcel.class);
//		map.put("报表2", ApprovalTimeExcel.class);
//		
//		List<ApprovalCountExcel> list1 = Lists.newArrayList();
//		list1.add(ace);
//		
//		List<ApprovalTimeExcel> list2 = Lists.newArrayList();
//		list2.add(ate);
//		
//		Map data = new HashMap();
//		data.put("报表1", list1);
//		data.put("报表2", list2);
//		
//
//		ExportMultipleSheetExcel ee = new ExportMultipleSheetExcel(map, data);		
//		
//		ee.writeFile("target/export.xlsx");
//
//		ee.dispose();
//		
//		log.debug("Export success.");
		
	}

}
