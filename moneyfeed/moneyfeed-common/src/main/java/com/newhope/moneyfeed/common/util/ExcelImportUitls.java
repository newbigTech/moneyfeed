package com.newhope.moneyfeed.common.util;

import com.google.common.collect.Lists;
import com.newhope.moneyfeed.common.util.excel.annotation.MyCell;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liming on 2018/1/11.
 */
public class ExcelImportUitls {

    public static List<Map<String, String>> readWorkbook(Sheet sheet, int skipLineNum,int allRowNum) throws Exception {

        if(skipLineNum < 0) {
            throw new RuntimeException("跳过的行数不能小于0");
        } else {
            List<Map<String, String>> list = new LinkedList();
            final Iterator<Row> rowIterator = sheet.rowIterator();
            int i=1;
            while (rowIterator.hasNext()){
                final Row row = rowIterator.next();
                if(i<=skipLineNum){
                    i++;
                    continue;
                }
                if(row != null) {
                    Map<String, String> map = new LinkedHashMap();
                    for(int c = 0; c < allRowNum; c++) {
                        Cell cell = row.getCell(c);
                        String value=null;
                        if(cell != null) {
                            value = getCellValue(cell);
                        }
                        map.put(String.valueOf(c), value);
                    }
                    list.add(map);
                }
            }
            return list;
        }
    }


    protected static String getCellValue(Cell cell) throws Exception {
        String value = null;
        switch(cell.getCellType()) {
            case 0:
            case 2:
                double doubleVal=0;
                try {
                    doubleVal = cell.getNumericCellValue();
                }catch (Exception e){
                    throw new Exception(cell.getStringCellValue()+"不能转换");
                }
                if(HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date =  cell.getDateCellValue();
                    value= DateUtil.getDateStr(date,DateUtil.YYYY_MM_DD_HH_MM_SS);
                } else {
                    BigDecimal b = new BigDecimal(doubleVal);
                    value = b.toString();
                }
                break;
            case 1:
                value = cell.getStringCellValue();
                break;
            case 3:
                value = "";
                break;
            case 4:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case 5:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                value = "";
        }

        return StringUtils.trim(value);
    }

    public static <T> List<T> MapToBean(List<Map<String, String>> list, Class<T> c) throws Exception {
        String msg="";
        List<T> rsList = new ArrayList();
        List<Field> cellFieldList = new ArrayList();
        Field[] fields = c.getDeclaredFields();
        T o = null;
        String index = null;
        Class<?> fieldType = null;
        String fieldName = null;
        Field[] fieldsMap = fields;
        int var11 = fields.length;

        int i;
        for(i = 0; i < var11; ++i) {
            Field field = fieldsMap[i];
            MyCell myCell = field.getAnnotation(MyCell.class);
            if(null != myCell) {
                cellFieldList.add(field);
            }
        }

        StringBuilder errorMsg = new StringBuilder();

        for(i = 0; i < list.size(); ++i) {
            Map<String, String> map = list.get(i);
            if(null != map && !map.isEmpty()) {
                o = c.newInstance();
                int fieldListSize = cellFieldList.size();
                int emptyFieldCount = 0;
                Iterator cellItorater = cellFieldList.iterator();

                while(cellItorater.hasNext()) {
                    Field field = (Field)cellItorater.next();
                    final MyCell myCell = field.getAnnotation(MyCell.class);
                    index = String.valueOf(myCell.index());
                    String value = map.get(index);
                    if(StringUtils.isBlank(value)) {
                        ++emptyFieldCount;
                    }

                    fieldType = field.getType();
                    fieldName = field.getName();
                    if(String.class.equals(fieldType)) {
                        BeanUtils.setProperty(o, fieldName, value);
                    } else if(Date.class.equals(fieldType)) {
                        if(StringUtils.isNotBlank(value)) {
                            try {
                                BeanUtils.setProperty(o, fieldName, DateUtil.StringToDate(value, myCell.format()));
                            }catch (Exception e){
                                errorMsg.append(msg).append("第").append(i + 4).append("行 [").append(value).append("]不能转换为时间;");
                            }
                        }
                    } else if(BigDecimal.class.equals(fieldType)) {
                        if(StringUtils.isNotBlank(value)) {
                            try {
                                BigDecimal b = new BigDecimal(value);
                                BeanUtils.setProperty(o, fieldName, b);
                            } catch (Exception var22) {
                                errorMsg.append(msg).append("第").append(i + 4).append("行,[").append(value).append("]不能转换为数字;");
                            }
                        }
                    } else if(Integer.class.equals(fieldType)) {
                        if(StringUtils.isNotBlank(value)) {
                            try {
                                Integer b = Integer.valueOf(value);
                                BeanUtils.setProperty(o, fieldName, b);
                            } catch (Exception var21) {
                                errorMsg.append(msg).append("第").append(i + 4).append("行 [").append(value).append("]不能转换为整型;");
                            }
                        }
                    } else if(Double.class.equals(fieldType)) {
                        if(StringUtils.isNotBlank(value)) {
                            try {
                                Double b = Double.valueOf(value);
                                BeanUtils.setProperty(o, fieldName, b);
                            } catch (Exception var20) {
                                errorMsg.append(msg).append("第").append(i + 4).append("行 [").append(value).append("]不能转换为数字;");
                            }
                        }
                    } else if(Float.class.equals(fieldType) && StringUtils.isNotBlank(value)) {
                        try {
                            Float b = Float.valueOf(value);
                            BeanUtils.setProperty(o, fieldName, b);
                        } catch (Exception var19) {
                            errorMsg.append(msg).append("第").append(i + 4).append("行 [").append(value).append("]不能转换为数字;");
                        }
                    }
                }
                if(fieldListSize != emptyFieldCount) {
                    rsList.add(o);
                }
            }
        }
        if(errorMsg.length() > 0) {
            throw new Exception(errorMsg.toString());
        } else {
            return rsList;
        }
    }


    public static <T> List<T> excelImportConvert(InputStream inputStream,Class<T> tClass,String excelType,int SkipLine) throws Exception {
        Workbook wb = null;
        if ("xls".equalsIgnoreCase(excelType)) {
            wb = new HSSFWorkbook(inputStream);
        } else {
            if (!"xlsx".equalsIgnoreCase(excelType) && !"xlsm".equalsIgnoreCase(excelType)) {
                throw new Exception("file is not office excel");
            }
            wb = new XSSFWorkbook(inputStream);
        }
        final Sheet sheetAt = wb.getSheetAt(0);
        int allRowNum=sheetAt.getRow(2).getPhysicalNumberOfCells();
        List<Map<String, String>> mapList = ExcelImportUitls.readWorkbook(sheetAt,SkipLine,allRowNum);
        final List<T> ts = ExcelImportUitls.MapToBean(mapList, tClass);
        return ts;
    }

}