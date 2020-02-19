package com.ss.facesys.data.system.common.model;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.StringJoiner;

/**
 * CollectionPersoncardDetail
 *
 * @author FrancisYs
 * @date 2020/2/18
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_collection_personcard_detail")
public class CollectionPersoncardDetail {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    @Column(name = "capture_id")
    private String captureId;
    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "device_address")
    private String deviceAddress;
    @Column(name = "capture_time")
    private Long captureTime;
    @Column(name = "capture_url")
    private String captureUrl;
    @Column(name = "card_url")
    private String cardUrl;
    @Column(name = "auth_result")
    private Integer authResult;
    @Column(name = "score")
    private Double score;
    @Column(name = "mask_state")
    private Integer maskState;
    @Column(name = "glasses_state")
    private Integer glassesState;
    @Column(name = "sun_glasses_state")
    private Integer sunGlassesState;
    @Column(name = "nation")
    private Integer nation;
    @Column(name = "name")
    private String name;
    @Column(name = "card_gender")
    private Integer cardGender;
    @Column(name = "card_nation")
    private String cardNation;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "address")
    private String address;
    @Column(name = "card_org")
    private String cardOrg;
    @Column(name = "validdate")
    private String validdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaptureId() {
        return captureId;
    }

    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public Long getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Long captureTime) {
        this.captureTime = captureTime;
    }

    public String getCaptureUrl() {
        return captureUrl;
    }

    public void setCaptureUrl(String captureUrl) {
        this.captureUrl = captureUrl;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public Integer getAuthResult() {
        return authResult;
    }

    public void setAuthResult(Integer authResult) {
        this.authResult = authResult;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getMaskState() {
        return maskState;
    }

    public void setMaskState(Integer maskState) {
        this.maskState = maskState;
    }

    public Integer getGlassesState() {
        return glassesState;
    }

    public void setGlassesState(Integer glassesState) {
        this.glassesState = glassesState;
    }

    public Integer getSunGlassesState() {
        return sunGlassesState;
    }

    public void setSunGlassesState(Integer sunGlassesState) {
        this.sunGlassesState = sunGlassesState;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCardGender() {
        return cardGender;
    }

    public void setCardGender(Integer cardGender) {
        this.cardGender = cardGender;
    }

    public String getCardNation() {
        return cardNation;
    }

    public void setCardNation(String cardNation) {
        this.cardNation = cardNation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardOrg() {
        return cardOrg;
    }

    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg;
    }

    public String getValiddate() {
        return validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CollectionPersoncardDetail.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("captureId='" + captureId + "'")
                .add("deviceId='" + deviceId + "'")
                .add("deviceName='" + deviceName + "'")
                .add("deviceAddress='" + deviceAddress + "'")
                .add("captureTime=" + captureTime)
                .add("captureUrl='" + captureUrl + "'")
                .add("cardUrl='" + cardUrl + "'")
                .add("authResult=" + authResult)
                .add("score=" + score)
                .add("maskState=" + maskState)
                .add("glassesState=" + glassesState)
                .add("sunGlassesState=" + sunGlassesState)
                .add("nation=" + nation)
                .add("name='" + name + "'")
                .add("cardGender=" + cardGender)
                .add("cardNation='" + cardNation + "'")
                .add("birthday='" + birthday + "'")
                .add("cardId='" + cardId + "'")
                .add("address='" + address + "'")
                .add("cardOrg='" + cardOrg + "'")
                .add("validdate='" + validdate + "'")
                .toString();
    }

}
