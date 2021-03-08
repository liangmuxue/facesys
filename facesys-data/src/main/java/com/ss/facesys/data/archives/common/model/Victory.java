package com.ss.facesys.data.archives.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* 战果汇总
* @author chao
* @create 2020/2/17
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_base_victory")
public class Victory {

    @Id
    private Integer id;
    @Column(name = "victory_name")
    private String victoryName;
    @Transient
    private String userIds;
    @Transient
    private String userNames;
    @Column(name = "solve_time")
    private String solveTime;
    @Column(name = "case_code")
    private String caseCode;
    @Column(name = "case_type")
    private Integer caseType;
    @Column(name = "case_detail")
    private String caseDetail;
    @Column(name = "picture_one")
    private String pictureOne;
    @Column(name = "picture_two")
    private String pictureTwo;
    @Column(name = "picture_three")
    private String pictureThree;
    @Column(name = "picture_four")
    private String pictureFour;
    @Column(name = "picture_five")
    private String pictureFive;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "update_time")
    private Long updateTime;
    private Integer status;
    @Transient
    private String caseTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVictoryName() {
        return victoryName;
    }

    public void setVictoryName(String victoryName) {
        this.victoryName = victoryName;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public String getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(String solveTime) {
        this.solveTime = solveTime;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public String getCaseDetail() {
        return caseDetail;
    }

    public void setCaseDetail(String caseDetail) {
        this.caseDetail = caseDetail;
    }

    public String getPictureOne() {
        return pictureOne;
    }

    public void setPictureOne(String pictureOne) {
        this.pictureOne = pictureOne;
    }

    public String getPictureTwo() {
        return pictureTwo;
    }

    public void setPictureTwo(String pictureTwo) {
        this.pictureTwo = pictureTwo;
    }

    public String getPictureThree() {
        return pictureThree;
    }

    public void setPictureThree(String pictureThree) {
        this.pictureThree = pictureThree;
    }

    public String getPictureFour() {
        return pictureFour;
    }

    public void setPictureFour(String pictureFour) {
        this.pictureFour = pictureFour;
    }

    public String getPictureFive() {
        return pictureFive;
    }

    public void setPictureFive(String pictureFive) {
        this.pictureFive = pictureFive;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }
}