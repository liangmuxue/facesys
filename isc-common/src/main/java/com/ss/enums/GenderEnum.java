package com.ss.enums;


public enum GenderEnum {

    P("P", "不区分"),


    M("M", "男"),


    F("F", "女");


    private String code;


    private String message;


    GenderEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMessage(int code) {
        for (GenderEnum re : values()) {
            if (re.getCode().equals(Integer.valueOf(code))) {
                return re.getMessage();
            }
        }

        return "";
    }


    public String getCode() {
        return this.code;
    }


    public String getMessage() {
        return this.message;
    }

}
