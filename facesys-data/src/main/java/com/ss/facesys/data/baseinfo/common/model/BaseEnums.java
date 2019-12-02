package com.ss.facesys.data.baseinfo.common.model;

import java.io.Serializable;
import javax.persistence.Table;

/**
 * 枚举实体类
 * @author FrancisYs
 * @date 2019/08/09
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_enum")
public class BaseEnums implements Serializable {

    private static final long serialVersionUID = -4201165453774287927L;
    private String enumType;
    private String enumName;
    private Integer enumValue;
    private String enumDesc;

    public BaseEnums() {
    }

    public BaseEnums(String enumName, Integer enumValue) {
        this.enumName = enumName;
        this.enumValue = enumValue;
    }


    public String getEnumType() {
        return this.enumType;
    }


    public void setEnumType(String enumType) {
        this.enumType = (enumType == null) ? null : enumType.trim();
    }


    public String getEnumName() {
        return this.enumName;
    }


    public void setEnumName(String enumName) {
        this.enumName = (enumName == null) ? null : enumName.trim();
    }


    public Integer getEnumValue() {
        return this.enumValue;
    }


    public void setEnumValue(Integer enumValue) {
        this.enumValue = enumValue;
    }


    public String getEnumDesc() {
        return this.enumDesc;
    }


    public void setEnumDesc(String enumDesc) {
        this.enumDesc = (enumDesc == null) ? null : enumDesc.trim();
    }

}
