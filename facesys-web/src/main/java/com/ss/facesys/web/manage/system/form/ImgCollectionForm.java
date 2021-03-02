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
    @NotNull(message = "{imgCollection.dataId.empty}", groups = {APIAddGroup.class})
    private Integer dataId;
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

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
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
        return "ImgCollectionForm{" +
                "id=" + id +
                ", dataType=" + dataType +
                ", dataId='" + dataId + '\'' +
                ", remark='" + remark + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
