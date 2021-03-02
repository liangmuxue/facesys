package com.ss.facesys.data.access.common.web;

public class AlarmRecordsVO {

    private String userId;
    private String name;
    private String cardId;
    private Integer regdbId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Integer getRegdbId() {
        return regdbId;
    }

    public void setRegdbId(Integer regdbId) {
        this.regdbId = regdbId;
    }
}
