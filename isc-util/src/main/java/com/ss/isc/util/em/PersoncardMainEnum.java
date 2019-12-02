package com.ss.isc.util.em;


public enum PersoncardMainEnum {

    HOTEL(Integer.valueOf(0), "酒店"),
    INTERNET_BAR(Integer.valueOf(1), "网吧"),
    STATION(Integer.valueOf(3), "车站");

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
}
