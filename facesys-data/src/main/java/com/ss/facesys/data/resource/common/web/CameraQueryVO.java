package com.ss.facesys.data.resource.common.web;

import com.google.common.collect.Maps;
import com.ss.request.Pagination;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Map;

/**
 * @Description 像机查询对象
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public class CameraQueryVO extends Pagination {

    private String villageCode;
    private String villageCodes;
    private String cameraName;
    private String prducetBrand;
    private Integer cameraType;
    private Integer cameraState;
    private String userIds;
    private Map<String, String> sqlMap;

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

    @Override
    public String toString() {
        return "CameraQueryVO{" +
                "villageCode='" + villageCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", cameraName='" + cameraName + '\'' +
                ", prducetBrand='" + prducetBrand + '\'' +
                ", cameraType=" + cameraType +
                ", cameraState=" + cameraState +
                ", userIds='" + userIds + '\'' +
                ", sqlMap=" + sqlMap +
                '}';
    }

}
