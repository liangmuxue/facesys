package com.ss.spider.system.dictionary.model;

import com.ss.entity.UTEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "CW_GE_DICTIONARY")
public class Dictionary extends UTEntity {

    private static final long serialVersionUID = 970207214260392807L;
    @Id
    @Column(name = "DIC_ID")
    private String dicId;
    @Column(name = "DIC_TYPE_CODE")
    private String dicTypeCode;
    @Column(name = "DIC_NAME")
    private String dicName;
    @Column(name = "DIC_VALUE")
    private String dicValue;

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


    public String toString() {
        return "Dictionary [dicId=" + this.dicId + ", dicTypeCode=" + this.dicTypeCode + ", dicName=" + this.dicName + ", dicValue=" + this.dicValue + "]";
    }

}
