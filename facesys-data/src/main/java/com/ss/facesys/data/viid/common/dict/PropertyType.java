package com.ss.facesys.data.viid.common.dict;


public enum PropertyType {
    NAME("1", "人名"),


    ID_NUMBER("2", "证件号码"),


    LICENSE_PLATE("3", "车牌"),


    LICENSE_PLATE_COLOR("4", "车牌颜色"),


    VEHICLE_BRANDS("5", "车辆品牌"),


    VEHICLE_MODEL("6", "车辆型号"),


    VEHICLE_YEAR("7", "车辆年款"),


    KEYWORD("8", "关键字");


    private String code;


    private String desc;


    PropertyType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PropertyType get(String code) {
        if (code == null) {
            return null;
        }
        for (PropertyType type : values()) {
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
