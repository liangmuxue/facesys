package com.ss.isc.data.resource.common.model;

import com.ss.isc.util.export.excel.annotation.ExcelField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Tollgate 车辆卡口
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_vehicle_tollgate")
public class Tollgate implements Serializable {

    private static final long serialVersionUID = 8225214142174962051L;
    @NotNull(message = "{tollgate.id.empty}", groups = {APIGetsGroup.class, APIDeltGroup.class, APIEditGroup.class})
    private Integer id;
    @NotBlank(message = "{tollgate.villageCode.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String villageCode;
    private String tollgateID;
    @NotBlank(message = "{tollgate.tollgateName.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String tollgateName;
    private Double lon;
    private Double lat;
    private Double alt;
    private Integer gisType;
    private String place;
    @NotNull(message = "{tollgate.status.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer status;
    @NotNull(message = "{tollgate.tollgateType.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer tollgateType;
    @Transient
    private String tollgateTypeName;
    private String tollgatePicUrl;
    private Integer parkNum;
    private String remark;
    private Date createTime;
    private Date updateTime;
    @Transient
    private List<String> villages;
    @Transient
    private String villageName;
    @Transient
    private String enumName;
    @Transient
    private Integer pageNum;
    @Transient
    private Integer pageSize;
    @Transient
    private String sqlString;
    @Transient
    private String userIds;
    @Transient
    private String villageCodes;
    @Transient
    private String regionCode;
    @Transient
    private Map<String, String> sqlMap;
    @Transient
    private Integer isDelete;

    @Transient
    private String statusDesc;

    public Map<String, String> getSqlMap() {
        return this.sqlMap;
    }


    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }


    public String getEnumName() {
        return this.enumName;
    }


    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public List<String> getVillages() {
        return this.villages;
    }


    public void setVillages(List<String> villages) {
        this.villages = villages;
    }


    public Tollgate() {
    }


    public Tollgate(Integer id, String villageCode, String tollgateID, String tollgateName,
                    Double lon, Double lat, Double alt, Integer gisType, String place, Integer status,
                    Integer tollgateType, String tollgatePicUrl, Integer parkNum, String remark, Date createTime,
                    Date updateTime) {
        this.id = id;
        this.villageCode = villageCode;
        this.tollgateID = tollgateID;
        this.tollgateName = tollgateName;
        this.lon = lon;
        this.lat = lat;
        this.alt = alt;
        this.gisType = gisType;
        this.place = place;
        this.status = status;
        this.tollgateType = tollgateType;
        this.tollgatePicUrl = tollgatePicUrl;
        this.parkNum = parkNum;
        this.remark = remark;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }


    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    @ExcelField(title = "小区编号", type = 0, align = 2, sort = 2)
    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    @ExcelField(title = "卡口id", type = 0, align = 2, sort = 1)
    public String getTollgateID() {
        return this.tollgateID;
    }


    public void setTollgateID(String tollgateID) {
        this.tollgateID = tollgateID;
    }


    @ExcelField(title = "车辆卡口名称", type = 0, align = 2, sort = 3)
    public String getTollgateName() {
        return this.tollgateName;
    }


    public void setTollgateName(String tollgateName) {
        this.tollgateName = tollgateName;
    }


    @ExcelField(title = "经度", type = 0, align = 2, sort = 4)
    public Double getLon() {
        return this.lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    @ExcelField(title = "纬度", type = 0, align = 2, sort = 5)
    public Double getLat() {
        return this.lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    @ExcelField(title = "高度", type = 0, align = 2, sort = 6)
    public Double getAlt() {
        return this.alt;
    }


    public void setAlt(Double alt) {
        this.alt = alt;
    }


    @ExcelField(title = "坐标类型", type = 0, align = 2, sort = 7)
    public Integer getGisType() {
        return this.gisType;
    }


    public void setGisType(Integer gisType) {
        this.gisType = gisType;
    }


    @ExcelField(title = "具体位置", type = 0, align = 2, sort = 8)
    public String getPlace() {
        return this.place;
    }


    public void setPlace(String place) {
        this.place = place;
    }


    @ExcelField(title = "状态", type = 0, align = 2, sort = 9)
    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    @ExcelField(title = "类型", type = 0, align = 2, sort = 10)
    public Integer getTollgateType() {
        return this.tollgateType;
    }


    public void setTollgateType(Integer tollgateType) {
        this.tollgateType = tollgateType;
    }


    @ExcelField(title = "图片", type = 0, align = 2, sort = 11)
    public String getTollgatePicUrl() {
        return this.tollgatePicUrl;
    }


    public void setTollgatePicUrl(String tollgatePicUrl) {
        this.tollgatePicUrl = tollgatePicUrl;
    }


    public Integer getParkNum() {
        return this.parkNum;
    }


    public void setParkNum(Integer parkNum) {
        this.parkNum = parkNum;
    }


    @ExcelField(title = "备注", type = 0, align = 2, sort = 12)
    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
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


    public String getSqlString() {
        return this.sqlString;
    }


    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }


    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public String getTollgateTypeName() {
        return this.tollgateTypeName;
    }


    public void setTollgateTypeName(String tollgateTypeName) {
        this.tollgateTypeName = tollgateTypeName;
    }


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


    public Integer getIsDelete() {
        return this.isDelete;
    }


    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

}
