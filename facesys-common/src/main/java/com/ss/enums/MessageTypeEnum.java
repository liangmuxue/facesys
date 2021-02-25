package com.ss.enums;


public enum MessageTypeEnum {

    ALARM(1, "报警消息"),


    SYSTEM(2, "系统消息");

    private int code;


    private String message;


    MessageTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMessage(int code) {
        for (MessageTypeEnum re : values()) {
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
