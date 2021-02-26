package com.ss.facesys.data.access.common.dto;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "cw_monitor_user_ref")
public class MonUserRef {

    @Column(name = "monitor_id")
    private Integer monitorId;
    @Column(name = "user_id")
    private String userId;

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
