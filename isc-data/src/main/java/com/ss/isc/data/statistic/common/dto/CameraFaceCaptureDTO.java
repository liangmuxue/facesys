package com.ss.isc.data.statistic.common.dto;

import java.io.Serializable;

public class CameraFaceCaptureDTO implements Serializable {

    private String villageName;
    private String villageCode;
    private String deviceIds;

    public String getVillageCode() { return this.villageCode; }


    public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


    public String getDeviceIds() { return this.deviceIds; }


    public void setDeviceIds(String deviceIds) { this.deviceIds = deviceIds; }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
}

