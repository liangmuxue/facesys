package com.ss.isc.data.viid.common.dict;


public enum ViidStatusType {
    OFF((short) 0, "停用"),


    ON((short) 1, "启用"),


    DELETE((short) -1, "删除");

    private Short code;

    private String desc;

    ViidStatusType(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ViidStatusType get(Short code) {
        if (code == null) {
            return null;
        }
        for (ViidStatusType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public Short getCode() {
        return this.code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
