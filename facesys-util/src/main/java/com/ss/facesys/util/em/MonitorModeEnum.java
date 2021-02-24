package com.ss.facesys.util.em;

public enum MonitorModeEnum {

    DB_MONITOR(1, "人像库布控"),
    PICTURE_MONITOR(2, "人像布控");

    private String name;
    private int code;

    MonitorModeEnum(int code, String name) {
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
            for (MonitorModeEnum c : values()) {
                if (c.getCode() == code.intValue()) {
                    return c.name;
                }
            }
        }
        return null;
    }
}
