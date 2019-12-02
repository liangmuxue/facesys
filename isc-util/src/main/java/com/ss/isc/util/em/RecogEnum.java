package com.ss.isc.util.em;


public enum RecogEnum {

    CAPTURE((short)5, "抓拍库检索"), PERSONCARD((short)6, "人证库检索");

    private String desc;

    private short value;

    RecogEnum(short value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public short getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

}