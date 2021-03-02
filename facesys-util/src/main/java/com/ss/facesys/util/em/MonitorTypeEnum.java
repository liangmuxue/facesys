package com.ss.facesys.util.em;

public enum MonitorTypeEnum {

    BLACK(1, "黑名单"),
    STRANGER(2, "陌生人");

    private String name;
    private int code;

    MonitorTypeEnum(int code, String name) {
        this.name = name;
        this.code = code;
    }


    public int getCode() {
        return this.code;
    }


    public String getName() {
        return this.name;
    }


    public static String getName(Integer code) {
        if (code != null) {
            for (MonitorTypeEnum c : values()) {
                if (c.getCode() == code.intValue()) {
                    return c.name;
                }
            }
        }
        return null;
    }
}
