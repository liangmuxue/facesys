package com.ss.facesys.data.statistic.common.dto;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "cw_st_alarm")
public class AlarmHour {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    @Column(name = "black_count")
    private Integer blackCount;
    @Column(name = "stranger_count")
    private Integer strangerCount;
    @Column(name = "gather_count")
    private Integer gatherCount;
    @Column(name = "inconformity_count")
    private Integer inconformityCount;
    @Column(name = "")
    private Integer hour;
    private Integer date;
    @Column(name = "create_time")
    private Long createTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlackCount() {
        return blackCount;
    }

    public void setBlackCount(Integer blackCount) {
        this.blackCount = blackCount;
    }

    public Integer getStrangerCount() {
        return strangerCount;
    }

    public void setStrangerCount(Integer strangerCount) {
        this.strangerCount = strangerCount;
    }

    public Integer getGatherCount() {
        return gatherCount;
    }

    public void setGatherCount(Integer gatherCount) {
        this.gatherCount = gatherCount;
    }

    public Integer getInconformityCount() {
        return inconformityCount;
    }

    public void setInconformityCount(Integer inconformityCount) {
        this.inconformityCount = inconformityCount;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}
