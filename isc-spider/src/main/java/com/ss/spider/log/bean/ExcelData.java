package com.ss.spider.log.bean;

import java.io.Serializable;
import java.util.List;


public class ExcelData
        implements Serializable {

    private static final long serialVersionUID = 8683288107021034861L;
    private List<String> titles;
    private List<List<Object>> rows;
    private String name;

    public List<String> getTitles() {
        return this.titles;
    }


    public void setTitles(List<String> titles) {
        this.titles = titles;
    }


    public List<List<Object>> getRows() {
        return this.rows;
    }


    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("ExcelData:{");
        sb.append("\"titles\":")
                .append(this.titles);
        sb.append(",\"rows\":")
                .append(this.rows);
        sb.append(",\"name\":\"")
                .append(this.name).append('"');
        sb.append('}');
        return sb.toString();
    }

}
