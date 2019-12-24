package com.ss.facesys.web.app.facedb.form;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIFeatureExtractionGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 人像集
 *
 * @author FrancisYs
 * @date 2019/12/9
 * @email yaoshuai@ss-cas.com
 */
public class FacedbFaceForm {

    @NotNull(message = "{facedbface.id.empty}", groups = {APIEditGroup.class, APIDeltGroup.class, APIFeatureExtractionGroup.class})
    private Integer id;
    @NotNull(message = "{facedbface.facedbId.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer facedbId;
    @NotBlank(message = "{facedbface.cardType.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String cardType;
    @NotBlank(message = "{facedbface.cardId.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String cardId;
    @NotBlank(message = "{facedbface.name.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String name;
    private String birthday;
    private Integer gender;
    private Integer nation;
    @NotBlank(message = "{facedbface.facePath.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String facePath;
    private String recentPath;
    private String phoneNo;
    private String remark;
    @NotBlank(message = "{userIds.empty}", groups = {APIAddGroup.class, APIEditGroup.class, APIDeltGroup.class})
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public String getFacePath() {
        return facePath;
    }

    public void setFacePath(String facePath) {
        this.facePath = facePath;
    }

    public String getRecentPath() {
        return recentPath;
    }

    public void setRecentPath(String recentPath) {
        this.recentPath = recentPath;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

}
