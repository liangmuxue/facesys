package com.ss.isc.data.collect.common.web;

import com.ss.request.Pagination;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;

import javax.validation.constraints.NotNull;

/**
 * FacedbVO
 *
 * @author FrancisYs
 * @date 2019/9/3
 * @email yaoshuai@ss-cas.com
 */
public class FacedbVO extends Pagination {

    /**
     * 主键id
     */
    @NotNull(message = "{facedb.id.empty}", groups = {APIGetsGroup.class, APIEditGroup.class, APIDeltGroup.class})
    private Integer id;
    /**
     * 人像库名称
     */
    @NotNull(message = "{facedb.name.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String name;
    /**
     * 用途 [1-通用,2-通用+人口管理分析]
     */
    private Integer model;
    /**
     * 标识类型：若model值为2，则此字段必填；对应字典类型：FACEDB_TYPE
     */
    @NotNull(message = "{facedb.type.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String type;
    /**
     * 多个标识类型条件
     */
    private String types;
    /**
     * 当前用户编号
     */
    @NotNull(message = "{userIds.empty}", groups = {APIAddGroup.class, APIEditGroup.class, APIDeltGroup.class})
    private String userIds;

    /**
     * 备注信息
     */
    private String remark;


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

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FacedbVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model=" + model +
                ", type='" + type + '\'' +
                ", types='" + types + '\'' +
                ", userIds='" + userIds + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
