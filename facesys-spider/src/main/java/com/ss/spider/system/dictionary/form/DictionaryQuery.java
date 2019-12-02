package com.ss.spider.system.dictionary.form;

import com.ss.request.Pagination;


public class DictionaryQuery extends Pagination {

    private static final long serialVersionUID = -4501472798474603738L;
    private String dicTypeCode;
    private String dicName;

    public String getDicTypeCode() {
        return this.dicTypeCode;
    }


    public void setDicTypeCode(String dicTypeCode) {
        this.dicTypeCode = dicTypeCode;
    }


    public String getDicName() {
        return this.dicName;
    }


    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

}
