package com.ss.facesys.util.em;

public enum FaceOrBodyEnum {

    FACE("人脸", 1),
    BODY("人体", 2);

    private String name;

    private Integer code;


    FaceOrBodyEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }


    public Integer getCode() {
        return this.code;
    }


    public String getName() {
        return this.name;
    }


    public static String getName(Integer code) {
        for (FaceOrBodyEnum c : values()) {
            if (c.getCode() == code.intValue()) {
                return c.name;
            }
        }
        return null;
    }
}
