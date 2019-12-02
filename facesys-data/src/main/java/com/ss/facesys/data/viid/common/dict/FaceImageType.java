package com.ss.facesys.data.viid.common.dict;


public enum FaceImageType {
    CAPTURE(Integer.valueOf(11), "抓拍人脸图"),


    PANORAMA(Integer.valueOf(14), "抓拍全景照");


    private Integer type;


    private String desc;


    FaceImageType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }


    public Integer getType() {
        return this.type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public String getDesc() {
        return this.desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }
}
