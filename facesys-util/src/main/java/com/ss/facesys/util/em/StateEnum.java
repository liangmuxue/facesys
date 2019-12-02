package com.ss.facesys.util.em;


public enum StateEnum {
    EFFECTIVE(Short.valueOf((short) 1), "有效"),

    INVALID(Short.valueOf((short) 0), "无效");

    private Short key;

    private String value;

    StateEnum(Short key, String value) {
        this.key = key;
        this.value = value;
    }


    public Short getKey() {
        return this.key;
    }


    public void setKey(Short key) {
        this.key = key;
    }


    public String getValue() {
        return this.value;
    }


    public void setValue(String value) {
        this.value = value;
    }
}
