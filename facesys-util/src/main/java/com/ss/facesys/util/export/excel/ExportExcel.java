package com.ss.facesys.util.export.excel;

import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.export.excel.annotation.ExcelField;
import com.google.common.collect.Lists;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExportExcel {

    private static Logger log = LoggerFactory.getLogger(ExportExcel.class);


    private SXSSFWorkbook wb;


    private Sheet sheet;


    private Map<String, CellStyle> styles;


    private int rownum;


    List<Object[]> annotationList = Lists.newArrayList();


    public ExportExcel(String title, Class<?> cls) {
        this(title, cls, 1, new int[0]);
    }


    public ExportExcel(String title, Class<?> cls, int type, int... groups) {
        Field[] fs = cls.getDeclaredFields();
        for (Field f : fs) {
            ExcelField ef = (ExcelField) f.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == type)) {
                if (groups != null && groups.length > 0) {
                    boolean inGroup = false;
                    for (int g : groups) {
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) {
                            if (g == efg) {
                                inGroup = true;
                                this.annotationList.add(new Object[]{ef, f});
                                break;
                            }
                        }
                    }
                } else {
                    this.annotationList.add(new Object[]{ef, f});
                }
            }
        }

        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) {
            ExcelField ef = (ExcelField) m.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == type)) {
                if (groups != null && groups.length > 0) {
                    boolean inGroup = false;
                    for (int g : groups) {
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) {
                            if (g == efg) {
                                inGroup = true;
                                this.annotationList.add(new Object[]{ef, m});
                                break;
                            }
                        }
                    }
                } else {
                    this.annotationList.add(new Object[]{ef, m});
                }
            }
        }

        Collections.sort(this.annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return (new Integer(((ExcelField) o1[0]).sort())).compareTo(new Integer(((ExcelField) o2[0])
                        .sort()));
            }
        });

        List<String> headerList = Lists.newArrayList();
        for (Object[] os : this.annotationList) {
            String t = ((ExcelField) os[0]).title();

            if (type == 1) {
                String[] ss = StringUtils.split(t, "**", 2);
                if (ss.length == 2) {
                    t = ss[0];
                }
            }
            headerList.add(t);
        }
        initialize(title, headerList);
    }


    public ExportExcel(String title, String[] headers) {
        initialize(title, Lists.newArrayList(headers));
    }


    public ExportExcel(String title, List<String> headerList) {
        initialize(title, headerList);
    }


    private void initialize(String title, List<String> headerList) {
        this.wb = new SXSSFWorkbook('Ǵ');
        this.sheet = this.wb.createSheet("Export");
        this.styles = createStyles(this.wb);

        if (StringUtils.isNotBlank(title)) {
            Row titleRow = this.sheet.createRow(this.rownum++);
            titleRow.setHeightInPoints(30.0F);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle((CellStyle) this.styles.get("title"));
            titleCell.setCellValue(title);
            this.sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(), titleRow
                    .getRowNum(), titleRow.getRowNum(), headerList.size() - 1));
        }

        if (headerList == null) {
            throw new RuntimeException("headerList not null!");
        }
        Row headerRow = this.sheet.createRow(this.rownum++);
        headerRow.setHeightInPoints(16.0F);
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle((CellStyle) this.styles.get("header"));
            String[] ss = StringUtils.split((String) headerList.get(i), "**", 2);
            if (ss.length == 2) {
                cell.setCellValue(ss[0]);
                Comment comment = this.sheet.createDrawingPatriarch().createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 3, 3, 5, 6));

                comment.setString(new XSSFRichTextString(ss[1]));
                cell.setCellComment(comment);
            } else {
                cell.setCellValue((String) headerList.get(i));
            }
            this.sheet.autoSizeColumn(i);
        }
        for (int i = 0; i < headerList.size(); i++) {
            int colWidth = this.sheet.getColumnWidth(i) * 2;
            this.sheet.setColumnWidth(i, (colWidth < 3000) ? 3000 : colWidth);
        }
        log.debug("Initialize success.");
    }


    private Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

        CellStyle style = wb.createCellStyle();
        style.setAlignment((short) 2);
        style.setVerticalAlignment((short) 1);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBoldweight((short) 700);
        style.setFont(titleFont);
        styles.put("title", style);

        style = wb.createCellStyle();
        style.setVerticalAlignment((short) 1);
        style.setBorderRight((short) 1);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft((short) 1);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop((short) 1);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom((short) 1);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom((CellStyle) styles.get("data"));
        style.setAlignment((short) 1);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom((CellStyle) styles.get("data"));
        style.setAlignment((short) 2);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom((CellStyle) styles.get("data"));
        style.setAlignment((short) 3);
        styles.put("data3", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom((CellStyle) styles.get("data"));

        style.setAlignment((short) 2);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern((short) 1);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBoldweight((short) 700);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        return styles;
    }


    public Row addRow() {
        return this.sheet.createRow(this.rownum++);
    }


    public Cell addCell(Row row, int column, Object val) {
        return addCell(row, column, val, 0, Class.class);
    }


    public Cell addCell(Row row, int column, Object val, int align, Class<?> fieldType) {
        Cell cell = row.createCell(column);
        String cellFormatString = "@";
        try {
            if (val == null) {
                cell.setCellValue("");
            } else if (fieldType != Class.class) {
                cell.setCellValue((String) fieldType.getMethod("setDesc", new Class[]{Object.class}).invoke(null, new Object[]{val}));
            } else if (val instanceof String) {
                cell.setCellValue((String) val);
            } else if (val instanceof Integer) {
                cell.setCellValue(((Integer) val).intValue());
                cellFormatString = "0";
            } else if (val instanceof Long) {
                cell.setCellValue(((Long) val).longValue());
                cellFormatString = "0";
            } else if (val instanceof Double) {
                cell.setCellValue(((Double) val).doubleValue());
                cellFormatString = "0.00";
            } else if (val instanceof Float) {
                cell.setCellValue(((Float) val).floatValue());
                cellFormatString = "0.00";
            } else if (val instanceof Date) {
                cell.setCellValue((Date) val);
                cellFormatString = "yyyy-MM-dd HH:mm";
            } else {
                cell.setCellValue((String) Class.forName(getClass().getName().replaceAll(getClass().getSimpleName(), "fieldtype." + val
                        .getClass().getSimpleName() + "Type")).getMethod("setDesc", new Class[]{Object.class}).invoke(null, new Object[]{val}));
            }

            if (val != null) {
                CellStyle style = (CellStyle) this.styles.get("data_column_" + column);
                if (style == null) {
                    style = this.wb.createCellStyle();
                    style.cloneStyleFrom((CellStyle) this.styles.get("data" + ((align >= 1 && align <= 3) ? Integer.valueOf(align) : "")));
                    style.setDataFormat(this.wb.createDataFormat().getFormat(cellFormatString));
                    this.styles.put("data_column_" + column, style);
                }
                cell.setCellStyle(style);
            }
        } catch (Exception ex) {
            log.info("Set cell value [" + row.getRowNum() + "," + column + "] error: " + ex.toString());
            cell.setCellValue(val.toString());
        }
        return cell;
    }


    public <E> ExportExcel setDataList(List<E> list) {
        for (E e : list) {
            int colunm = 0;
            Row row = addRow();
            StringBuilder sb = new StringBuilder();
            for (Object[] os : this.annotationList) {
                ExcelField ef = (ExcelField) os[0];
                Object val = null;

                try {
                    if (StringUtils.isNotBlank(ef.value())) {
                        val = Reflections.invokeGetter(e, ef.value());
                    } else if (os[1] instanceof Field) {
                        val = Reflections.invokeGetter(e, ((Field) os[1]).getName());
                    } else if (os[1] instanceof Method) {
                        val = Reflections.invokeMethod(e, ((Method) os[1]).getName(), new Class[0], new Object[0]);


                    }


                } catch (Exception ex) {

                    log.info(ex.toString());
                    val = "";
                }
                addCell(row, colunm++, val, ef.align(), ef.fieldType());
                sb.append(val + ", ");
            }
            log.debug("Write success: [" + row.getRowNum() + "] " + sb.toString());
        }
        return this;
    }


    public ExportExcel write(OutputStream os) throws IOException {
        this.wb.write(os);
        return this;
    }


    public ExportExcel write(HttpServletResponse response, String fileName) throws IOException {
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
        write(response.getOutputStream());
        return this;
    }


    public ExportExcel writeFile(String name) throws FileNotFoundException, IOException {
        FileOutputStream os = new FileOutputStream(name);
        write(os);
        return this;
    }


    public ExportExcel dispose() {
        this.wb.dispose();
        return this;
    }

}
