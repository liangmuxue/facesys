package com.ss.enums;


public enum OperaTypeEnum {

    ADD("ADD", "新增"), EDIT("EDIT", "修改"), DELETE("DELETE", "删除"), SELECT("SELECT", "查询"), SEARCH("SEARCH", "检索"), OTHER("OTHER", "其他"), UNKNOWN("UNKNOWN", "未知");

    private String value;

    private String key;

    OperaTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return this.key;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public String getValue() {
        return this.value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public static OperaTypeEnum get(String key) {
        if (key == null || key.length() == 0) {
            return UNKNOWN;
        }
        for (OperaTypeEnum operaTypeEnum : values()) {
            if (operaTypeEnum.getKey().equals(key)) {
                return operaTypeEnum;
            }
        }
        return UNKNOWN;
    }

}
