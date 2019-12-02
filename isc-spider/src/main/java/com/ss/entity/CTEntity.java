package com.ss.entity;

import javax.persistence.Column;


public class CTEntity implements ITableEntity {

    private static final long serialVersionUID = 5396928504857978911L;
    @Column(name = "CREATED_TIME")
    private Long createdTime;
    @Column(name = "CREATED_USERID")
    private String createdUserid;

    public Long getCreatedTime() {
        return this.createdTime;
    }


    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }


    public String getCreatedUserid() {
        return this.createdUserid;
    }


    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid;
    }

}
