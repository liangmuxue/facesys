package com.ss.facesys.data.viid.common.constants;

/**
 * 视图库接口地址枚举
 *
 * @author FrancisYs
 * @date 2019/10/25
 */
public enum ViidURL {

    /**
     * 系统注册
     */
    REGISTER("/VIID/System/Register", "系统注册"),

    /**
     * 系统保活
     */
    KEEPALIVE("/VIID/System/Keepalive", "系统保活");

    private String url;
    private String message;

    ViidURL(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public static String getMessage(String url) {
        for (ViidURL re : values()) {
            if (re.getUrl().equals(url)) {
                return re.getMessage();
            }
        }
        return "";
    }

    public String getUrl() {
        return this.url;
    }

    public String getMessage() {
        return this.message;
    }

}
