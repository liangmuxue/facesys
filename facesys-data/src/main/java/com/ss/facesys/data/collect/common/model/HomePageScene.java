package com.ss.facesys.data.collect.common.model;

/**
 * com.ss.facesys.data.collect.common.model
 *
 * @author 李爽超 chao
 * @create 2021/03/02
 * @email lishuangchao@ss-cas.com
 **/
public class HomePageScene {

    private Integer sceneId;
    private String sceneName;
    private Integer deviceTotal;
    private Integer captureTotal;
    private String sceneIds;

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
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

    public String getSceneIds() {
        return sceneIds;
    }

    public void setSceneIds(String sceneIds) {
        this.sceneIds = sceneIds;
    }
}
