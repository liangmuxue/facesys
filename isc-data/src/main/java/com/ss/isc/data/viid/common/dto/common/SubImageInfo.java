package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;


public class SubImageInfo {
    @JSONField(name = "ImageID")
    @JsonProperty("ImageID")
    private String imageId;
    @JSONField(name = "EventSort")
    @JsonProperty("EventSort")
    private Integer eventSort;
    @JSONField(name = "DeviceID")
    @JsonProperty("DeviceID")
    private String deviceId;
    @JSONField(name = "StoragePath")
    @JsonProperty("StoragePath")
    private String storagePath;
    @JSONField(name = "Type")
    @JsonProperty("Type")
    private String type;
    @JSONField(name = "ShotTime")
    @JsonProperty("ShotTime")
    private String shotTime;
    @JSONField(name = "Width")
    @JsonProperty("Width")
    private Integer width;
    @JSONField(name = "FileFormat")
    @JsonProperty("FileFormat")
    private String fileFormat = "Jpeg";
    @JSONField(name = "Height")
    @JsonProperty("Height")
    private Integer height;
    @JSONField(name = "Data")
    @JsonProperty("Data")
    private String data;
    @JSONField(name = "SnapUuid")
    @JsonProperty("SnapUuid")
    private String snapUuid;
    @JSONField(name = "DeviceChannel")
    @JsonProperty("DeviceChannel")
    private String deviceChannel;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SubImageInfo)) return false;
        SubImageInfo other = (SubImageInfo) o;
        if (!other.canEqual(this)) return false;
        Object this$imageId = getImageId(), other$imageId = other.getImageId();
        if ((this$imageId == null) ? (other$imageId != null) : !this$imageId.equals(other$imageId)) return false;
        Object this$eventSort = getEventSort(), other$eventSort = other.getEventSort();
        if ((this$eventSort == null) ? (other$eventSort != null) : !this$eventSort.equals(other$eventSort))
            return false;
        Object this$deviceId = getDeviceId(), other$deviceId = other.getDeviceId();
        if ((this$deviceId == null) ? (other$deviceId != null) : !this$deviceId.equals(other$deviceId)) return false;
        Object this$storagePath = getStoragePath(), other$storagePath = other.getStoragePath();
        if ((this$storagePath == null) ? (other$storagePath != null) : !this$storagePath.equals(other$storagePath))
            return false;
        Object this$type = getType(), other$type = other.getType();
        if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type)) return false;
        Object this$fileFormat = getFileFormat(), other$fileFormat = other.getFileFormat();
        if ((this$fileFormat == null) ? (other$fileFormat != null) : !this$fileFormat.equals(other$fileFormat))
            return false;
        Object this$shotTime = getShotTime(), other$shotTime = other.getShotTime();
        if ((this$shotTime == null) ? (other$shotTime != null) : !this$shotTime.equals(other$shotTime)) return false;
        Object this$width = getWidth(), other$width = other.getWidth();
        if ((this$width == null) ? (other$width != null) : !this$width.equals(other$width)) return false;
        Object this$height = getHeight(), other$height = other.getHeight();
        if ((this$height == null) ? (other$height != null) : !this$height.equals(other$height)) return false;
        Object this$data = getData(), other$data = other.getData();
        if ((this$data == null) ? (other$data != null) : !this$data.equals(other$data)) return false;
        Object this$snapUuid = getSnapUuid(), other$snapUuid = other.getSnapUuid();
        if ((this$snapUuid == null) ? (other$snapUuid != null) : !this$snapUuid.equals(other$snapUuid)) return false;
        Object this$deviceChannel = getDeviceChannel(), other$deviceChannel = other.getDeviceChannel();
        return !((this$deviceChannel == null) ? (other$deviceChannel != null) : !this$deviceChannel.equals(other$deviceChannel));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SubImageInfo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $imageId = getImageId();
        result = result * 59 + (($imageId == null) ? 0 : $imageId.hashCode());
        Object $eventSort = getEventSort();
        result = result * 59 + (($eventSort == null) ? 0 : $eventSort.hashCode());
        Object $deviceId = getDeviceId();
        result = result * 59 + (($deviceId == null) ? 0 : $deviceId.hashCode());
        Object $storagePath = getStoragePath();
        result = result * 59 + (($storagePath == null) ? 0 : $storagePath.hashCode());
        Object $type = getType();
        result = result * 59 + (($type == null) ? 0 : $type.hashCode());
        Object $fileFormat = getFileFormat();
        result = result * 59 + (($fileFormat == null) ? 0 : $fileFormat.hashCode());
        Object $shotTime = getShotTime();
        result = result * 59 + (($shotTime == null) ? 0 : $shotTime.hashCode());
        Object $width = getWidth();
        result = result * 59 + (($width == null) ? 0 : $width.hashCode());
        Object $height = getHeight();
        result = result * 59 + (($height == null) ? 0 : $height.hashCode());
        Object $data = getData();
        result = result * 59 + (($data == null) ? 0 : $data.hashCode());
        Object $snapUuid = getSnapUuid();
        result = result * 59 + (($snapUuid == null) ? 0 : $snapUuid.hashCode());
        Object $deviceChannel = getDeviceChannel();
        return result * 59 + (($deviceChannel == null) ? 0 : $deviceChannel.hashCode());
    }

    public String toString() {
        return "SubImageInfo(imageId=" + getImageId() + ", eventSort=" + getEventSort() + ", deviceId=" + getDeviceId() + ", storagePath=" + getStoragePath() + ", type=" + getType() + ", fileFormat=" + getFileFormat() + ", shotTime=" + getShotTime() + ", width=" + getWidth() + ", height=" + getHeight() + ", data=" + getData() + ", snapUuid=" + getSnapUuid() + ", deviceChannel=" + getDeviceChannel() + ")";
    }

    public String getImageId() {
        return this.imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getEventSort() {
        return this.eventSort;
    }

    public void setEventSort(Integer eventSort) {
        this.eventSort = eventSort;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStoragePath() {
        return this.storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileFormat() {
        return this.fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getShotTime() {
        return this.shotTime;
    }

    public void setShotTime(String shotTime) {
        this.shotTime = shotTime;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSnapUuid() {
        return this.snapUuid;
    }

    public void setSnapUuid(String snapUuid) {
        this.snapUuid = snapUuid;
    }

    public String getDeviceChannel() {
        return this.deviceChannel;
    }

    public void setDeviceChannel(String deviceChannel) {
        this.deviceChannel = deviceChannel;
    }
}
