package com.ss.facesys.data.collect.common.model;

/**
 * com.ss.facesys.data.collect.common.model
 *
 * @author 李爽超 chao
 * @create 2021/03/02
 * @email lishuangchao@ss-cas.com
 **/
public class HomePageBase {

    private Integer faceTotal;
    private Integer deviceTotal;
    private Integer captureTotal;
    private Integer focusOnPeopleTotal;
    private Integer alarmTotal;
    private Integer victoryTotal;
    private String sceneIds;

    public Integer getFaceTotal() {
        return faceTotal;
    }

    public void setFaceTotal(Integer faceTotal) {
        this.faceTotal = faceTotal;
    }

    public Integer getDeviceTotal() {
        return deviceTotal;
    }

    public void setDeviceTotal(Integer deviceTotal) {
        this.deviceTotal = deviceTotal;
    }

    public Integer getCaptureTotal() {
        return captureTotal;
    }

    public void setCaptureTotal(Integer captureTotal) {
        this.captureTotal = captureTotal;
    }

    public Integer getFocusOnPeopleTotal() {
        return focusOnPeopleTotal;
    }

    public void setFocusOnPeopleTotal(Integer focusOnPeopleTotal) {
        this.focusOnPeopleTotal = focusOnPeopleTotal;
    }

    public Integer getAlarmTotal() {
        return alarmTotal;
    }

    public void setAlarmTotal(Integer alarmTotal) {
        this.alarmTotal = alarmTotal;
    }

    public Integer getVictoryTotal() {
        return victoryTotal;
    }

    public void setVictoryTotal(Integer victoryTotal) {
        this.victoryTotal = victoryTotal;
    }

    public String getSceneIds() {
        return sceneIds;
    }

    public void setSceneIds(String sceneIds) {
        this.sceneIds = sceneIds;
    }
}
