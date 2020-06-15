package com.ss.facesys.web.manage.system.form;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

/**
 * ImgCollectionForm
 *
 * @author FrancisYs
 * @date 2020/2/18
 * @email yaoshuai@ss-cas.com
 */
public class ImgCollectionForm {

    @NotNull(message = "{imgCollection.id.empty}", groups = {APIDeltGroup.class})
    private Integer id;
    @NotNull(message = "{imgCollection.dataType.empty}", groups = {APIAddGroup.class})
    private Integer dataType;
    // 不是dataId，是原始数据的主键id
    @NotNull(message = "{imgCollection.captureId.empty}", groups = {APIAddGroup.class})
    private String captureId;
    private String remark;
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getCaptureId() {
        return captureId;
    }

    public void setCaptureId(String captureId) {
        this.captureId = captureId;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", ImgCollectionForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("dataType=" + dataType)
                .add("captureId=" + captureId)
                .add("remark=" + remark)
                .add("userId='" + userId + "'")
                .toString();
    }

}
