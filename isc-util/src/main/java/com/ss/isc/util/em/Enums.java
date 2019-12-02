package com.ss.isc.util.em;

import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;

import java.util.ArrayList;
import java.util.List;


public class Enums {

    public enum Sex {

        SEX_MAN("男", 1),
        SEX_WOMEN("女", 2),
        SEX_UNKONW("性别不明", 3);

        private String name;

        private int code;


        Sex(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (Sex c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum PeopleType {
        PEOPLETYPE_RESIDENCE("常住人口", 1),


        PEOPLETYPE_ABROAD("境外人员", 2),


        PEOPLETYPE_FLOW("流动人员", 3),


        PEOPLETYPE_TEMPORARY("暂住人口", 4);
        private String name;
        private int code;

        PeopleType(String name, int code) {
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
                for (PeopleType c : values()) {
                    if (c.getCode() == code.intValue()) {
                        return c.name;
                    }
                }
            }
            return null;
        }
    }


    public enum Relation {
        RELATION_HOLDER("户主", 1),


        RELATION_TENANT("租户", 2),


        RELATION_SPOUSE("夫妻", 3),


        RELATION_PARENT("父母", 4),


        RELATION_FAtherSON("父子", 5),


        RELATION_MOTHERSON("母子", 6),


        RELATION_FATHERGIRL("父女", 7),


        RELATION_MOTHERGIRL("母女", 8),


        RELATION_BROTHER("兄弟", 9),


        RELATION_SISTER("姐妹", 10);
        private String name;
        private int code;

        Relation(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (Relation c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }

    /**
     * 人口标签
     */
    public enum PeopleLabel {

        /**
         * 寄住人口
         */
        HOST_POPULATION("寄住人口", 1),
        /**
         * 受害人
         */
        VICTIM("受害人", 2),
        /**
         * 空挂户
         */
        EMPTY_HOLDER("空挂户", 3),
        /**
         * 被盘查人员
         */
        INTERROGATING_PERSONNEL("被盘查人员", 4),
        /**
         * 工作对象
         */
        WORKING_OBJECT("工作对象", 5),
        /**
         * 涉警人员
         */
        POLICE_OFFICERS_INVOLVED("涉警人员", 6),
        /**
         * 涉案嫌疑人
         */
        SUSPECTS_INVOLVED("涉案嫌疑人", 7),
        /**
         * 违法行为人
         */
        ILLEGAL_PERSON("违法行为人", 8),
        /**
         * 吸毒人员
         */
        DRUG_ADDICTS("吸毒人员", 9),
        /**
         * 重点人员
         */
        KEY_PERSONNEL("重点人员", 10),
        /**
         * 流出人员
         */
        OUTFLOW_STAFF("流出人员", 11),
        /**
         * 证人
         */
        WITNESS("证人", 12),
        /**
         * 消防违法人
         */
        FIRE_OFFENDER("消防违法人", 13),
        /**
         * 房屋出租户房主
         */
        TENANT_HOUSEHOLDS("房屋出租户房主", 14),
        /**
         * 社区矫正对象
         */
        COMMUNITY_CORRECTION_OBJECT("社区矫正对象", 15),
        /**
         * 涉稳人员
         */
        STABILIZING_PERSONNEL("涉稳人员", 16),
        /**
         * 治保群防人员
         */
        PREVENTION_CONTROL_PERSONNEL("治保群防人员", 17),
        /**
         * 从业人员
         */
        PRACTITIONERS("从业人员", 18),
        /**
         * 市内暂口
         */
        TEMPORARY_SUSPENSION("市内暂口", 19),
        /**
         * 历史暂住人口
         */
        TEMPORARY_POPULATION_HISTORY("历史暂住人口", 20);

        private String name;
        private int code;

        PeopleLabel(String name, int code) {
            this.name = name;
            this.code = code;
        }
        public int getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        public static String getName(int code) {
            for (PeopleLabel c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
        public static String getName(String codeStr) {
            if (StringUtils.isBlank(codeStr)) {
                return "";
            }
            String[] labelArr = codeStr.split(CommonConstant.SPLIT_COMMA);
            List<String> labelDescArr = new ArrayList<>();
            for (String s : labelArr) {
                labelDescArr.add(getName(Integer.parseInt(s)));
            }
            return StringUtils.join(labelDescArr, CommonConstant.SPLIT_COMMA);
        }

    }


    public enum Gender {
        MALE("男", 1),


        FEMALE("女", 2),


        GENDER_UNKNOWN("性别不明", 3);
        private String name;
        private int code;

        Gender(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (Relation c : Relation.values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum NettyUri {
        URI_HOME("首页推送", "home"),


        URI_OTHER("其他推送", "other"),


        URI_HOME_PAGE("首页推送", "homePage");

        private String name;
        private String code;

        NettyUri(String name, String code) {
            this.name = name;
            this.code = code;
        }


        public String getName() {
            return this.name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getCode() {
            return this.code;
        }


        public void setCode(String code) {
            this.code = code;
        }
    }


    public enum carType {
        HOLD("住户车辆", 1),


        TENANT("租户车辆", 2),


        FAMILY("亲情车辆", 3),


        SERVICE("服务车辆", 3),


        VISITOR("访客车辆", 3),


        EXPRESS("快递车辆", 3),


        OUTFOOD("外卖车辆", 3),


        VILLAGESERVICE("小区服务车辆", 3),


        VILLAGEWORK("小区工作车辆", 3),


        WORK("其他车辆", 3);
        private String name;
        private int code;

        carType(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (carType c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum syncCameraType {
        FACE("大眼睛", 2),


        TERMINAL("识别终端", 4),


        USUAL("抓拍机", 6);

        private String name;
        private int code;

        syncCameraType(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (cameraType c : cameraType.values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum ClusterState {
        EXECUTORY("进行中", 1),


        FAILED("失败", 2),


        FINISH("完成", 4);

        private String name;
        private int code;

        ClusterState(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public String getName() {
            return this.name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public int getCode() {
            return this.code;
        }


        public void setCode(int code) {
            this.code = code;
        }
    }


    public enum cameraType {
        UNKNOWN("未知", -1),


        USUAL("普通摄像机", 1),


        FACE("人脸抓拍摄像机", 2),


        CAR("车辆卡口摄像机", 3),


        ROAD("道路卡摄像机", 4),


        DOOR("门禁摄像机", 5),


        CRED("人证设备", 6),


        ELEVATOR("电梯摄像机", 7);

        private String name;
        private int code;

        cameraType(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (cameraType c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum SysState {
        STATE_0("通用状态枚举-0", 0),

        STATE_1("通用状态枚举-1", 1),

        STATE_2("通用状态枚举-2", 2);


        private String name;


        private int code;


        SysState(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }
    }


    public enum LeaveState {
        STATE_1("未处理", 1),


        STATE_2("有效", 2),


        STATE_3("无效", 3);


        private String name;


        private int code;


        LeaveState(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }
    }


    public enum AlarmDeviceType {
        CAMERA("像机设备", 1),


        PERSONCARD("人证设备", 2),


        VIDEO("离线视频", 3);


        private String name;


        private int code;


        AlarmDeviceType(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }
    }


    public enum AlarmMonitorType {
        BLACK("黑名单报警", 1),


        STRANGER("陌生人报警", 2),


        WEI_NATION("在册维族报警", 3),


        WEI_NATION_STRANGER("陌生人维族报警", 4),


        WEI_NATION_CROWD("维族人聚集报警", 5);

        private String name;
        private int code;

        AlarmMonitorType(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (cameraType c : cameraType.values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum AnalysisTaskState {
        INIT("初始化", 1),


        RUNNING("进行中", 2),


        FINISH("已完成", 3),


        FAILURE("失败", 4),


        OVER("分析结束", 9);


        private String name;


        private int code;


        AnalysisTaskState(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }
    }


    public enum AnalysisTaskType {
        ADD("感知发现", 1),


        LEAVE("感知离开", 2);


        private String name;


        private int code;


        AnalysisTaskType(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }
    }


    public enum peopleSource {
        PEOPLE_LIBRARY("人口库", (short) 1),


        ENTRANCE_GUARD_SYSTEM("门禁系统", (short) 2),


        NETWORK_COLLECT("网络采集", (short) 3),


        APPERCEIVE_DISCOVER("感知发现", (short) 4),


        OTHER("其他", (short) 5);

        private String name;
        private short code;

        peopleSource(String name, short code) {
            this.name = name;
            this.code = code;
        }


        public short getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(short code) {
            for (peopleSource c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum SpecialType {
        OLD("高龄老人", 1),


        YOUNG("青壮年", 2);


        private String name;


        private int code;


        SpecialType(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }
    }


    public enum FrequencyLabel {
        COURIER("快递人员", 1),


        TAKEAWAY("外卖", 2),


        CLEANING("保洁", 3),


        SECURITY_STAFF("保安", 4);
        private String name;
        private int code;

        FrequencyLabel(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (FrequencyLabel c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum gisType {
        WGS("地球坐标", "1"),


        GD("gcj02 (火星、高德)", "2"),


        BD("bd09 (百度)", "3"),


        CGCS("CGCS2000", "4"),


        XA("西安 80", "5"),


        BJ("北京 54", "6"),


        OTHER("其他", "7");
        private String name;
        private String code;

        gisType(String name, String code) {
            this.name = name;
            this.code = code;
        }


        public String getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(String code) {
            if (StringUtils.isNotBlank(code)) {
                for (gisType c : values()) {
                    if (code.equals(c.getCode())) {
                        return c.name;
                    }
                }
            }
            return null;
        }
    }


    public enum regionType {

        COUNTRY("国家", 1),

        PROVINCE("省级", 2),

        CITY("市级", 3),

        DISTRICT("区级", 4),

        STREET("街道", 5),

        COMMITTEE("居委会", 6),

        VILLAGE("小区", 7),

        CAMERA("设备", 8);

        private String name;
        private int code;

        regionType(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }
    }


    public enum CompanyType {
        STATE_OWNED_COMPANY("国有企业", 1),


        STATE_OWNED_HOLDING_COMPANY("国有控股企业", 2),


        FOREIGN_COMPANY("外资企业", 3),


        JOINT_VENTURE_COMPANY("合资企业", 4),


        PRIVATE_ENTERPRISE_COMPANY("私营企业", 5),


        INSTITUTION("事业单位", 6),


        STATE_ADMINISTRATIVE_ORGAN("国家行政机关", 7),


        GOVERNMENT("政府", 8);

        private String name;
        private int code;

        CompanyType(String name, int code) {
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
                for (CompanyType c : values()) {
                    if (c.getCode() == code.intValue()) {
                        return c.name;
                    }
                }
            }
            return null;
        }
    }


    public enum CompanySize {
        MICRO_ENTERPRISES("微型企业", 1),


        SAMALL_ENTERPRISE("小型企业", 2),


        MEDIUM_SIZED_ENTERPRISE("中型企业", 3),


        LARGE_ENTERPRISE("大型企业", 4);

        private String name;
        private int code;

        CompanySize(String name, int code) {
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
                for (CompanySize c : values()) {
                    if (c.getCode() == code.intValue()) {
                        return c.name;
                    }
                }
            }
            return null;
        }
    }


    public enum EconomicType {
        STATE_OWNED("国有", 1),


        COLLECTIVE("集体", 2),


        PRIVATE("私营", 3),


        INDIVIDUAL("个体", 4),


        JOINT_VENTURE("联营", 5),


        SHAREHOLDING_SYSTEM("股份制", 6),


        FOREIGN_INVESTMENT("外商投资", 7),


        HK_AM_TW_INVESTMENT("港澳台投资", 8),


        OTHER("其他经济", 9);

        private String name;
        private int code;

        EconomicType(String name, int code) {
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
                for (EconomicType c : values()) {
                    if (c.getCode() == code.intValue()) {
                        return c.name;
                    }
                }
            }
            return null;
        }
    }


    public enum ImportantFlag {
        KEY_UNIT_SERVICE_UNIT("重点单位服务单位", 1),


        KEY_UNIT("重点单位", 2);

        private String name;
        private int code;

        ImportantFlag(String name, int code) {
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
                for (ImportantFlag c : values()) {
                    if (c.getCode() == code.intValue()) {
                        return c.name;
                    }
                }
            }
            return null;
        }
    }


    public enum plateType {
        PLATE_COMMON("普通蓝牌", 1),

        PLATE_BLACK("普通黑牌", 2),

        PLATE_YELLOW("普通黄牌", 3),

        PLATE_DOUBLEYELLOW("双层黄牌", 4),

        PLATE_COACH("教练车牌", 5),

        PLATE_POLICE("警车车牌", 6),

        PLATE_POLICE_LICENSE("新式武警车牌", 7),

        PLATE_MILITARY("新式军牌", 8),

        PLATE_EMBASSY("大使馆车牌", 9),

        PLATE_NEW_ENERGY("新能源车牌", 10),

        PLATE_OTHER("其它车牌", 11);

        private String name;
        private int code;

        plateType(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (plateType c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum plateColor {
        PLATE_YELLOW("黄色", 1),

        PLATE_BLUE("蓝色", 2),

        PLATE_WHITE("白色", 3),

        PLATE_BLACK("黑色", 4),

        PLATE_GREEN("绿色", 5);

        private String name;
        private int code;

        plateColor(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (plateColor c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum credentialType {
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

        credentialType(int code, String name) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (credentialType c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum maritalStatusCode {
        MARIAL_UNKNOW("未知", 0),


        MARIAL_UNMARRIED("未婚", 1),


        MARIAL_MARRIED("已婚", 2),


        MARIAL_DIVORCE("离异", 3),


        MARIAL_SPOUSE("丧偶", 4);

        private String name;
        private int code;

        maritalStatusCode(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }


        public static String getName(int code) {
            for (maritalStatusCode c : values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }


    public enum Education {
        EDUCATION_NA("未知", 0),


        EDUCATION_1("小学", 1),


        EDUCATION_2("中学", 2),


        EDUCATION_3("高中", 3),


        EDUCATION_4("中专", 4),


        EDUCATION_5("大专", 5),


        EDUCATION_6("本科", 6),


        EDUCATION_7("研究生", 7),


        EDUCATION_8("博士", 8),


        EDUCATION_9("博士后", 9),


        EDUCATION_10("院士", 10),


        EDUCATION_11("大学在读", 11),


        EDUCATION_12("中学在读", 12),


        EDUCATION_13("小学在读", 13);


        private String name;


        private int code;


        Education(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }
    }


    public enum Political {
        POLITICAL_NA("未知", 0),


        POLITICAL_TY("团员", 1),


        POLITICAL_DY("党员", 2),


        POLITICAL_QZ("群众", 3),


        POLITICAL_MZ("民主党派", 4);


        private String name;


        private int code;


        Political(String name, int code) {
            this.name = name;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }


        public String getName() {
            return this.name;
        }
    }

}
