package com.ss.spider.system.dictionary.model;

import com.ss.entity.UTEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "CW_GE_DIC_DATA")
public class DictionaryData extends UTEntity {

    private static final long serialVersionUID = -4813767213019780421L;
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "DIC_ID")
    private String dicId;
    @Column(name = "DATA_TYPE_CODE")
    private String dataTypeCode;
    @Column(name = "DATA_NAME")
    private String dataName;
    @Column(name = "DATA_VALUE")
    private String dataValue;

    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
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


    public String toString() {
        return "DictionaryData [id=" + this.id + ", dicId=" + this.dicId + ", dataTypeCode=" + this.dataTypeCode + ", dataName=" + this.dataName + ", dataValue=" + this.dataValue + "]";
    }

}
