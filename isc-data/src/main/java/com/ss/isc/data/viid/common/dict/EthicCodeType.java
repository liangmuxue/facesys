package com.ss.isc.data.viid.common.dict;


public enum EthicCodeType {
    TUJIA("Tujia", "TJ", new Short("15"), "土家族"),


    TU("Tu", "TU", new Short("30"), "土族"),


    MONBA("Monba", "MB", new Short("54"), "门巴族"),


    SUI("Sui", "SU", new Short("25"), "水族"),


    MAONAN("Maonan", "MN", new Short("36"), "毛南族"),


    UZBEK("Uzbek", "UZ", new Short("43"), "乌孜别克族"),


    BUYEI("Buyei", "BY", new Short("09"), "布依族"),


    BLANG("Blang", "BL", new Short("34"), "布朗族"),


    DONGXIANG("Dongxiang", "DX", new Short("26"), "东乡族"),


    GELAO("Gelao", "GL", new Short("37"), "仡佬族"),


    MULAO("Mulao", "ML", new Short("32"), "仫佬族"),


    BAI("Bai", "BA", new Short("14"), "白族"),


    HAN("Han", "HA", new Short("01"), "汉族"),


    DAUR("Daur", "DU", new Short("31"), "达翰尔族"),


    HUI("Hui", "HU", new Short("03"), "回族"),


    VA("Va", "VA", new Short("21"), "佤族"),


    ZHUANG("Zhuang", "ZH", new Short("08"), "壮族"),


    QIANG("Qiang", "QI", new Short("33"), "羌族"),


    ACHANG("Achang", "AC", new Short("39"), "阿昌族"),


    NAXI("Naxi", "NX", new Short("27"), "纳西族"),


    LAHU("Lahu", "LH", new Short("24"), "拉祜族"),


    MIAO("Miao", "MH", new Short("06"), "苗族"),


    DONG("Dong", "DO", new Short("12"), "侗族"),


    GIN("Gin", "GI", new Short("49"), "京族"),


    KIRGIZ("Kirgiz", "KG", new Short("29"), "柯尔克孜族"),


    HANI("Hani", "HN", new Short("16"), "哈尼族"),


    KAZAK("Kazak", "KZ", new Short("17"), "哈萨克族"),


    BONAN("Bonan", "BN", new Short("47"), "保安族"),


    RUSS("Russ", "RS", new Short("44"), "俄罗斯族"),


    DERUNG("Derung", "DR", new Short("51"), "独龙族"),


    NU("Nu", "NU", new Short("42"), "怒族"),


    LHOBA("Lhoba", "LB", new Short("55"), "珞巴族"),


    GAOSHAN("Gaoshan", "GS", new Short("23"), "高山族"),


    JINO("Jino", "JN", new Short("56"), "基诺族"),


    OROQEN("Oroqen", "OR", new Short("52"), "鄂伦春族"),


    EWENKI("Ewenki", "EW", new Short("45"), "鄂温克族"),


    UYGUR("Uygur", "UG", new Short("05"), "维吾尔族"),


    TAJIK("Tajik", "TA", new Short("41"), "塔吉克族"),


    TATAR("Tatar", "TT", new Short("50"), "塔塔尔族"),


    CHOSEN("Chosen", "CS", new Short("10"), "朝鲜族"),


    JINGPO("Jingpo", "JP", new Short("28"), "景颇族"),


    DAI("Dai", "DA", new Short("18"), "傣族"),


    LISU("Lisu", "LS", new Short("20"), "傈僳族"),


    PUMI("Pumi", "PM", new Short("40"), "普米族"),


    SHE("She", "SH", new Short("22"), "畲族"),


    YUGUR("Yugur", "YG", new Short("48"), "裕固族"),


    MONGOL("Mongol", "MG", new Short("02"), "蒙古族"),


    XIBE("Xibe", "XB", new Short("38"), "锡伯族"),


    MAN("Man", "MA", new Short("11"), "满族"),


    YAO("Yao", "YA", new Short("13"), "瑶族"),


    HEZHEN("Hezhen", "HZ", new Short("53"), "赫哲族"),


    SALAR("Salar", "SL", new Short("35"), "撒拉族"),


    DEANG("Deang", "DE", new Short("46"), "德昂族"),


    LI("Li", "LI", new Short("19"), "黎族"),


    ZANG("Zang", "ZA", new Short("04"), "藏族"),


    YI("Yi", "YI", new Short("07"), "彝族");


    private final String romanCode;


    private final String alphabetCode;


    private final Short numberCode;


    private final String nationName;


    EthicCodeType(String romanCode, String alphabetCode, Short numberCode, String nationName) {
        this.romanCode = romanCode;
        this.alphabetCode = alphabetCode;
        this.numberCode = numberCode;
        this.nationName = nationName;
    }

    public static String getNumberCodeByStr(String str) {
        EthicCodeType[] enums = values();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getRomanCode().equals(str) || enums[i].getAlphabetCode().equals(str) || enums[i]
                    .getNumberCode().equals(str)) {
                return enums[i].getNumberCode().toString();
            }
        }
        return null;
    }

    public String getRomanCode() {
        return this.romanCode;
    }

    public String getAlphabetCode() {
        return this.alphabetCode;
    }

    public Short getNumberCode() {
        return this.numberCode;
    }

    public String getNationName() {
        return this.nationName;
    }
}
