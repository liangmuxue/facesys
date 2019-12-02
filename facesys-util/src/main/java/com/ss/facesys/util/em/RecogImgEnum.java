package com.ss.facesys.util.em;


public enum RecogImgEnum {

    URL(Integer.valueOf(1), "URL"),
    BASE64(Integer.valueOf(2), "BASE64");

    private final Integer key;
    private final String value;

    RecogImgEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

}
