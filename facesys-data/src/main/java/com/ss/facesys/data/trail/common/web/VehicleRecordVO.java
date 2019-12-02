package com.ss.facesys.data.trail.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * VehicleRecordVO 过车记录视图对象
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
public class VehicleRecordVO {

    @NotBlank(message = "车牌号码不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String plateNumber;
    @NotNull(message = "查询过车开始时间不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inOutTimeB;
    @NotNull(message = "查询过车结束时间不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inOutTimeE;
    private String villageCode;
    private String userIds;

    public String getPlateNumber() {
        return this.plateNumber;
    }


    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


    public Date getInOutTimeB() {
        return this.inOutTimeB;
    }


    public void setInOutTimeB(Date inOutTimeB) {
        this.inOutTimeB = inOutTimeB;
    }


    public Date getInOutTimeE() {
        return this.inOutTimeE;
    }


    public void setInOutTimeE(Date inOutTimeE) {
        this.inOutTimeE = inOutTimeE;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    @Override
    public String toString() {
        return "VehicleRecordVO{" +
                "plateNumber='" + plateNumber + '\'' +
                ", inOutTimeB=" + inOutTimeB +
                ", inOutTimeE=" + inOutTimeE +
                ", villageCode='" + villageCode + '\'' +
                ", userIds='" + userIds + '\'' +
                '}';
    }

}
