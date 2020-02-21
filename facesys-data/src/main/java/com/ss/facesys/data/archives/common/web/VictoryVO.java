package com.ss.facesys.data.archives.common.web;

import com.ss.request.Pagination;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
* 战果汇总
* @author chao
* @create 2020/2/17
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_base_victory")
public class VictoryVO {
    @NotNull(message = "{victory.id.empty}", groups = {APIEditGroup.class, APIDeltGroup.class, APIGetsGroup.class})
    private Integer id;
    @NotBlank(message = "{victory.name.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String victoryName;
    private String userIds;
    private String userNames;
    private String beginTime;
    private String endTime;
    private String solveTime;
    @NotNull(message = "{victory.caseCode.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String caseCode;
    private Integer caseType;
    private String caseDetail;
    private String pictureOne;
    private String pictureTwo;
    private String pictureThree;
    private String pictureFour;
    private String pictureFive;
    private String createTime;
    private String updateTime;
    private Integer status;

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

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}