package com.ss.facesys.data.resource.common.web;

import com.google.common.collect.Maps;
import com.ss.request.Pagination;
import com.ss.valide.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.Map;

/**
* 相机设备查询实体类
* @author chao
* @create 2019/12/6
* @email lishuangchao@ss-cas.com
**/
public class CameraQueryVO extends Pagination {

    private String villageCode;
    private String villageCodes;
    private String cameraName;
    private String prducetBrand;
    private Integer cameraType;
    private Integer cameraState;
    @NotBlank(message = "{user.ids.empty}", groups = {APIPageGroup.class})
    private String userIds;
    private Map<String, String> sqlMap;
    private String orgId;
    private Integer cameraEnabled;
    private String cameraId;
    private List<Integer> resources;
    private List<String> orgIds;
    private String userId;
    private String sceneIds;

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
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

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getPrducetBrand() {
        return prducetBrand;
    }

    public void setPrducetBrand(String prducetBrand) {
        this.prducetBrand = prducetBrand;
    }

    public Integer getCameraType() {
        return cameraType;
    }

    public void setCameraType(Integer cameraType) {
        this.cameraType = cameraType;
    }

    public Integer getCameraState() {
        return cameraState;
    }

    public void setCameraState(Integer cameraState) {
        this.cameraState = cameraState;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getCameraEnabled() {
        return cameraEnabled;
    }

    public void setCameraEnabled(Integer cameraEnabled) {
        this.cameraEnabled = cameraEnabled;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public List<Integer> getResources() {
        return resources;
    }

    public void setResources(List<Integer> resources) {
        this.resources = resources;
    }

    public List<String> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<String> orgIds) {
        this.orgIds = orgIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSceneIds() {
        return sceneIds;
    }

    public void setSceneIds(String sceneIds) {
        this.sceneIds = sceneIds;
    }
}
