package com.ss.facesys.util.em;

import java.util.ArrayList;
import java.util.List;


public enum HistoryEnum {

    ONETOONE((short)1),
    FACEDB((short)2),
    CAPTURE((short)3),
    PERSONCARD((short)4),
    OFFLINVIDEO((short)5),
    TRACK((short)6),
    NTON((short)7);

    private short value;

    HistoryEnum(short value) {
        this.value = value;
    }

    public short getValue() {
        return this.value;
    }

    public static List<Short> getAllValue() {
        List list = new ArrayList();
        for (HistoryEnum type : values()) {
            list.add(Short.valueOf(type.getValue()));
        }
        return list;
    }

}
