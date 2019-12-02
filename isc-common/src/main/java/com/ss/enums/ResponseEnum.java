package com.ss.enums;

/**
 * ResponseEnum 响应信息枚举
 * @author FrancisYs
 * @date 2019/8/21
 * @email yaoshuai@ss-cas.com
 */
public enum ResponseEnum {

    /** 请求成功编码/描述信息 */
    SUCC(0, "请求处理成功"),
    /** 请求失败编码/描述信息 */
    FAIL(1, "请求处理失败");

    private int code;
    private String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(int code) {
        for (ResponseEnum re : values()) {
            if (re.getCode() == code) {
                return re.getMessage();
            }
        }
        return "";
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
