package com.ss.facesys.data.system.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * SysPara
 *
 * @author FrancisYs
 * @date 2020/2/14
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_sys_para")
public class SysPara {

    @Id
    private Integer id;
    @Column(name = "para_code")
    private String paraCode;
    @Column(name = "para_type")
    private Integer paraType;
    @Column(name = "para_module")
    private Integer paraModule;
    @Column(name = "para_name")
    private String paraName;
    @Column(name = "para_value")
    private String paraValue;
    @Column(name = "para_default_value")
    private String paraDefaultValue;
    @Column(name = "remark")
    private String remark;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "update_time")
    private Long updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParaCode() {
        return paraCode;
    }

    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }

    public Integer getParaType() {
        return paraType;
    }

    public void setParaType(Integer paraType) {
        this.paraType = paraType;
    }

    public Integer getParaModule() {
        return paraModule;
    }

    public void setParaModule(Integer paraModule) {
        this.paraModule = paraModule;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }

    public String getParaDefaultValue() {
        return paraDefaultValue;
    }

    public void setParaDefaultValue(String paraDefaultValue) {
        this.paraDefaultValue = paraDefaultValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SysPara.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("paraCode='" + paraCode + "'")
                .add("paraType=" + paraType)
                .add("paraModule=" + paraModule)
                .add("paraName='" + paraName + "'")
                .add("paraValue='" + paraValue + "'")
                .add("paraDefaultValue='" + paraDefaultValue + "'")
                .add("remark='" + remark + "'")
                .add("createTime=" + createTime)
                .add("updateTime=" + updateTime)
                .toString();
    }
    
}
