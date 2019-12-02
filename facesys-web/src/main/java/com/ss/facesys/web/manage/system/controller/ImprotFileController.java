package com.ss.facesys.web.manage.system.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: excelTest
 * Description: excel表格读取
 * 注意:引用poi 架包版本要一致
 * 如:
 * poi-3.13.jar
 * poi-ooxml-3.13.jar
 * poi-ooxml-schemas-3.13.jar
 * poi-scratchpad-3.13.jar
 * 这些架包版本随意
 * stax-api.jar
 * xmlbeans.jar
 * dom4j.jar
 * Version:1.0.0
 *
 * @author pancm
 */


//  注:03和07的写法不一致。
//  区别如下
//  HSSFWorkbook 2003的excel .xls,XSSFWorkbook导入2007的excel   .xlsx
//  HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File(file)));
//  XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File(file))));

public class ImprotFileController {

    private static final String path = "D:\\file\\test.xlsx";
    private static final String path1 = "D:\\file\\test1.xlsx";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        readExcel(path);
    }

    /**
     * 读取Excel表格内容
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void readExcel(String str) throws FileNotFoundException, IOException {
        File file = new File(str);
        // HSSFWorkbook 2003的excel .xls,XSSFWorkbook导入2007的excel   .xlsx
        //HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File(file)));
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);//读取第一个 sheet
        List list = new ArrayList<>();
        Row row = null;
        int count = sheet.getPhysicalNumberOfRows();
        //逐行处理 excel 数据
        for (int i = 1; i < count; i++) {
            JSONObject json = new JSONObject();
            row = sheet.getRow(i);
            Cell cell1 = cell1=row.getCell(0);
            if(cell1 != null && cell1.equals("")){
                //设置取值为String    整数数据要转，否则会变成浮点数
                cell1.setCellType(Cell.CELL_TYPE_STRING);
            }

            Cell cell2 = row.getCell(1);
            if(cell2 != null && cell2.equals("")){
                //设置取值为String    整数数据要转，否则会变成浮点数
                cell2.setCellType(Cell.CELL_TYPE_STRING);
            }
            json.put("orgId", cell1.toString()); //编号
            json.put("Id", cell2.toString()); //编号
            list.add(json);
            System.out.println("json:" + json);
        }
        //workbook.close();
        System.out.println("list:" + list);
    }
}

