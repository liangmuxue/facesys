package com.ss.facesys.util.em;


public enum CredentialTypeEnum {

    ID_CARD(1, "身份证"),
    MILITARY_OFFICER_CARD(2, "军官证"),
    POLICE_OFFICER_CARD(3, "警官证"),
    DIPLOMATIC_PASSPORT(11, "外交护照"),
    OFFICIAL_PASSPORTS(12, "公务护照"),
    COMMON_PASSPORT(13, "因公普通护照"),
    ORDINARY_PASSPORT(14, "普通护照"),
    REPUBLIC_OF_CHINA_TRAVEL_CERTIFICATE(15, "中华人名共和国旅行证"),
    TAIWAN_COMPATRIOT_TRAVEL_CERTIFICATE(16, "台湾同胞旅行证"),
    SEAFARERS_CARD(17, "海员证"),
    CREW_CERTIFICATE(18, "机组人员证"),
    RAILWAY_EMPLOYEE_CERTIFICATE(19, "铁路员工证"),
    PEOPLES_REPUBLIC_CHINA_ENTRY_EXIT_PASS(20, "中国人民共和国入出境通行证"),
    HONG_KONG_MACAU_PASS(21, "往来港澳通行证"),
    GOTO_HONG_KONG_MACAU_PASS(23, "军官证"),
    HONG_KONG_MACAO_COMPATRIOTS_HOME_VISIT_PERMIT(24, "港澳同胞回乡证"),
    FOREIGNERS_ENTRY_AND_EXIT_PASSES(30, "外国人出入境通行证"),
    FOREIGNER_TRAVEL_DOCUMENT(31, "外国人旅行证件"),
    FOREIGNER_RETURNING_DOCUMENTS(32, "外国人回国证件"),
    OTHER_DOCUMENTS_FOR_FOREIGNERS_RETURNING_TO_CHINA(99, "外国人回国证件其它证件");

    private String name;
    private int code;

    CredentialTypeEnum(int code, String name) {
        this.name = name;
        this.code = code;
    }


    public int getCode() {
        return this.code;
    }


    public String getName() {
        return this.name;
    }


    public static String getName(Integer code) {
        if (code != null) {
            for (CredentialTypeEnum c : values()) {
                if (c.getCode() == code.intValue()) {
                    return c.name;
                }
            }
        }
        return null;
    }
}
