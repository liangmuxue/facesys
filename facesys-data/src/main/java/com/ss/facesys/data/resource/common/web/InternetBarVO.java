package com.ss.facesys.data.resource.common.web;

import com.ss.request.Pagination;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
* 酒店实体类
* @author chao
* @create 2020/2/5
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_base_internet_bar")
public class InternetBarVO extends Pagination {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "{internetBar.id.empty}", groups = {APIEditGroup.class, APIDeltGroup.class, APIGetsGroup.class})
    private Integer id;
    @NotBlank(message = "{internetBar.name.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String name;
    @NotBlank(message = "{internetBar.orgId.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String orgId;
    private String address;
    private String contactNumber;
    private String contacts;
    private String remark;
    private Double lon;
    private Double lat;
    private String createTime;
    private String updateTime;
    private Integer status;
    @Transient
    private String orgName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}