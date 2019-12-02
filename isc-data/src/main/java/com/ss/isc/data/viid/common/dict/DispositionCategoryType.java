package com.ss.isc.data.viid.common.dict;


public enum DispositionCategoryType {
    PERSON("1", "人"),


    MOTOR_VEHICLE("2", "机动车"),


    NON_MOTOR_VEHICLE("3", "非机动车"),


    KEYWORD("4", "关键字");


    private String code;


    private String desc;


    DispositionCategoryType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DispositionCategoryType get(String code) {
        if (code == null) {
            return null;
        }
        for (DispositionCategoryType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
