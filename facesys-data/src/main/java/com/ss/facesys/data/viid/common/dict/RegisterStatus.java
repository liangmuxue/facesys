package com.ss.facesys.data.viid.common.dict;


public enum RegisterStatus {
    UNREGISTERED(Short.valueOf((short) 0), "未注册"),


    REGISTERED(Short.valueOf((short) 1), "已注册"),


    OFFREGISTERED(Short.valueOf((short) 2), "已注销");


    private Short code;


    private String desc;


    RegisterStatus(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RegisterStatus get(Short code) {
        if (code == null) {
            return null;
        }
        for (RegisterStatus type : values()) {
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
