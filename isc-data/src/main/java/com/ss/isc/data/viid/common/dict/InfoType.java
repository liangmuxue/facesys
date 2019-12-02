package com.ss.isc.data.viid.common.dict;


public enum InfoType {
    OTHER(Integer.valueOf(0), "其他"),


    AUTO(Integer.valueOf(1), "自动采集"),


    PERSON(Integer.valueOf(2), "人工采集");


    private Integer code;


    private String desc;


    InfoType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static InfoType get(Integer code) {
        if (code == null) {
            return null;
        }
        for (InfoType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
