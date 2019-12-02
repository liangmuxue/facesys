package com.ss.spider.system.dictionary.form;

import com.ss.request.Pagination;


public class DictionaryDataQuery extends Pagination {

    private static final long serialVersionUID = 4084437613530338009L;
    private String dataTypeCode;
    private String dataName;

    public String getDataTypeCode() {
        return this.dataTypeCode;
    }


    public void setDataTypeCode(String dataTypeCode) {
        this.dataTypeCode = dataTypeCode;
    }


    public String getDataName() {
        return this.dataName;
    }


    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

}
