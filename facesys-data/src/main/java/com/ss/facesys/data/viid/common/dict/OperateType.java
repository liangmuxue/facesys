package com.ss.facesys.data.viid.common.dict;


public enum OperateType {
    START("0", "布控"),


    EXIT("1", "撤控");


    private String code;


    private String desc;


    OperateType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OperateType get(String code) {
        if (code == null) {
            return null;
        }
        for (OperateType type : values()) {
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
