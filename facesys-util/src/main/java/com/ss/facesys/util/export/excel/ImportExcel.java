package com.ss.facesys.util.export.excel;

import com.ss.facesys.util.export.excel.annotation.ExcelField;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class ImportExcel {

    private static Logger log = LoggerFactory.getLogger(ImportExcel.class);


    private Workbook wb;


    private Sheet sheet;


    private int headerNum;


    public ImportExcel(String fileName, int headerNum) throws InvalidFormatException, IOException {
        this(new File(fileName), headerNum);
    }


    public ImportExcel(File file, int headerNum) throws InvalidFormatException, IOException {
        this(file, headerNum, 0);
    }


    public ImportExcel(String fileName, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
        this(new File(fileName), headerNum, sheetIndex);
    }


    public ImportExcel(File file, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
        this(file.getName(), new FileInputStream(file), headerNum, sheetIndex);
    }


    public ImportExcel(MultipartFile multipartFile, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
        this(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), headerNum, sheetIndex);
    }


    public ImportExcel(String fileName, InputStream is, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
        if (StringUtils.isBlank(fileName))
            throw new RuntimeException("导入文档为空!");
        if (fileName.toLowerCase().endsWith("xls")) {
            this.wb = new HSSFWorkbook(is);
        } else if (fileName.toLowerCase().endsWith("xlsx")) {
            this.wb = new XSSFWorkbook(is);
        } else {
            throw new RuntimeException("文档格式不正确!");
        }
        if (this.wb.getNumberOfSheets() < sheetIndex) {
            throw new RuntimeException("文档中没有工作表!");
        }
        this.sheet = this.wb.getSheetAt(sheetIndex);
        this.headerNum = headerNum;
        log.debug("Initialize success.");
    }


    public Row getRow(int rownum) {
        return this.sheet.getRow(rownum);
    }


    public int getDataRowNum() {
        return this.headerNum + 1;
    }


    public int getLastDataRowNum() {
        return this.sheet.getLastRowNum() + this.headerNum;
    }


    public int getLastCellNum() {
        return getRow(this.headerNum).getLastCellNum();
    }


    public Object getCellValue(Row row, int column) {
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (cell != null) {
                if (cell.getCellType() == 0) {
                    val = Double.valueOf(cell.getNumericCellValue());
                } else if (cell.getCellType() == 1) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellType() == 2) {
                    val = cell.getCellFormula();
                } else if (cell.getCellType() == 4) {
                    val = Boolean.valueOf(cell.getBooleanCellValue());
                } else if (cell.getCellType() == 5) {
                    val = Byte.valueOf(cell.getErrorCellValue());
                }
            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }


    public <E> List<E> getDataList(Class<E> cls, int... groups) throws InstantiationException, IllegalAccessException {
        List<Object[]> annotationList = Lists.newArrayList();

        Field[] fs = cls.getDeclaredFields();
        for (Field f : fs) {
            ExcelField ef = (ExcelField) f.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == 2)) {
                if (groups != null && groups.length > 0) {
                    boolean inGroup = false;
                    for (int g : groups) {
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) {
                            if (g == efg) {
                                inGroup = true;
                                annotationList.add(new Object[]{ef, f});
                                break;
                            }
                        }
                    }
                } else {
                    annotationList.add(new Object[]{ef, f});
                }
            }
        }

        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) {
            ExcelField ef = (ExcelField) m.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == 2)) {
                if (groups != null && groups.length > 0) {
                    boolean inGroup = false;
                    for (int g : groups) {
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) {
                            if (g == efg) {
                                inGroup = true;
                                annotationList.add(new Object[]{ef, m});
                                break;
                            }
                        }
                    }
                } else {
                    annotationList.add(new Object[]{ef, m});
                }
            }
        }

        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return (new Integer(((ExcelField) o1[0]).sort())).compareTo(new Integer(((ExcelField) o2[0])
                        .sort()));
            }
        });


        List<E> dataList = Lists.newArrayList();
        for (int i = getDataRowNum(); i < getLastDataRowNum(); i++) {
            E e = (E) cls.newInstance();
            int column = 0;
            Row row = getRow(i);
            StringBuilder sb = new StringBuilder();
            for (Object[] os : annotationList) {
                Object val = getCellValue(row, column++);
                if (val != null) {
                    ExcelField ef = (ExcelField) os[0];
                    Class<?> valType = Class.class;
                    if (os[1] instanceof Field) {
                        valType = ((Field) os[1]).getType();
                    } else if (os[1] instanceof Method) {
                        Method method = (Method) os[1];
                        if ("get".equals(method.getName().substring(0, 3))) {
                            valType = method.getReturnType();
                        } else if ("set".equals(method.getName().substring(0, 3))) {
                            valType = ((Method) os[1]).getParameterTypes()[0];
                        }
                    }

                    try {
                        if (valType == String.class) {
                            String s = String.valueOf(val.toString());
                            if (StringUtils.endsWith(s, ".0")) {
                                val = StringUtils.substringBefore(s, ".0");
                            } else {
                                val = String.valueOf(val.toString());
                            }
                        } else if (valType == Integer.class) {
                            val = Integer.valueOf(Double.valueOf(val.toString()).intValue());
                        } else if (valType == Long.class) {
                            val = Long.valueOf(Double.valueOf(val.toString()).longValue());
                        } else if (valType == Double.class) {
                            val = Double.valueOf(val.toString());
                        } else if (valType == Float.class) {
                            val = Float.valueOf(val.toString());
                        } else if (valType == java.util.Date.class) {
                            val = DateUtil.getJavaDate(((Double) val).doubleValue());
                        } else if (ef.fieldType() != Class.class) {
                            val = ef.fieldType().getMethod("getValue", new Class[]{String.class}).invoke(null, new Object[]{val.toString()});
                        } else {

                            val = Class.forName(getClass().getName().replaceAll(getClass().getSimpleName(), "fieldtype." + valType.getSimpleName() + "Type")).getMethod("getValue", new Class[]{String.class}).invoke(null, new Object[]{val.toString()});
                        }

                    } catch (Exception ex) {
                        log.info("Get cell value [" + i + "," + column + "] error: " + ex.toString());
                        val = null;
                    }

                    if (os[1] instanceof Field) {
                        Reflections.invokeSetter(e, ((Field) os[1]).getName(), val);
                    } else if (os[1] instanceof Method) {
                        String mthodName = ((Method) os[1]).getName();
                        if ("get".equals(mthodName.substring(0, 3))) {
                            mthodName = "set" + StringUtils.substringAfter(mthodName, "get");
                        }
                        Reflections.invokeMethod(e, mthodName, new Class[]{valType}, new Object[]{val});
                    }
                }
                sb.append(val + ", ");
            }
            dataList.add(e);
            log.debug("Read success: [" + i + "] " + sb.toString());
        }
        return dataList;
    }

}
