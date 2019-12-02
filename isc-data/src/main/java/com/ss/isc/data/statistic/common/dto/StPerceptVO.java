package com.ss.isc.data.statistic.common.dto;

public class StPerceptVO {
    private String id;

    private Integer statDate;

    private Integer nowCnt;

    private Integer periodType;

    private String villageCode;

    private Integer dataType;

    private String villageName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getStatDate() {
        return statDate;
    }

    public void setStatDate(Integer statDate) {
        this.statDate = statDate;
    }

    public Integer getNowCnt() {
        return nowCnt;
    }

    public void setNowCnt(Integer nowCnt) {
        this.nowCnt = nowCnt;
    }

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

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName == null ? null : villageName.trim();
    }
}