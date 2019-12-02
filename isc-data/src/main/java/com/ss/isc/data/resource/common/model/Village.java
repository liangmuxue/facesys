package com.ss.isc.data.resource.common.model;

import com.google.common.collect.Maps;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Village：小区
 * @author FrancisYs
 * @date 2019/8/15
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_village")
public class Village implements Serializable {

    private static final long serialVersionUID = -270204728118879526L;
    @Id
    private Integer id;
    @NotBlank(message = "{village.villageCode.empty}", groups = {APIDeltGroup.class})
    private String villageCode;
    @NotBlank(message = "{village.villageName.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String villageName;
    private String provinceCode;
    private String cityCode;
    private String districtCode;
    private String streetCode;
    private String roadCode;
    private String address;
    private int policeStation;
    private String policeStationName;
    private String description;
    @NotNull(message = "{village.gisType.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer gisType;
    @NotNull(message = "{village.lon.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Double lon;
    @NotNull(message = "{village.lat.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Double lat;
    private Double alt;
    private String villagePicUrl;
    private Integer buildingNum;
    private Integer houseNum;
    private Integer residentNum;
    @NotBlank(message = "{village.orgCode.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String orgCode;
    private String pinyin;
    @NotBlank(message = "{village.gisArea.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String gisArea;
    @NotBlank(message = "{village.gisCenter.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String gisCenter;
    private Date createTime;
    private Date updateTime;
    private String thirdId;
    private String thirdRegionCode;
    private Integer state;
    @Transient
    private String cityName;
    @Transient
    private String provinceName;
    @Transient
    private String districtName;
    @Transient
    private String streetName;
    @Transient
    private String orgName;
    @Transient
    private Integer rowNum;
    @Transient
    private List<Latitude> latitudeList = new ArrayList();

    @Transient
    private String userIds;

    @Transient
    private String sqlString;

    @Transient
    private Map<String, String> sqlMap;

    @Transient
    private List<String> villages;

    @Transient
    private Integer pageNum;

    @Transient
    private Integer pageSize;

    @Transient
    private String regionCode;

    @Transient
    private String villageCodes;

    /** 登记人口数量 */
    @Transient
    private Integer peopleCount;
    /** 登记房屋数量 */
    @Transient
    private Integer houseCount;
    /** 登记车辆数量 */
    @Transient
    private Integer vehicleCount;

    /** 社区图片完整访问路径 */
    @Transient
    private String villagePicFullUrl;


    public String getVillageCodes() {
        return this.villageCodes;
    }


    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }


    public String getRegionCode() {
        return this.regionCode;
    }


    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    public Integer getPageNum() {
        return this.pageNum;
    }


    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


    public Integer getPageSize() {
        return this.pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public List<String> getVillages() {
        return this.villages;
    }


    public void setVillages(List<String> villages) {
        this.villages = villages;
    }


    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public String getProvinceCode() {
        return this.provinceCode;
    }


    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }


    public String getCityCode() {
        return this.cityCode;
    }


    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }


    public String getDistrictCode() {
        return this.districtCode;
    }


    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }


    public String getStreetCode() {
        return this.streetCode;
    }


    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }


    public String getRoadCode() {
        return this.roadCode;
    }


    public void setRoadCode(String roadCode) {
        this.roadCode = roadCode;
    }


    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public int getPoliceStation() {
        return this.policeStation;
    }


    public void setPoliceStation(int policeStation) {
        this.policeStation = policeStation;
    }


    public String getPoliceStationName() {
        return this.policeStationName;
    }


    public void setPoliceStationName(String policeStationName) {
        this.policeStationName = policeStationName;
    }


    public String getDescription() {
        return this.description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getGisType() {
        return this.gisType;
    }


    public void setGisType(Integer gisType) {
        this.gisType = gisType;
    }


    public Double getLon() {
        return this.lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    public Double getLat() {
        return this.lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    public Double getAlt() {
        return this.alt;
    }


    public void setAlt(Double alt) {
        this.alt = alt;
    }


    public String getVillagePicUrl() {
        return this.villagePicUrl;
    }


    public void setVillagePicUrl(String villagePicUrl) {
        this.villagePicUrl = villagePicUrl;
    }


    public Integer getBuildingNum() {
        return this.buildingNum;
    }


    public void setBuildingNum(Integer buildingNum) {
        this.buildingNum = buildingNum;
    }


    public Integer getHouseNum() {
        return this.houseNum;
    }


    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }


    public Integer getResidentNum() {
        return this.residentNum;
    }


    public void setResidentNum(Integer residentNum) {
        this.residentNum = residentNum;
    }


    public String getOrgCode() {
        return this.orgCode;
    }


    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }


    public String getPinyin() {
        return this.pinyin;
    }


    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }


    public String getGisArea() {
        return this.gisArea;
    }


    public void setGisArea(String gisArea) {
        this.gisArea = gisArea;
    }


    public String getGisCenter() {
        return this.gisCenter;
    }


    public void setGisCenter(String gisCenter) {
        this.gisCenter = gisCenter;
    }


    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getThirdId() {
        return this.thirdId;
    }


    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }


    public String getThirdRegionCode() {
        return this.thirdRegionCode;
    }


    public void setThirdRegionCode(String thirdRegionCode) {
        this.thirdRegionCode = thirdRegionCode;
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public String getCityName() {
        return this.cityName;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getProvinceName() {
        return this.provinceName;
    }


    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    public String getDistrictName() {
        return this.districtName;
    }


    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }


    public String getStreetName() {
        return this.streetName;
    }


    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }


    public String getOrgName() {
        return this.orgName;
    }


    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    public Integer getRowNum() {
        return this.rowNum;
    }


    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }


    public List<Latitude> getLatitudeList() {
        return this.latitudeList;
    }


    public void setLatitudeList(List<Latitude> latitudeList) {
        this.latitudeList = latitudeList;
    }


    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public String getSqlString() {
        return this.sqlString;
    }


    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }


    public Map<String, String> getSqlMap() {
        if (this.sqlMap == null) {
            this.sqlMap = Maps.newHashMap();
        }
        return this.sqlMap;
    }


    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Integer getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }

    public Integer getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(Integer vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public String getVillagePicFullUrl() {
        return villagePicFullUrl;
    }

    public void setVillagePicFullUrl(String villagePicFullUrl) {
        this.villagePicFullUrl = villagePicFullUrl;
    }

    @Override
    public String toString() {
        return "Village{" +
                "id=" + id +
                ", villageCode='" + villageCode + '\'' +
                ", villageName='" + villageName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", streetCode='" + streetCode + '\'' +
                ", roadCode='" + roadCode + '\'' +
                ", address='" + address + '\'' +
                ", policeStation=" + policeStation +
                ", policeStationName='" + policeStationName + '\'' +
                ", description='" + description + '\'' +
                ", gisType=" + gisType +
                ", lon=" + lon +
                ", lat=" + lat +
                ", alt=" + alt +
                ", villagePicUrl='" + villagePicUrl + '\'' +
                ", buildingNum=" + buildingNum +
                ", houseNum=" + houseNum +
                ", residentNum=" + residentNum +
                ", orgCode='" + orgCode + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", gisArea='" + gisArea + '\'' +
                ", gisCenter='" + gisCenter + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", thirdId='" + thirdId + '\'' +
                ", thirdRegionCode='" + thirdRegionCode + '\'' +
                ", state=" + state +
                ", cityName='" + cityName + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", districtName='" + districtName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", rowNum=" + rowNum +
                ", latitudeList=" + latitudeList +
                ", userIds='" + userIds + '\'' +
                ", sqlString='" + sqlString + '\'' +
                ", sqlMap=" + sqlMap +
                ", villages=" + villages +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", regionCode='" + regionCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", peopleCount=" + peopleCount +
                ", houseCount=" + houseCount +
                ", vehicleCount=" + vehicleCount +
                ", villagePicFullUrl='" + villagePicFullUrl + '\'' +
                '}';
    }

}
