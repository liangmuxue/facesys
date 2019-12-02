package com.ss.facesys.util.em;


public enum TrackRecogEnum {

    TRACK((short) 6),

    WALLCHART((short) 8);

    private final Short value;


    TrackRecogEnum(Short value) {
        this.value = value;
    }


    public Short getValue() {
        return this.value;
    }

}
