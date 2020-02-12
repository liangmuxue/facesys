package com.ss.facesys.web.app.device.form;

import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

/**
 * DevicePersoncardForm
 *
 * @author FrancisYs
 * @date 2020/2/11
 * @email yaoshuai@ss-cas.com
 */
public class DevicePersoncardForm {

    @NotNull(message = "{personcard.id.empty}", groups = {APIEditGroup.class, APIDeltGroup.class})
    private Integer id;
    private String deviceName;
    private String deviceCode;
    private String ip;
    private Integer relAddressType;
    private Integer relAddressId;
    @NotBlank(message = "{personcard.orgId.empty}", groups = {APIEditGroup.class})
    private String orgId;
    private Integer onlineState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getRelAddressType() {
        return relAddressType;
    }

    public void setRelAddressType(Integer relAddressType) {
        this.relAddressType = relAddressType;
    }

    public Integer getRelAddressId() {
        return relAddressId;
    }

    public void setRelAddressId(Integer relAddressId) {
        this.relAddressId = relAddressId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(Integer onlineState) {
        this.onlineState = onlineState;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DevicePersoncardForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deviceName='" + deviceName + "'")
                .add("deviceCode='" + deviceCode + "'")
                .add("ip='" + ip + "'")
                .add("relAddressType=" + relAddressType)
                .add("relAddressId=" + relAddressId)
                .add("orgId='" + orgId + "'")
                .add("onlineState=" + onlineState)
                .toString();
    }

}
