package com.ss.facesys.data.viid.common.dict;


public enum ConfirmStatusType {
    OK("0", "OK"),


    OTHER_ERROR("1", "其他未知错误"),


    DEVICE_BUSY("2", "设备忙"),


    DEVICE_ERROR("3", "设备错"),


    INVALID_OPERATION("4", "无效操作"),


    INVALID_XML_FORMAT("5", "XML格式无效"),


    INVALID_XML_CONTENT("6", "XML内容无效"),


    INVALID_JSON_FORMAT("7", "JSON格式无效"),


    INVALID_JSON_CONTENT("8", "JSON内容无效"),


    REBOOT("9", "系统重启中，以附录B中类型定义为准");

    private String code;
    private String desc;


    ConfirmStatusType(String code, String desc) {
        this.code = code;
        this.desc = desc;
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
