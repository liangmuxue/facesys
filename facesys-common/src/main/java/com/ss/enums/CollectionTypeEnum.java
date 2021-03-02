package com.ss.enums;


public enum CollectionTypeEnum {

    CAPTURE(1, "抓拍库"),
    WITNESS(2, "人证库"),
    OFFLINE(3, "离线视频"),
    BAYONET(4, "人像卡口抓拍照");

    private int code;

    private String message;

    CollectionTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMessage(int code) {
        for (CollectionTypeEnum re : values()) {
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
