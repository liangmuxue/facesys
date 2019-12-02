package com.ss.enums;


public enum SystemInitFlagEnum {

    YES(1, "是"),


    NO(0, "否");


    private int code;


    private String message;


    SystemInitFlagEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMessage(int code) {
        for (SystemInitFlagEnum re : values()) {
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
