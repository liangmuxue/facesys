package com.ss.facesys.util.em;


public enum DictionaryEnum {

    ETHIC_CODE_TYPE("DE00011", "民族"),


    ID_TYPE("DE00085", "证件类型"),


    GENDER_TYPE("DE00007", "性别"),


    DEVICE_TYPE("CW00001", "采集设备类型"),


    CAMERA_TYPE("CW00002", "像机类型"),


    DEVICE_FACTORY_TYPE("DEVICE_FACTORY_TYPE", "设备厂商类型");

    private String code;

    private String message;

    DictionaryEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return this.code;
    }


    public String getMessage() {
        return this.message;
    }
}
