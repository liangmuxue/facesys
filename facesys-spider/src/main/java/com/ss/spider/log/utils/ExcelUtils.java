package com.ss.spider.log.utils;

import com.ss.spider.log.bean.ExcelData;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;


public class ExcelUtils {

    public static void exportExcel(HttpServletRequest request, HttpServletResponse response,
                                   String fileName, ExcelData data) throws Exception {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String userAgent = request.getHeader("user-agent");
        if ((userAgent != null && userAgent.indexOf("Firefox") >= 0) || userAgent.indexOf("Chrome") >= 0
                || userAgent
                .indexOf("Safari") >= 0) {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
        } else {

            fileName = URLEncoder.encode(fileName, "UTF8");
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        exportExcel(data, response.getOutputStream());
    }


    public static void exportExcel(ExcelData data, OutputStream out) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            String sheetName = data.getName();
            if (StringUtils.isEmpty(sheetName)) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            writeExcel(wb, sheet, data);
            wb.write(out);
        } finally {
            out.flush();
            out.close();
        }
    }


    private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
        writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
        autoSizeColumns(sheet, data.getTitles().size() + 1);
    }


    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;
        XSSFFont xSSFFont = wb.createFont();
        xSSFFont.setFontName("simsun");
        //lmx:有疑问
        xSSFFont.setBoldweight((short) 1);
        xSSFFont.setFontHeightInPoints((short) 14);
        xSSFFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment((short) 2);
        titleStyle.setVerticalAlignment((short) 1);
        titleStyle.setFillForegroundColor(new XSSFColor(new Color('¶', '¸', 'À')));
        titleStyle.setFillPattern((short) 1);
        titleStyle.setFont(xSSFFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

        Row titleRow = sheet.createRow(rowIndex);

        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
        rowIndex++;
        return rowIndex;
    }


    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows,
                                        int rowIndex) {
        int colIndex = 0;

        XSSFFont xSSFFont = wb.createFont();
        xSSFFont.setFontName("simsun");

        xSSFFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment((short) 2);
        dataStyle.setVerticalAlignment((short) 1);
        dataStyle.setFont(xSSFFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        boolean isMany = false;
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);

            dataRow.setHeightInPoints(20.0F);
            colIndex = 0;

            for (Object cellData : rowData) {
                if (cellData instanceof List) {
                    int beginRow = rowIndex;
                    int endCell = 0;
                    for (Object objects : (List) cellData) {
                        int beginColindex = colIndex;
                        endCell = colIndex;
                        List<String> cell = (List) objects;
                        Row dataRow1 = sheet.getRow(rowIndex);
                        if (dataRow1 == null) {
                            dataRow1 = sheet.createRow(rowIndex);

                            dataRow1.setHeightInPoints(20.0F);
                        }
                        for (String val : cell) {
                            Cell cell1 = dataRow1.createCell(beginColindex);
                            if (val.contains("Image_@")) {
                                String imageUrl = val.split("@")[1];

                                try {
                                    XSSFDrawing patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();

                                    XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0,
                                            beginColindex, rowIndex, beginColindex + 1, rowIndex + 1);
                                    anchor.setAnchorType(3);
                                    patriarch.createPicture(anchor, loadPicture(imageUrl, wb));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                cell1.setCellValue(val);
                            }
                            cell1.setCellStyle(dataStyle);
                            beginColindex++;
                        }
                        rowIndex++;
                    }
                    int endRow = rowIndex - 1;
                    for (int i = 0; i < endCell; i++) {

                        CellRangeAddress cellRangeAddress = new CellRangeAddress(beginRow, endRow, i, i);
                        sheet.addMergedRegion(cellRangeAddress);

                        RegionUtil.setBorderBottom(1, cellRangeAddress, sheet, wb);
                        RegionUtil.setBorderLeft(1, cellRangeAddress, sheet, wb);
                        RegionUtil.setBorderRight(1, cellRangeAddress, sheet, wb);
                        RegionUtil.setBorderTop(1, cellRangeAddress, sheet, wb);
                    }
                    isMany = true;
                    continue;
                }
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    String cellDataStr = cellData.toString();
                    if (cellDataStr.contains("Image_@")) {
                        String imageUrl = cellDataStr.split("@")[1];

                        try {
                            XSSFDrawing patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();

                            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, colIndex,
                                    rowIndex, colIndex + 1, rowIndex + 1);
                            anchor.setAnchorType(3);
                            patriarch.createPicture(anchor, loadPicture(imageUrl, wb));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        cell.setCellValue(cellDataStr);
                    }
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(dataStyle);
                colIndex++;
            }

            if (!isMany) {
                rowIndex++;
            }
        }
        return rowIndex;
    }


    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {

            int colWidth = sheet.getColumnWidth(i) * 2;
            if (colWidth < 65280) {
                sheet.setColumnWidth(i, (colWidth < 3000) ? 3000 : colWidth);
            } else {
                sheet.setColumnWidth(i, 6000);
            }
        }
    }

    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, color);
    }


    private static int loadPicture(String imageUrl, XSSFWorkbook wb) throws IOException {
        int pictureIndex;
        if (StringUtils.indexOf(imageUrl, "x-oss-process") < 0) {
            imageUrl = imageUrl + "?x-oss-process=style/ss200";
        }
        URL url = new URL(imageUrl);
        BufferedImage bufferImg = null;
        ByteArrayOutputStream bos = null;
        InputStream in = null;
        try {
            URLConnection conn = url.openConnection();
            in = conn.getInputStream();
            bos = new ByteArrayOutputStream();
            bufferImg = ImageIO.read(in);
            ImageIO.write(bufferImg, "jpg", bos);
            pictureIndex = wb.addPicture(bos.toByteArray(), 5);
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return pictureIndex;
    }

}
