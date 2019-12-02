package com.ss.isc.sync.data.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;


@Table(name = "cw_base_camera")
public class Camera implements Serializable {

    private static final long serialVersionUID = -4972616909555429239L;
    private int id;
    private String villageCode;
    private String cameraId;
    private String cameraName;
    private String cameraIp;
    private int cameraPort;
    private String loginName;
    private String password;
    private String streamSource;
    private String buildingNo;
    private String installAdd;
    private String prducetBrand;
    private String productModel;
    private String productCode;
    private String companyCode;
    private int cameraType;
    private int thridType;
    private int inOutFlag;
    private int cameraState;
    private int gisType;
    private Double lon;
    private Double lat;
    private Double alt;
    private int vehicleChannelNo;
    private String camreaChannelId;
    private String thirdCameraId;
    private String standardCameraId;
    private Date createTime;
    private Date updateTime;

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


    public int getCameraPort() {
        return this.cameraPort;
    }


    public void setCameraPort(int cameraPort) {
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


    public int getCameraType() {
        return this.cameraType;
    }


    public void setCameraType(int cameraType) {
        this.cameraType = cameraType;
    }


    public int getThridType() {
        return this.thridType;
    }


    public void setThridType(int thridType) {
        this.thridType = thridType;
    }


    public int getInOutFlag() {
        return this.inOutFlag;
    }


    public void setInOutFlag(int inOutFlag) {
        this.inOutFlag = inOutFlag;
    }


    public int getCameraState() {
        return this.cameraState;
    }


    public void setCameraState(int cameraState) {
        this.cameraState = cameraState;
    }


    public int getGisType() {
        return this.gisType;
    }


    public void setGisType(int gisType) {
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


    public int getVehicleChannelNo() {
        return this.vehicleChannelNo;
    }


    public void setVehicleChannelNo(int vehicleChannelNo) {
        this.vehicleChannelNo = vehicleChannelNo;
    }


    public String getCamreaChannelId() {
        return this.camreaChannelId;
    }


    public void setCamreaChannelId(String camreaChannelId) {
        this.camreaChannelId = camreaChannelId;
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

}
