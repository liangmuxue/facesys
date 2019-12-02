package com.ss.model;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/**
* 设备实体类
* @author chao
* @create 2019/9/16
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_base_camera")
public class Camera implements Serializable {

    private static final long serialVersionUID = -4972616909555429239L;
    private Integer id;
    private String villageCode;
    private String cameraId;
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
    private Integer cameraType;
    private Integer thridType;
    private Integer inOutFlag;
    private Integer cameraState;
    private Integer gisType;
    private Double lon;
    private Double lat;
    private Double alt;
    private Integer vehicleChannelNo;
    private String camreaChannelID;
    private String thirdCameraId;
    private String standardCameraId;
    private Date createTime;
    private Date updateTime;
    private Integer flowState;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getCameraId() {
        return this.cameraId;
    }


    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }


    public String getCameraName() {
        return this.cameraName;
    }


    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }


    public String getCameraIp() {
        return this.cameraIp;
    }


    public void setCameraIp(String cameraIp) {
        this.cameraIp = cameraIp;
    }


    public Integer getCameraPort() {
        return this.cameraPort;
    }


    public void setCameraPort(Integer cameraPort) {
        this.cameraPort = cameraPort;
    }


    public String getLoginName() {
        return this.loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getStreamSource() {
        return this.streamSource;
    }


    public void setStreamSource(String streamSource) {
        this.streamSource = streamSource;
    }


    public String getBuildingNo() {
        return this.buildingNo;
    }


    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }


    public String getInstallAdd() {
        return this.installAdd;
    }


    public void setInstallAdd(String installAdd) {
        this.installAdd = installAdd;
    }


    public String getPrducetBrand() {
        return this.prducetBrand;
    }


    public void setPrducetBrand(String prducetBrand) {
        this.prducetBrand = prducetBrand;
    }


    public String getProductModel() {
        return this.productModel;
    }


    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }


    public String getProductCode() {
        return this.productCode;
    }


    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public String getCompanyCode() {
        return this.companyCode;
    }


    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    public Integer getCameraType() {
        return this.cameraType;
    }


    public void setCameraType(Integer cameraType) {
        this.cameraType = cameraType;
    }


    public Integer getThridType() {
        return this.thridType;
    }


    public void setThridType(Integer thridType) {
        this.thridType = thridType;
    }


    public Integer getInOutFlag() {
        return this.inOutFlag;
    }


    public void setInOutFlag(Integer inOutFlag) {
        this.inOutFlag = inOutFlag;
    }


    public Integer getCameraState() {
        return this.cameraState;
    }


    public void setCameraState(Integer cameraState) {
        this.cameraState = cameraState;
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


    public Integer getVehicleChannelNo() {
        return this.vehicleChannelNo;
    }


    public void setVehicleChannelNo(Integer vehicleChannelNo) {
        this.vehicleChannelNo = vehicleChannelNo;
    }


    public String getCamreaChannelID() {
        return this.camreaChannelID;
    }


    public void setCamreaChannelID(String camreaChannelID) {
        this.camreaChannelID = camreaChannelID;
    }


    public String getThirdCameraId() {
        return this.thirdCameraId;
    }


    public void setThirdCameraId(String thirdCameraId) {
        this.thirdCameraId = thirdCameraId;
    }


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


    public Integer getFlowState() {
        return flowState;
    }

    public void setFlowState(Integer flowState) {
        this.flowState = flowState;
    }
}
