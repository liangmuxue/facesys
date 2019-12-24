package com.ss.facesys.util.em;

/**
 * 资源类型
 *
 * @author FrancisYs
 * @date 2019/12/24
 * @email yaoshuai@ss-cas.com
 */
public enum ResourceType {

    /**
     * 像机设备资源
     */
    CAMERA(1, "像机设备"),
    /**
     * 人像库资源
     */
    PERSONCARD(2, "人证设备"),
    /**
     * 人证设备资源
     */
    FACEDB(3, "人像库");

    private int value;
    private String desc;

    ResourceType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ResourceType getResourceType(Integer value) {
        for (ResourceType type : values()) {
            if (value.equals(type.getValue())) {
                return type;
            }
        }
        return null;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

}
