package com.ss.facesys.data.archives.common.web;

/**
 * EnumVO
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
public class EnumVO {

    private String enumName;
    private String enumValue;

    public String getEnumName() {
        return this.enumName;
    }


    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }


    public String getEnumValue() {
        return this.enumValue;
    }


    public void setEnumValue(String enumValue) {
        this.enumValue = enumValue;
    }


    @Override
    public String toString() {
        return "EnumVO [enumName=" + this.enumName + ", enumValue=" + this.enumValue + "]";
    }

}
