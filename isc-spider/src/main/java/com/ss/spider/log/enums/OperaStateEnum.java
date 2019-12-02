package com.ss.spider.log.enums;

public enum OperaStateEnum {

    SUCC(0, "成功"), FAIL(1, "失败");

    private String value;

    private int code;

    OperaStateEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValue(int code) {
        for (OperaStateEnum lt : values()) {
            if (lt.getCode() == code) {
                return lt.getValue();
            }
        }
        return "";
    }


    public int getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }

}
