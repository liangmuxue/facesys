package com.ss.enums;


public enum AlarmMessageTypeEnum {

    ALL(0, "不限"),
    BLACK(1, "黑名单报警"),
    STRANGER(2, "陌生人报警"),
    GATHER(3, "聚集报警");

    private int code;


    private String message;


    AlarmMessageTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMessage(int code) {
        for (AlarmMessageTypeEnum re : values()) {
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
