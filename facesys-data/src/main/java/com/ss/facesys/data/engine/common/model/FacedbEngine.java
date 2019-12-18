package com.ss.facesys.data.engine.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.StringJoiner;

/**
 * 人像库绑定引擎关系
 * 
 * @author FrancisYs
 * @date 2019/12/16
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_facedb_engine")
public class FacedbEngine {

    @Id
    private Integer id;
    @Column(name = "facedb_id")
    private Integer facedbId;
    @Column(name = "engine_type")
    private Integer engineType;
    @Column(name = "create_time")
    private Long createTime;

    @Transient
    private List<Integer> facedbIds;
    @Transient
    private Integer bindStatus;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<Integer> getFacedbIds() {
        return facedbIds;
    }

    public void setFacedbIds(List<Integer> facedbIds) {
        this.facedbIds = facedbIds;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FacedbEngine.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("facedbId=" + facedbId)
                .add("engineType=" + engineType)
                .add("createTime=" + createTime)
                .add("facedbIds=" + facedbIds)
                .add("bindStatus=" + bindStatus)
                .toString();
    }

}
