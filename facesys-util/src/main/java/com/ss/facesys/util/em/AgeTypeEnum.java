package com.ss.facesys.util.em;

public enum AgeTypeEnum {

    AGEa("儿童", "0-6"),
    AGEb("少年", "7-17"),
    AGEc("青年", "18-40"),
    AGEd("中年", "41-65"),
    AGEe("老年", "66-120");

    private String name;

    private String code;


    AgeTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public String getCode() {
        return this.code;
    }


    public String getName() {
        return this.name;
    }


    public static String getName(String code) {
        for (AgeTypeEnum c : values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
}
