package com.ss.isc.data.viid.common.dict;


public enum PriorityLevel {
    VIID_PRIORITY_LEVEL1("1", "1400布控等级1", "0.93"),


    VIID_PRIORITY_LEVEL2("2", "1400布控等级2", "0.92"),


    VIID_PRIORITY_LEVEL3("3", "1400布控等级3", "0.91"),


    VIID_PRIORITY_LEVEL_DEFAULT("default", "1400布控默认等级", "0.90");


    private String code;


    private String desc;


    private String defVal;


    PriorityLevel(String code, String desc, String defVal) {
        this.code = code;
        this.desc = desc;
        this.defVal = defVal;
    }

    public static PriorityLevel getByCode(String code) {
        if (code == null) {
            return VIID_PRIORITY_LEVEL_DEFAULT;
        }
        for (PriorityLevel level : values()) {
            if (level.getCode().equals(code)) {
                return level;
            }
        }
        return VIID_PRIORITY_LEVEL_DEFAULT;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getDefVal() {
        return this.defVal;
    }
}
