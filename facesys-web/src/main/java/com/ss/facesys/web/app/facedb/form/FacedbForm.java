package com.ss.facesys.web.app.facedb.form;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIFeatureExtractionGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

/**
 * FacedbForm
 *
 * @author FrancisYs
 * @date 2019/12/6
 * @email yaoshuai@ss-cas.com
 */
public class FacedbForm {

    @NotNull(message = "{facedb.id.empty}", groups = {APIEditGroup.class, APIDeltGroup.class, APIFeatureExtractionGroup.class})
    private Integer id;
    @NotBlank(message = "{facedb.name.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String name;
    @NotBlank(message = "{facedb.orgId.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String orgId;
    @NotNull(message = "{facedb.model.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer model;
    private String type;
    private String remark;
    @NotBlank(message = "{userIds.empty}", groups = {APIAddGroup.class, APIEditGroup.class, APIDeltGroup.class})
    private String userId;
    @NotNull(message = "{facedb.faceDBFaceStateInvalid.empty}", groups = {APIFeatureExtractionGroup.class})
    private Integer faceDBFaceStateInvalid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getFaceDBFaceStateInvalid() {
        return faceDBFaceStateInvalid;
    }

    public void setFaceDBFaceStateInvalid(Integer faceDBFaceStateInvalid) {
        this.faceDBFaceStateInvalid = faceDBFaceStateInvalid;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FacedbForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("orgId='" + orgId + "'")
                .add("model=" + model)
                .add("type='" + type + "'")
                .add("remark='" + remark + "'")
                .add("userId='" + userId + "'")
                .add("faceDBFaceStateInvalid='" + faceDBFaceStateInvalid + "'")
                .toString();
    }

}
