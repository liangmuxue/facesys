package com.ss.facesys.web.app.device.query;

import com.ss.request.Pagination;
import com.ss.valide.APIGetsGroup;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

/**
 * DevicePersoncardQuery
 *
 * @author FrancisYs
 * @date 2020/2/11
 * @email yaoshuai@ss-cas.com
 */
public class DevicePersoncardQuery extends Pagination {

    @NotNull(message = "{personcard.id.empty}", groups = {APIGetsGroup.class})
    private Integer id;
    private String deviceName;
    private String deviceCode;
    private String orgId;
    private String userId;

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DevicePersoncardQuery.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deviceName='" + deviceName + "'")
                .add("deviceCode='" + deviceCode + "'")
                .add("orgId='" + orgId + "'")
                .add("userId='" + userId + "'")
                .toString();
    }

}
