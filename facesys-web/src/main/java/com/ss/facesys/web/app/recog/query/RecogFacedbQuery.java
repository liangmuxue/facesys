package com.ss.facesys.web.app.recog.query;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.StringJoiner;

/**
 * 1:N 注册库参数
 *
 * @author FrancisYs
 * @date 2019/12/20
 * @email yaoshuai@ss-cas.com
 */
public class RecogFacedbQuery {

    private List<Integer> facedbIds;
    private List<String> vplatFacedbIds;
    @NotBlank(message = "{recog.img.empty}")
    private String img;
    @NotNull(message = "{recog.imgType.empty}")
    private Integer imgType;
    private Integer ageB;
    private Integer ageE;
    private Integer gender;
    private Integer topN;
    private Double thresholdMin;
    private Double thresholdMax;
    private String name;
    private String cardId;
    private Integer nation;

    public List<Integer> getFacedbIds() {
        return facedbIds;
    }

    public void setFacedbIds(List<Integer> facedbIds) {
        this.facedbIds = facedbIds;
    }

    public List<String> getVplatFacedbIds() {
        return vplatFacedbIds;
    }

    public void setVplatFacedbIds(List<String> vplatFacedbIds) {
        this.vplatFacedbIds = vplatFacedbIds;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public Integer getAgeB() {
        return ageB;
    }

    public void setAgeB(Integer ageB) {
        this.ageB = ageB;
    }

    public Integer getAgeE() {
        return ageE;
    }

    public void setAgeE(Integer ageE) {
        this.ageE = ageE;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getTopN() {
        return topN;
    }

    public void setTopN(Integer topN) {
        this.topN = topN;
    }

    public Double getThresholdMin() {
        return thresholdMin;
    }

    public void setThresholdMin(Double thresholdMin) {
        this.thresholdMin = thresholdMin;
    }

    public Double getThresholdMax() {
        return thresholdMax;
    }

    public void setThresholdMax(Double thresholdMax) {
        this.thresholdMax = thresholdMax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RecogFacedbQuery.class.getSimpleName() + "[", "]")
                .add("facedbIds=" + facedbIds)
                .add("vplatFacedbIds=" + vplatFacedbIds)
                .add("img='" + img + "'")
                .add("imgType=" + imgType)
                .add("ageB=" + ageB)
                .add("ageE=" + ageE)
                .add("gender=" + gender)
                .add("topN=" + topN)
                .add("thresholdMin=" + thresholdMin)
                .add("thresholdMax=" + thresholdMax)
                .add("name='" + name + "'")
                .add("cardId='" + cardId + "'")
                .add("nation=" + nation)
                .toString();
    }

}
