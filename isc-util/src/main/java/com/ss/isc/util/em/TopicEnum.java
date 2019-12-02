package com.ss.isc.util.em;


public enum TopicEnum {
    CW_OCEAN_FACE_FEATURE("CW_OCEAN_FACE_FEATURE", "人像库重提特征"),


    CW_OCEAN_FTP_FILENAME_DOWLOAD("CW_OCEAN_FTP_FILENAME_DOWLOAD", "FTP方式图片名称"),


    CW_OCEAN_RECORD_ALARM_FLOW("CW_OCEAN_RECORD_ALARM_FLOW", "报警数据主题"),


    CW_OCEAN_RECORD_CAPTURE_FLOW("CW_OCEAN_RECORD_CAPTURE_FLOW", "抓拍数据主题"),


    CW_OCEAN_RECORD_ALARM_MSG("CW_OCEAN_RECORD_ALARM_MSG", "报警我的消息主题");

    private String topicCode;

    private String topicDesc;

    TopicEnum(String topicCode, String topicDesc) {
        this.topicCode = topicCode;
        this.topicDesc = topicDesc;
    }


    public String getTopicCode() {
        return this.topicCode;
    }


    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }


    public String getTopicDesc() {
        return this.topicDesc;
    }


    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }
}
