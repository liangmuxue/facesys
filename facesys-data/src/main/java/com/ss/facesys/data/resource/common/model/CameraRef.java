package com.ss.facesys.data.resource.common.model;

import java.io.Serializable;
import javax.persistence.Table;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIListGroup;
import org.hibernate.validator.constraints.NotBlank;

/**
 * CameraRef 相机关联数据
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_camera_ref")
public class CameraRef implements Serializable {

    private static final long serialVersionUID = 4123936698213757195L;
    @NotBlank(message = "{cameraRef.locationId.empty}", groups = {APIListGroup.class, APIAddGroup.class})
    private String locationId;
    private String cameraId;
    private Integer type;

    public String getLocationId() {
        return this.locationId;
    }


    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }


    public String getCameraId() {
        return this.cameraId;
    }


    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }


    public Integer getType() {
        return this.type;
    }


    public void setType(Integer type) {
        this.type = type;
    }

}
