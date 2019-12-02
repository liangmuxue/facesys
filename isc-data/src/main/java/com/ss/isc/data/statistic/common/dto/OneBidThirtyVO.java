package com.ss.isc.data.statistic.common.dto;

public class OneBidThirtyVO {

    private Integer periodType;

    private String villageCode;

    private Integer nowCnt;

    private Double chainRatio;

    private Integer creaseCnt;

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode == null ? null : villageCode.trim();
    }

    public Integer getNowCnt() {
        return nowCnt;
    }

    public void setNowCnt(Integer nowCnt) {
        this.nowCnt = nowCnt;
    }

    public Double getChainRatio() {
        return chainRatio;
    }

    public void setChainRatio(Double chainRatio) {
        this.chainRatio = chainRatio;
    }

    public Integer getCreaseCnt() {
        return creaseCnt;
    }

    public void setCreaseCnt(Integer creaseCnt) {
        this.creaseCnt = creaseCnt;
    }
}