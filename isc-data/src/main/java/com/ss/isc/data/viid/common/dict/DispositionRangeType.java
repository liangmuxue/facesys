package com.ss.isc.data.viid.common.dict;


public enum DispositionRangeType {
    COUNTY("1", "县内（含县级市）"),


    CROSS_COUNTY("2", "跨县"),


    CITY("3", "跨县"),


    CROSS_CITY("4", "跨市"),


    PROVINCE("5", "省内"),


    CROSS_PROVINCE("6", "跨省"),


    COUNTRY("7", "全国"),


    CROSS_BORDER("8", "跨境"),


    DEVICE("9", "卡口");


    private String code;


    private String desc;


    DispositionRangeType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DispositionRangeType get(String code) {
        if (code == null) {
            return null;
        }
        for (DispositionRangeType type : values()) {
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
