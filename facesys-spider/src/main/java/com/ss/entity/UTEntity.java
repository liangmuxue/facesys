package com.ss.entity;

import javax.persistence.Column;


public class UTEntity extends CTEntity {

    private static final long serialVersionUID = 8860879316446668472L;
    @Column(name = "UPDATED_TIME")
    private Long updatedTime;
    @Column(name = "UPDATED_USERID")
    private String updatedUserid;

    public Long getUpdatedTime() {
        return this.updatedTime;
    }


    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }


    public String getUpdatedUserid() {
        return this.updatedUserid;
    }


    public void setUpdatedUserid(String updatedUserid) {
        this.updatedUserid = updatedUserid;
    }

}
