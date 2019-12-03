package com.ss.entity;

import javax.persistence.Column;

/**
 * UTEntity
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public class UTEntity extends CTEntity {

    private static final long serialVersionUID = 8860879316446668472L;

    @Column(name = "update_time")
    private Long updateTime;
    @Column(name = "update_user_id")
    private String updateUserId;

    public Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return this.updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

}
