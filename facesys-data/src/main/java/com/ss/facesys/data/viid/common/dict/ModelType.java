package com.ss.facesys.data.viid.common.dict;


public enum ModelType {
    NORMAL(new Short("1"), "标准模式"),
    VIID(new Short("2"), "1400模式"),
    PUBLIC_SECURITY(new Short("3"), "公安模式（待移除）"),
    YC_MODEL(new Short("4"), "云从模式(待移除)");

    private Short key;
    private String value;

    ModelType(Short key, String value) {
        this.key = key;
        this.value = value;
    }


    public Short getKey() {
        return this.key;
    }


    public String getValue() {
        return this.value;
    }
}
