package com.tt.base.Util;


import com.tt.base.cotent.Const;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel 操作类，依赖poi
 */
public class ExcelUtils {

    private final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0");
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(Const.DEFAULT_DATA_FORMAT_PATTERN);

    public static <T> List<T> parseFromExcel(String path, Class<T> aimClass) {
        return parseFromExcel(path, 0, aimClass);
    }

    @SuppressWarnings("deprecation")
    public static <T> List<T> parseFromExcel(String path, int firstIndex, Class<T> aimClass) {
        List<T> result = new ArrayList<T>();
        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook workbook = WorkbookFactory.create(fis);
            //对excel文档的第一页,即sheet1进行操作
            Sheet sheet = workbook.getSheetAt(0);
            int lastRaw = sheet.getLastRowNum();
            for (int i = firstIndex; i <= lastRaw; i++) {
                //第i行
                Row row = sheet.getRow(i);
                T parseObject = aimClass.newInstance();
                Field[] fields = aimClass.getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    Field field = fields[j];
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    //第j列
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }

                    //很重要的一行代码,如果不加,像12345这样的数字是不会给你转成String的,只会给你转成double,而且会导致cell.getStringCellValue()报错
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String cellContent = cell.getStringCellValue();
                    cellContent = "".equals(cellContent) ? "0" : cellContent;
                    if (type.equals(String.class)) {
                        field.set(parseObject, cellContent);
                    } else if (type.equals(char.class) || type.equals(Character.class)) {
                        field.set(parseObject, cellContent.charAt(0));
                    } else if (type.equals(int.class) || type.equals(Integer.class)) {
                        field.set(parseObject, Integer.parseInt(cellContent));
                    } else if (type.equals(long.class) || type.equals(Long.class)) {
                        field.set(parseObject, Long.parseLong(cellContent));
                    } else if (type.equals(float.class) || type.equals(Float.class)) {
                        field.set(parseObject, Float.parseFloat(cellContent));
                    } else if (type.equals(double.class) || type.equals(Double.class)) {
                        field.set(parseObject, Double.parseDouble(cellContent));
                    } else if (type.equals(short.class) || type.equals(Short.class)) {
                        field.set(parseObject, Short.parseShort(cellContent));
                    } else if (type.equals(byte.class) || type.equals(Byte.class)) {
                        field.set(parseObject, Byte.parseByte(cellContent));
                    } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
                        field.set(parseObject, Boolean.parseBoolean(cellContent));
                    }
                }
                result.add(parseObject);
            }
            fis.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //ok

    /**
     * 读取文件第一列数据，并转换为List集合
     *
     * @param inputStream
     * @return
     */
    public final static List<String> read(InputStream inputStream) {

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);

            List<String> rowList = new ArrayList<String>();
            int rowNum = sheet.getLastRowNum();
            for (int i = 0; i <= rowNum; i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getLastCellNum() > 0) {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(row.getCell(0).getStringCellValue())) {
                        rowList.add(row.getCell(0).getStringCellValue());
                    }
                }
            }
            return rowList;
        }
        return Collections.emptyList();
    }
    //ok

    /**
     * 读取excel文件数据，并转换为List集合
     *
     * @param filepath 文件路径
     * @return
     * @throws Exception
     */
    public final static List<List<Object>> readToList(String filepath) throws Exception {

        Workbook workbook = buildWordbook(filepath);

        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);

            List<List<Object>> rowList = new ArrayList<List<Object>>();
            int rowNum = sheet.getLastRowNum();
            for (int i = 0; i <= rowNum; i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getLastCellNum() > 0) {
                    List<Object> cellList = new ArrayList<Object>();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        cellList.add(getCellValue(row.getCell(j)));
                    }
                    rowList.add(cellList);
                }
            }
            return rowList;
        }
        return Collections.emptyList();
    }
    //ok

    /**
     * 读取excel文件数据，并转换为List<List<String>>集合
     *
     * @param filepath 文件路径
     * @return
     * @throws Exception
     */
    public final static List<List<String>> readToStringList(String filepath) throws Exception {

        Workbook workbook = buildWordbook(filepath);

        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);

            List<List<String>> rowList = new ArrayList<List<String>>();
            int rowNum = sheet.getLastRowNum();
            for (int i = 0; i <= rowNum; i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getLastCellNum() > 0) {
                    List<String> cellList = new ArrayList<String>();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                        cellList.add(row.getCell(j).getStringCellValue());
                    }
                    rowList.add(cellList);
                }
            }
            return rowList;
        }
        return Collections.emptyList();
    }
    //ok

    /**
     * 读取excel文件数据，并转换为map集合
     *
     * @param filepath 文件路径
     * @return
     * @throws Exception
     */

    public final static List<Map<String, Object>> readToMap(String filepath) throws Exception {
        Workbook workbook = buildWordbook(filepath);

        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);

            List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();
            int rowNum = sheet.getLastRowNum();
            if (rowNum > 0) {

                List<String> titleList = new ArrayList<String>();
                Row titleRow = sheet.getRow(0);
                for (int i = 0; i < titleRow.getLastCellNum(); i++) {
                    titleList.add(titleRow.getCell(i).getStringCellValue());
                }

                for (int i = 1; i <= rowNum; i++) {
                    Row row = sheet.getRow(i);
                    int cellNum = row.getLastCellNum();
                    if (row != null && cellNum > 0) {
                        Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                        for (int j = 0; j < cellNum; j++) {
                            cellMap.put(titleList.get(j), getCellValue(row.getCell(j)));
                        }
                        rowList.add(cellMap);
                    }
                }
            }
            return rowList;
        }
        return Collections.emptyList();
    }
    //ok

    /**
     * 将map集合导出excel表格
     *
     * @param filepath 导出路径
     * @param dataList 数据集合
     * @param
     * @return
     */
    @SuppressWarnings("resource")
    public final static boolean write(String topName, String filepath, List<Map<String, Object>> dataList) {

        Workbook workbook = null;
        if (StringUtils.endsWith(filepath, ".xls")) {
            workbook = new HSSFWorkbook();
        } else if (StringUtils.endsWith(filepath, ".xlsx")) {
            workbook = new SXSSFWorkbook();
        }

        if (workbook != null) {

            Sheet sheet = workbook.createSheet(topName);

            if (CollectionUtils.isNotEmpty(dataList)) {

                //创建表头
                Row row = sheet.createRow(0);
                Cell cell = null;
                Map<String, Object> tmpMap = dataList.get(0);
                int titleSize = tmpMap.size();
                List<String> titleList = new ArrayList<>(tmpMap.keySet());
                for (int i = 0; i < titleSize; i++) {
                    cell = row.createCell(i);
                    cell.setCellValue(titleList.get(i));
                }

                //填充数据
                int dataSize = dataList.size();
                for (int i = 0; i < dataSize; i++) {
                    row = sheet.createRow(i + 1);
                    tmpMap = dataList.get(i);
                    for (int j = 0; j < tmpMap.size(); j++) {
                        cell = row.createCell(j);
                        cell.setCellValue(String.valueOf(tmpMap.get(titleList.get(j))));
                    }
                }
            }

            if (!new File(filepath).exists()) {
                new File(filepath).getParentFile().mkdirs();
            }

            //导出excel
            try {
                FileOutputStream outputStream = new FileOutputStream(filepath);
                workbook.write(outputStream);
                outputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将对象<T>集合导出为excel表格
     *
     * @param filepath 导出路径
     * @param titleMap 标题字典，key：对象属性名称；value：对象属性编码
     * @param dataList 对象数据集合
     * @param <T>      对象类型
     * @return boolean
     */
    @SuppressWarnings("resource")
    public final static <T> boolean write(String filepath, LinkedHashMap<String, String> titleMap, List<T> dataList) throws Exception {

        Workbook workbook = null;
        if (StringUtils.endsWith(filepath, ".xls")) {
            workbook = new HSSFWorkbook();
        } else if (StringUtils.endsWith(filepath, ".xlsx")) {
            workbook = new SXSSFWorkbook();
        }

        if (workbook != null) {
            Sheet sheet = workbook.createSheet();

            if (MapUtils.isNotEmpty(titleMap) && CollectionUtils.isNotEmpty(dataList)) {

                //创建表头
                Row row = sheet.createRow(0);
                Cell cell = null;
                int index = 0;
                for (Object key : titleMap.keySet()) {
                    cell = row.createCell(index);
                    cell.setCellValue(String.valueOf(titleMap.get(key)));
                    index++;
                }

                //填充数据
                index = 0;
                try {
                    for (T t : dataList) {
                        row = sheet.createRow(index + 1);
                        Field field = null;
                        int j = 0;
                        for (Object key : titleMap.keySet()) {
                            field = t.getClass().getDeclaredField(
                                    String.valueOf(key));
                            field.setAccessible(true);
                            Object data = field.get(t);
                            CellStyle contextstyle = workbook.createCellStyle();
                            cell = row.createCell(j);
                            Boolean isNum = false;// data是否为数值型
                            Boolean isInteger = false;// data是否为整数
                            Boolean isPercent = false;// data是否为百分数
                            if (data != null && !"".equals(data)) {
                                isNum = StringUtils.isNumeric(data.toString());
                                isInteger = StringUtils.isNumeric(data.toString());
                                isPercent = data.toString().contains("%");
                            }
                            if (isNum && !isPercent) {
                                DataFormat df = workbook.createDataFormat(); // 此处设置数据格式
                                if (isInteger) {
                                    contextstyle.setDataFormat(df.getFormat("#,#0"));// 数据格式只显示整数
                                } else {
                                    contextstyle.setDataFormat(df.getFormat("#,##0.00"));// 保留两位小数点
                                }
                                cell.setCellStyle(contextstyle);
                                cell.setCellValue(Double.parseDouble(data.toString()));
                            } else {
                                cell.setCellStyle(contextstyle);
                                if (field.getType().getCanonicalName() == "java.util.Date") {
                                    cell.setCellValue(DATE_FORMAT.format(data));
                                } else {
                                    cell.setCellValue(data.toString());
                                }
                            }
                            j++;
                        }
                        index++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception("input data error");
                }
            }

            if (!new File(filepath).exists()) {
                new File(filepath).getParentFile().mkdirs();
            }

            //导出excel
            try {
                FileOutputStream outputStream = new FileOutputStream(filepath);
                workbook.write(outputStream);
                outputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    //ok

    /**
     * 读取excel文件，并构建Wordbook
     *
     * @param filepath 文件路径
     * @return
     * @throws Exception
     */
    private final static Workbook buildWordbook(String filepath) throws Exception {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(new File(filepath)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Wordbook build error");
        }
        return workbook;
    }

    /**
     * 根据cell数据类型获取对应类型的值
     *
     * @param cell
     * @return
     */
    private final static Object getCellValue(Cell cell) {

        if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
            return cell.getStringCellValue();
        } else if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
            return cell.getBooleanCellValue();
        } else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
            return DECIMAL_FORMAT.format(cell.getNumericCellValue());
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return cell.getStringCellValue();
        }
    }

}
