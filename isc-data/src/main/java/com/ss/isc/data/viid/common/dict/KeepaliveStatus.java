package com.ss.isc.data.viid.common.dict;


public enum KeepaliveStatus {
    UNKEEPALIVE((short) 0, "未保活"),


    KEEPALIVED((short) 1, "已保活");


    private Short code;


    private String desc;


    KeepaliveStatus(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static KeepaliveStatus get(Short code) {
        if (code == null) {
            return null;
        }
        for (KeepaliveStatus type : values()) {
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
