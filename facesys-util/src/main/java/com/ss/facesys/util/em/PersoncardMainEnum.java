package com.ss.facesys.util.em;


public enum PersoncardMainEnum {

    HOTEL(1, "酒店"),
    INTERNET_BAR(2, "网吧"),
    STATION(3, "车站");

    private final Integer key;
    private final String value;

    PersoncardMainEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public static String get(Integer key) {
        for (PersoncardMainEnum obj : values()) {
            if (obj.getKey().equals(key)) {
                return obj.getValue();
            }
        }
        return null;
    }

}
