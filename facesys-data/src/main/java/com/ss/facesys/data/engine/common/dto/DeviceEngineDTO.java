package com.ss.facesys.data.engine.common.dto;

import java.util.List;
import java.util.StringJoiner;

/**
 * 设备绑定引擎关系
 *
 * @author FrancisYs
 * @date 2019/12/17
 * @email yaoshuai@ss-cas.com
 */
public class DeviceEngineDTO {

    private Integer id;
    private Integer deviceId;
    private Integer engineType;
    private String cameraName;
    private String orgId;
    private String orgCname;
    private List<Integer> engineTypeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getEngineType() {
        return engineType;
    }

    public void setEngineType(Integer engineType) {
        this.engineType = engineType;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCname() {
        return orgCname;
    }

    public void setOrgCname(String orgCname) {
        this.orgCname = orgCname;
    }

    public List<Integer> getEngineTypeList() {
        return engineTypeList;
    }

    public void setEngineTypeList(List<Integer> engineTypeList) {
        this.engineTypeList = engineTypeList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeviceEngineDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deviceId=" + deviceId)
                .add("engineType=" + engineType)
                .add("cameraName='" + cameraName + "'")
                .add("orgId='" + orgId + "'")
                .add("orgCname='" + orgCname + "'")
                .add("engineTypeList=" + engineTypeList)
                .toString();
    }

}
