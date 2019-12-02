package com.ss.spider.system.sequence.model;


public enum SeqCycleEnum {

    CYCLE(1),

    NONCYCLE(0);

    private int value;

    SeqCycleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
