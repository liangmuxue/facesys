package com.ss.isc.data.resource.common.model;

import com.google.common.collect.Maps;
import com.ss.isc.util.export.excel.annotation.ExcelField;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Camera 设备信息
 * @author FrancisYs
 * @date 2019/8/19
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_camera")
public class Camera implements Serializable {

    private static final long serialVersionUID = -4972616909555429239L;
    @Id
    @NotNull(message = "{camera.id.empty}", groups = {APIEditGroup.class, APIGetsGroup.class, APIDeltGroup.class})
    private Integer id;
    @NotBlank(message = "{camera.villageCode.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String villageCode;
    private String cameraId;
    @NotBlank(message = "{camera.cameraName.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String cameraName;
    private String cameraIp;
    private Integer cameraPort;
    private String loginName;
    private String password;
    private String streamSource;
    private String buildingNo;
    private String installAdd;
    private String prducetBrand;
    private String productModel;
    private String productCode;
    private String companyCode;
    @NotNull(message = "{camera.cameraType.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer cameraType;
    private Integer thridType;
    private Integer inOutFlag;
    @NotNull(message = "{camera.cameraState.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer cameraState;
    @NotNull(message = "{camera.gisType.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer gisType;
    @NotNull(message = "{camera.lon.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Double lon;
    @NotNull(message = "{camera.lat.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Double lat;
    private Double alt;
    private Integer vehicleChannelNo;
    private String camreaChannelID;
    private String thirdCameraId;
    private String standardCameraId;
    private Date createTime;
    private Date updateTime;
    @Transient
    private String regionCode;
    @Transient
    private String sqlString;
    @Transient
    private String userIds;
    @Transient
    private Map<String, String> sqlMap;
    @Transient
    private List<String> villages;
    @Transient
    private String villageCodes;
    @Transient
    private String enumName;
    @Transient
    private List<Integer> cameraList;
    @Transient
    private List<Integer> cameraTypeList;
    @Transient
    private String villageName;

    public String getEnumName() {
        return this.enumName;
    }


    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }


    public List<String> getVillages() {
        return this.villages;
    }


    public void setVillages(List<String> villages) {
        this.villages = villages;
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


    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    @ExcelField(title = "小区编号", type = 0, align = 2, sort = 1)
    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    @ExcelField(title = "摄像机唯一标识", type = 0, align = 2, sort = 2)
    public String getCameraId() {
        return this.cameraId;
    }


    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }


    @ExcelField(title = "摄像头名称", type = 0, align = 2, sort = 3)
    public String getCameraName() {
        return this.cameraName;
    }


    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }


    @ExcelField(title = "IP地址", type = 0, align = 2, sort = 4)
    public String getCameraIp() {
        return this.cameraIp;
    }


    public void setCameraIp(String cameraIp) {
        this.cameraIp = cameraIp;
    }


    @ExcelField(title = "端口", type = 0, align = 2, sort = 5)
    public Integer getCameraPort() {
        return this.cameraPort;
    }


    public void setCameraPort(Integer cameraPort) {
        this.cameraPort = cameraPort;
    }


    @ExcelField(title = "管理账户名", type = 0, align = 2, sort = 6)
    public String getLoginName() {
        return this.loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    @ExcelField(title = "登录密码", type = 0, align = 2, sort = 7)
    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    @ExcelField(title = "视频流源", type = 0, align = 2, sort = 8)
    public String getStreamSource() {
        return this.streamSource;
    }


    public void setStreamSource(String streamSource) {
        this.streamSource = streamSource;
    }


    @ExcelField(title = "楼栋号", type = 0, align = 2, sort = 9)
    public String getBuildingNo() {
        return this.buildingNo;
    }


    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }


    @ExcelField(title = "安装地址", type = 0, align = 2, sort = 10)
    public String getInstallAdd() {
        return this.installAdd;
    }


    public void setInstallAdd(String installAdd) {
        this.installAdd = installAdd;
    }


    @ExcelField(title = "产品品牌", type = 0, align = 2, sort = 11)
    public String getPrducetBrand() {
        return this.prducetBrand;
    }


    public void setPrducetBrand(String prducetBrand) {
        this.prducetBrand = prducetBrand;
    }


    @ExcelField(title = "产品型号", type = 0, align = 2, sort = 12)
    public String getProductModel() {
        return this.productModel;
    }


    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }


    @ExcelField(title = "产品编号", type = 0, align = 2, sort = 13)
    public String getProductCode() {
        return this.productCode;
    }


    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    @ExcelField(title = "维护单位代码", type = 0, align = 2, sort = 14)
    public String getCompanyCode() {
        return this.companyCode;
    }


    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    @ExcelField(title = "类型", type = 0, align = 2, sort = 15)
    public Integer getCameraType() {
        return this.cameraType;
    }


    public void setCameraType(Integer cameraType) {
        this.cameraType = cameraType;
    }


    @ExcelField(title = "第三方Id", type = 0, align = 2, sort = 16)
    public Integer getThridType() {
        return this.thridType;
    }


    public void setThridType(Integer thridType) {
        this.thridType = thridType;
    }


    @ExcelField(title = "拍摄方向位置", type = 0, align = 2, sort = 17)
    public Integer getInOutFlag() {
        return this.inOutFlag;
    }


    public void setInOutFlag(Integer inOutFlag) {
        this.inOutFlag = inOutFlag;
    }


    @ExcelField(title = "在线状态", type = 0, align = 2, sort = 18)
    public Integer getCameraState() {
        return this.cameraState;
    }


    public void setCameraState(Integer cameraState) {
        this.cameraState = cameraState;
    }


    @ExcelField(title = "坐标体系", type = 0, align = 2, sort = 19)
    public Integer getGisType() {
        return this.gisType;
    }


    public void setGisType(Integer gisType) {
        this.gisType = gisType;
    }


    @ExcelField(title = "经度", type = 0, align = 2, sort = 20)
    public Double getLon() {
        return this.lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    @ExcelField(title = "纬度", type = 0, align = 2, sort = 21)
    public Double getLat() {
        return this.lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    @ExcelField(title = "高度", type = 0, align = 2, sort = 22)
    public Double getAlt() {
        return this.alt;
    }


    public void setAlt(Double alt) {
        this.alt = alt;
    }


    @ExcelField(title = "车道号", type = 0, align = 2, sort = 23)
    public Integer getVehicleChannelNo() {
        return this.vehicleChannelNo;
    }


    public void setVehicleChannelNo(Integer vehicleChannelNo) {
        this.vehicleChannelNo = vehicleChannelNo;
    }


    @ExcelField(title = "摄像机通道", type = 0, align = 2, sort = 24)
    public String getCamreaChannelID() {
        return this.camreaChannelID;
    }


    public void setCamreaChannelID(String camreaChannelID) {
        this.camreaChannelID = camreaChannelID;
    }


    @ExcelField(title = "设备id", type = 0, align = 2, sort = 25)
    public String getThirdCameraId() {
        return this.thirdCameraId;
    }


    public void setThirdCameraId(String thirdCameraId) {
        this.thirdCameraId = thirdCameraId;
    }


    @ExcelField(title = "国标摄像机id", type = 0, align = 2, sort = 26)
    public String getStandardCameraId() {
        return this.standardCameraId;
    }


    public void setStandardCameraId(String standardCameraId) {
        this.standardCameraId = standardCameraId;
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


    public String getRegionCode() {
        return this.regionCode;
    }


    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
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


    public List<Integer> getCameraList() {
        return this.cameraList;
    }


    public void setCameraList(List<Integer> cameraList) {
        this.cameraList = cameraList;
    }


    public List<Integer> getCameraTypeList() {
        return this.cameraTypeList;
    }


    public void setCameraTypeList(List<Integer> cameraTypeList) {
        this.cameraTypeList = cameraTypeList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    @Override
    public String toString() {
        return "Camera [villageCode=" + this.villageCode + ", cameraId=" + this.cameraId + ", cameraName=" + this.cameraName + ", installAdd=" + this.installAdd + ", cameraState=" + this.cameraState + "]";
    }

}
