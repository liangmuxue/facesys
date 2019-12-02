package com.ss.facesys.data.baseinfo.common.web;

import com.ss.request.Pagination;
import com.google.common.collect.Maps;

import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;


public class BaseQueryEntity extends Pagination {

    private static final long serialVersionUID = 3670269734113283663L;
    private String userId;
    private String userIds;
    @NotBlank(message = "{BaseQueryEntity.villageCode.empty}", groups = {com.ss.valide.APIPageGroup.class, com.ss.valide.APIGetsGroup.class, com.ss.valide.APIListGroup.class, com.ss.valide.APIFeatureExtractionGroup.class})
    private String villageCode;
    protected Map<String, String> sqlMap;

    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserIds() {
        return this.userIds;
    }
    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
    public String getVillageCode() {
        return this.villageCode;
    }
    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }
    @XmlTransient
    public Map<String, String> getSqlMap() {
        if (this.sqlMap == null) {
            this.sqlMap = Maps.newHashMap();
        }
        return this.sqlMap;
    }
    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

}
