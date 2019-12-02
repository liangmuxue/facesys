package com.ss.facesys.data.collect.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * VehicleVO
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
public class VehicleVO {

    private String plateNumber;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inOutTimeB;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inOutTimeE;

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


    @Override
    public String toString() {
        return "VehicleVO [plateNumber=" + this.plateNumber + ", inOutTimeB=" + this.inOutTimeB + ", inOutTimeE=" + this.inOutTimeE + "]";
    }

}
