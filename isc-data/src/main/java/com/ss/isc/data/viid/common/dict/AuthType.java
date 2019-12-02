package com.ss.isc.data.viid.common.dict;


public enum AuthType {
    NO(Short.valueOf((short) 0), "不鉴权"),


    YES(Short.valueOf((short) 1), "鉴权");

    private Short code;

    private String desc;

    AuthType(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AuthType get(Short code) {
        if (code == null) {
            return null;
        }
        for (AuthType type : values()) {
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
