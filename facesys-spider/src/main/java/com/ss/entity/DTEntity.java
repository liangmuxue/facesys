package com.ss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;

/**
 * DTEntity
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public class DTEntity extends UTEntity {

    private static final long serialVersionUID = -8468026284722424273L;

    @Column(name = "delete_time")
    private Long deleteTime;
    @Column(name = "delete_user_id")
    private String deleteUserId;

    @JsonIgnore
    public Long getDeleteTime() {
        return this.deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }

    @JsonIgnore
    public String getDeleteUserId() {
        return this.deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

}
