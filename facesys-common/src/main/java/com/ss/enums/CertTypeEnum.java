package com.ss.enums;


public enum CertTypeEnum {

    NI("NI", "身份证"),
    PP("PP", "护照"),
    ID("ID", "其它");

    private String code;

    private String message;

    CertTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(int code) {
        for (CertTypeEnum re : values()) {
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
