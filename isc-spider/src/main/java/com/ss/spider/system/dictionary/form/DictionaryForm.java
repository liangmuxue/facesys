package com.ss.spider.system.dictionary.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;


public class DictionaryForm implements Serializable {

    private static final long serialVersionUID = -8463676158067014498L;
    @NotBlank(groups = {com.ss.valide.APIGetsGroup.class, com.ss.valide.APIDeltGroup.class}, message = "{dictionary.dicIds.empty}")
    private String dicIds;
    @NotBlank(groups = {com.ss.valide.APIEditGroup.class}, message = "{dictionary.dicId.empty}")
    private String dicId;
    private String dicTypeCode;
    private String dicName;
    private String dicValue;
    private String updatedUserid;
    private String createdUserid;

    public String getDicIds() {
        return this.dicIds;
    }


    public void setDicIds(String dicIds) {
        this.dicIds = dicIds;
    }


    public String getDicId() {
        return this.dicId;
    }


    public void setDicId(String dicId) {
        this.dicId = dicId;
    }


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


    public String getDicValue() {
        return this.dicValue;
    }


    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
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
