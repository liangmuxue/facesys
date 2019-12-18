package com.ss.facesys.data.engine.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

/**
 * 设备绑定引擎关系
 *
 * @author FrancisYs
 * @date 2019/12/16
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_device_engine")
public class DeviceEngine {

    @Id
    private Integer id;
    @Column(name = "device_id")
    private Integer deviceId;
    @Column(name = "engine_type")
    private Integer engineType;
    @Column(name = "create_time")
    private Long createTime;

    @Transient
    private List<Integer> deviceIds;
    @Transient
    private Integer bindStatus;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<Integer> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Integer> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeviceEngine.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deviceId=" + deviceId)
                .add("engineType=" + engineType)
                .add("createTime=" + createTime)
                .add("deviceIds=" + deviceIds)
                .add("bindStatus=" + bindStatus)
                .toString();
    }

}
