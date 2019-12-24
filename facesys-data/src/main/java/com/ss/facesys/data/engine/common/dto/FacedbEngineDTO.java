package com.ss.facesys.data.engine.common.dto;

import java.util.List;
import java.util.StringJoiner;

/**
 * 人像库绑定引擎关系
 *
 * @author FrancisYs
 * @date 2019/12/16
 * @email yaoshuai@ss-cas.com
 */
public class FacedbEngineDTO {

    private Integer id;
    private Integer facedbId;
    private Integer engineType;
    private String name;
    private String orgId;
    private String orgCname;
    private List<Integer> engineTypeList;
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacedbId() {
        return facedbId;
    }

    public void setFacedbId(Integer facedbId) {
        this.facedbId = facedbId;
    }

    public Integer getEngineType() {
        return engineType;
    }

    public void setEngineType(Integer engineType) {
        this.engineType = engineType;
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

    public String getOrgCname() {
        return orgCname;
    }

    public void setOrgCname(String orgCname) {
        this.orgCname = orgCname;
    }

    public List<Integer> getEngineTypeList() {
        return engineTypeList;
    }

    public void setEngineTypeList(List<Integer> engineTypeList) {
        this.engineTypeList = engineTypeList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FacedbEngineDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("facedbId=" + facedbId)
                .add("engineType=" + engineType)
                .add("name='" + name + "'")
                .add("orgId='" + orgId + "'")
                .add("orgCname='" + orgCname + "'")
                .add("engineTypeList='" + engineTypeList + "'")
                .add("userId='" + userId + "'")
                .toString();
    }

}
