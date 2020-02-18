package com.ss.facesys.data.archives.common.model;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
* 破案民警
* @author chao
* @create 2020/2/17
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_base_case_police")
public class CasePolice {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer victoryId;
    private String userId;
    private String userName;
    private String pictureUrl;
    @Transient
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVictoryId() {
        return victoryId;
    }

    public void setVictoryId(Integer victoryId) {
        this.victoryId = victoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}