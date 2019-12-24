package com.ss.facesys.web.app.facedb.form;

import com.ss.facesys.data.engine.validate.APIEngineBindGroup;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

/**
 * 设备绑定引擎
 *
 * @author FrancisYs
 * @date 2019/12/17
 * @email yaoshuai@ss-cas.com
 */
public class DeviceEngineBindForm implements Serializable {

    private static final long serialVersionUID = 4484431435782449521L;

    @NotEmpty(message = "{device.ids.empty}", groups = {APIEngineBindGroup.class})
    private List<Integer> deviceIds;
    @NotNull(message = "{engine.type.empty}", groups = {APIEngineBindGroup.class})
    @Range(min = 1L, max = 6L, message = "engine.type.not.support", groups = {APIEngineBindGroup.class})
    private Integer engineType;
    @NotNull(message = "{device.engine.bind.status.empty}", groups = {APIEngineBindGroup.class})
    @Range(max = 1L, message = "{device.engine.bind.status.invalid.ange}", groups = {APIEngineBindGroup.class})
    private Integer bindStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Integer> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Integer> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public Integer getEngineType() {
        return engineType;
    }

    public void setEngineType(Integer engineType) {
        this.engineType = engineType;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeviceEngineBindForm.class.getSimpleName() + "[", "]")
                .add("deviceIds=" + deviceIds)
                .add("engineType=" + engineType)
                .add("bindStatus=" + bindStatus)
                .toString();
    }

}
