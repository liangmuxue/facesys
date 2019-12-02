package com.ss.isc.data.resource.common.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ss.isc.data.baseinfo.common.model.BaseEnums;
import com.ss.isc.util.export.excel.annotation.ExcelField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 实有人口收藏关系实体
 * @author FrancisYs
 * @date 2019/08/09
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_people_collection")
public class PeopleCollection implements Serializable {

    @Id
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "people_id")
    private String peopleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    @Override
    public String toString() {
        return "PeopleCollection{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", peopleId='" + peopleId + '\'' +
                '}';
    }

}
