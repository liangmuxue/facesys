package com.ss.facesys.util.em;

public enum MonitorStateEnum {

    NO(0, "未布控"),
    YES(1, "已布控");

    private String name;
    private int code;

    MonitorStateEnum(int code, String name) {
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
            for (MonitorStateEnum c : values()) {
                if (c.getCode() == code.intValue()) {
                    return c.name;
                }
            }
        }
        return null;
    }
}
