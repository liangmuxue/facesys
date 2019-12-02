package com.ss.spider.system.sequence.model;


public enum SysSeqEnum {

    ORG_ID_SEQ("SYS_ORG_ID", "组织机构编号"),
    USR_ID_SEQ("SYS_USR_ID", "用户编号"),
    FACELIB_SEQ("FACE_LIB_ID", "人脸底库编号"),
    FACE_ID_SEQ("FACE_ID", "人脸记录编号"),
    AREA_ID_SEQ("AREA_ID", "区域编号"),
    DEVICE_ID_SEQ("DEVICE_ID", "设备编号");

    private String code;

    private String message;

    SysSeqEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code) {
        for (SysSeqEnum re : values()) {
            if (re.getCode().equals(code)) {
                return re.getMessage();
            }
        }

        return "";
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
