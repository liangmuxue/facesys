package com.ss.entity;

import javax.persistence.Column;

public class UTEntity extends CTEntity {

    private static final long serialVersionUID = 8860879316446668472L;
    @Column(name = "update_time")
    private Long updateTime;
    @Column(name = "update_user_id")
    private String updateUserId;

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
}
