package com.ss.facesys.data.collect.common.model;

import com.ss.entity.DTEntity;
import com.ss.facesys.util.file.FileConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.StringJoiner;

/**
 * 人像集
 *
 * @author FrancisYs
 * @date 2019/12/9
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_facedb_face")
public class FacedbFace extends DTEntity {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    @Column(name = "face_id")
    private String faceId;
    @Column(name = "facedb_id")
    private Integer facedbId;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "card_id")
    private String cardId;
    private String name;
    private String birthday;
    private Integer gender;
    private Integer nation;
    @Column(name = "face_path")
    private String facePath;
    @Column(name = "card_path")
    private String cardPath;
    @Column(name = "recent_path")
    private String recentPath;
    @Column(name = "phone_no")
    private String phoneNo;
    private Integer state;
    private Integer status;
    private String remark;

    @Transient
    private String birthdayMin;
    @Transient
    private String birthdayMax;
    @Transient
    private List<String> facedbIds;
    @Transient
    private String genderName;
    @Transient
    private String cardTypeName;
    @Transient
    private String stateName;
    @Transient
    private String nationName;
    @Transient
    private Integer age;
    @Transient
    private String facedbName;
    @Transient
    private String facePathFull;
    @Transient
    private String cardPathFull;
    @Transient
    private String recentPathFull;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
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

    public String getCardPath() {
        return cardPath;
    }

    public void setCardPath(String cardPath) {
        this.cardPath = cardPath;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBirthdayMin() {
        return birthdayMin;
    }

    public void setBirthdayMin(String birthdayMin) {
        this.birthdayMin = birthdayMin;
    }

    public String getBirthdayMax() {
        return birthdayMax;
    }

    public void setBirthdayMax(String birthdayMax) {
        this.birthdayMax = birthdayMax;
    }

    public List<String> getFacedbIds() {
        return facedbIds;
    }

    public void setFacedbIds(List<String> facedbIds) {
        this.facedbIds = facedbIds;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFacedbName() {
        return facedbName;
    }

    public void setFacedbName(String facedbName) {
        this.facedbName = facedbName;
    }

    public String getFacePathFull() {
        if (StringUtils.isBlank(this.facePath)) {
            this.facePathFull = "";
        } else if (!this.facePath.contains(FileConstant.FILE_HTTPADD)) {
            this.facePathFull = FilePropertiesUtil.getNginxImgUrl() + this.facePath;
        }
        return facePathFull;
    }

    public void setFacePathFull(String facePathFull) {
        this.facePathFull = facePathFull;
    }

    public String getCardPathFull() {
        if (StringUtils.isBlank(this.cardPath)) {
            this.cardPathFull = "";
        } else if (!this.cardPath.contains(FileConstant.FILE_HTTPADD)) {
            this.cardPathFull = FilePropertiesUtil.getNginxImgUrl() + this.cardPath;
        }
        return cardPathFull;
    }

    public void setCardPathFull(String cardPathFull) {
        this.cardPathFull = cardPathFull;
    }

    public String getRecentPathFull() {
        if (StringUtils.isBlank(this.recentPath)) {
            this.recentPathFull = "";
        } else if (!this.recentPath.contains(FileConstant.FILE_HTTPADD)) {
            this.recentPathFull = FilePropertiesUtil.getNginxImgUrl() + this.recentPath;
        }
        return recentPathFull;
    }

    public void setRecentPathFull(String recentPathFull) {
        this.recentPathFull = recentPathFull;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FacedbFace.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("faceId='" + faceId + "'")
                .add("facedbId='" + facedbId + "'")
                .add("cardType='" + cardType + "'")
                .add("cardId='" + cardId + "'")
                .add("name='" + name + "'")
                .add("birthday='" + birthday + "'")
                .add("gender=" + gender)
                .add("nation=" + nation)
                .add("facePath='" + facePath + "'")
                .add("cardPath='" + cardPath + "'")
                .add("phoneNo='" + phoneNo + "'")
                .add("state=" + state)
                .add("status=" + status)
                .add("remark='" + remark + "'")
                .toString();
    }

}
