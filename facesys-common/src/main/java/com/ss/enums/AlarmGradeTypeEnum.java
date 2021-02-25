package com.ss.enums;


public enum AlarmGradeTypeEnum {

    ALL(0, "不限"),
    ONE(1, "一级报警"),
    TWO(2, "二级报警"),
    THREE(3, "三级报警"),
    FOUR(4, "四级报警"),
    FIVE(5, "五级报警");

    private int code;


    private String message;


    AlarmGradeTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMessage(int code) {
        for (AlarmGradeTypeEnum re : values()) {
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
