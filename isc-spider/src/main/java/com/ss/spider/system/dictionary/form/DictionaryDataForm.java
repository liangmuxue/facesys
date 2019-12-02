package com.ss.spider.system.dictionary.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;


public class DictionaryDataForm implements Serializable {

    private static final long serialVersionUID = -1564997515343797639L;
    @NotBlank(groups = {com.ss.valide.APIEditGroup.class}, message = "{dictionary.id.empty}")
    private String id;
    @NotBlank(groups = {com.ss.valide.APIGetsGroup.class, com.ss.valide.APIDeltGroup.class}, message = "{dictionary.ids.empty}")
    private String ids;
    private String dicId;
    private String dataTypeCode;
    private String dataName;
    private String dataValue;
    private String updatedUserid;
    private String createdUserid;

    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getIds() {
        return this.ids;
    }


    public void setIds(String ids) {
        this.ids = ids;
    }


    public String getDicId() {
        return this.dicId;
    }


    public void setDicId(String dicId) {
        this.dicId = dicId;
    }


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


    public String getDataValue() {
        return this.dataValue;
    }


    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }


    public String getUpdatedUserid() {
        return this.updatedUserid;
    }


    public void setUpdatedUserid(String updatedUserid) {
        this.updatedUserid = updatedUserid;
    }


    public String getCreatedUserid() {
        return this.createdUserid;
    }


    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid;
    }

}
