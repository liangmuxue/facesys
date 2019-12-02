package com.ss.enums;


public enum StatusEnum {

    EFFECT(1, "有效"),


    INVALID(0, "无效"),


    EXPIRE(-1, "已删除");


    private int code;


    private String message;


    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMessage(int code) {
        for (StatusEnum re : values()) {
            if (re.getCode() == code) {
                return re.getMessage();
            }
        }

        return "";
    }


    public int getCode() {
        return this.code;
    }


    public String getMessage() {
        return this.message;
    }

}
