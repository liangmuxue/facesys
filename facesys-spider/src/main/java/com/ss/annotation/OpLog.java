package com.ss.annotation;

import com.ss.enums.OperaTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpLog {

    String model();

    OperaTypeEnum type();

    LogType logType() default LogType.OPERA;

    String desc();

    public enum LogType {
        LOGIN(1, "登录日志"),
        OPERA(2, "操作日志"),
        SYSTEM(3, "系统日志");


        private int code;


        private String value;


        LogType(int code, String value) {
            this.code = code;
            this.value = value;
        }


        public static String getValue(int code) {
            for (LogType lt : values()) {
                if (lt.getCode() == code) {
                    return lt.getValue();
                }
            }

            return "";
        }


        public int getCode() {
            return this.code;
        }


        public String getValue() {
            return this.value;
        }
    }

}
