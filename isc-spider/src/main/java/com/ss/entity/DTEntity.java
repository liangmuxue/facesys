package com.ss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;


public class DTEntity extends UTEntity {

    private static final long serialVersionUID = -8468026284722424273L;
    @Column(name = "DELETED_TIME")
    private Long deletedTime;
    @Column(name = "DELETED_USERID")
    private String deletedUserid;

    @JsonIgnore
    public Long getDeletedTime() {
        return this.deletedTime;
    }


    public void setDeletedTime(Long deletedTime) {
        this.deletedTime = deletedTime;
    }


    @JsonIgnore
    public String getDeletedUserid() {
        return this.deletedUserid;
    }


    public void setDeletedUserid(String deletedUserid) {
        this.deletedUserid = deletedUserid;
    }

}
