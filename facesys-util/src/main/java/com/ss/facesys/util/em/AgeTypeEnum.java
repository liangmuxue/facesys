package com.ss.facesys.util.em;


public enum AgeTypeEnum {

    CHILDREN(1, "儿童"),


    JUVENILE(2, "少年"),


    YOUTH(3, "青年"),


    MIDDLE(4, "中年"),


    OLD(5, "老年");


    private int code;


    private String message;


    AgeTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMessage(int code) {
        for (AgeTypeEnum re : values()) {
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
