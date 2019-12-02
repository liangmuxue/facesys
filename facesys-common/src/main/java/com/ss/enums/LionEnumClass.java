package com.ss.enums;


public class LionEnumClass {

    public enum LionInterfaceEnum {

        SUCCESS(0, "成功"),

        ERROR(-1, "系统异常"),

        PARAM_ERROR('Ɛ', "参数错误");

        private final int key;

        private final String value;

        LionInterfaceEnum(int key, String value) {
            this.key = key;
            this.value = value;
        }


        public int getKey() {
            return this.key;
        }


        public String getValue() {
            return this.value;
        }

    }

}
