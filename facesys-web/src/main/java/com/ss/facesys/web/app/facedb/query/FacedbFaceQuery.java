package com.ss.facesys.web.app.facedb.query;

import com.ss.request.Pagination;
import com.ss.valide.APIGetsGroup;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

/**
 * 人像集
 *
 * @author FrancisYs
 * @date 2019/12/9
 * @email yaoshuai@ss-cas.com
 */
public class FacedbFaceQuery extends Pagination {

    @NotNull(message = "{facedbface.id.empty}", groups = {APIGetsGroup.class})
    private Integer id;
    private String facedbId;
    private String cardId;
    private String name;
    private Integer gender;
    private String phoneNo;
    private Integer state;
    private Integer ageMin;
    private Integer ageMax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacedbId() {
        return facedbId;
    }

    public void setFacedbId(String facedbId) {
        this.facedbId = facedbId;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FacedbFaceQuery.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("facedbId='" + facedbId + "'")
                .add("cardId='" + cardId + "'")
                .add("name='" + name + "'")
                .add("gender=" + gender)
                .add("phoneNo='" + phoneNo + "'")
                .add("state=" + state)
                .add("ageMin=" + ageMin)
                .add("ageMax=" + ageMax)
                .toString();
    }

}
