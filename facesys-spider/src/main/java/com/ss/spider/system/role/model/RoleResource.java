package com.ss.spider.system.role.model;

import com.ss.entity.ITableEntity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


@Table(name = "cw_ge_role_resource_ref")
public class RoleResource implements ITableEntity {

    private static final long serialVersionUID = 7290152023086866656L;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "resource_id")
    private String resourceId;
    @Transient
    private List<String> resourceIds;

    public String getRoleId() {
        return this.roleId;
    }


    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getResourceId() {
        return this.resourceId;
    }


    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }


    public List<String> getResourceIds() {
        return this.resourceIds;
    }


    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }


    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RoleResource resource = (RoleResource) o;

        return (new EqualsBuilder())
                .append(this.roleId, resource.roleId)
                .append(this.resourceId, resource.resourceId)
                .isEquals();
    }


    public int hashCode() {
        return (new HashCodeBuilder(17, 37))
                .append(this.roleId)
                .append(this.resourceId)
                .toHashCode();
    }


    public String toString() {
        return "RoleResource [roleId=" + this.roleId + ", resourceId=" + this.resourceId + "]";
    }

}
