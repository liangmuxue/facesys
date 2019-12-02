package com.ss.spider.system.dictionary.ext.form;

import com.ss.request.Pagination;
import org.hibernate.validator.constraints.NotBlank;


public class DictionaryDataExtQuery extends Pagination {

    @NotBlank(message = "字典类型编码不能为空", groups = {com.ss.valide.APIPageGroup.class})
    private String dicTypeCode;

    public String getDicTypeCode() {
        return this.dicTypeCode;
    }


    public void setDicTypeCode(String dicTypeCode) {
        this.dicTypeCode = dicTypeCode;
    }

}
