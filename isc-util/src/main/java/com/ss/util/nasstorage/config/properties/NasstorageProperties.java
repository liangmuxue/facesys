package com.ss.util.nasstorage.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "cw.nasstorage")
public class NasstorageProperties {

    private String serverDeputy;
    private String mountDeputy;
    private String mountPath;
    private String weight;
    private String folderFacepic;
    private String folderFullpic;
    private Integer faceGroupSubDirCount;

    public Integer getFaceGroupSubDirCount() {
        return this.faceGroupSubDirCount;
    }


    public void setFaceGroupSubDirCount(Integer faceGroupSubDirCount) {
        this.faceGroupSubDirCount = faceGroupSubDirCount;
    }


    public String getServerDeputy() {
        return this.serverDeputy;
    }


    public void setServerDeputy(String serverDeputy) {
        this.serverDeputy = serverDeputy;
    }


    public String getMountDeputy() {
        return this.mountDeputy;
    }


    public void setMountDeputy(String mountDeputy) {
        this.mountDeputy = mountDeputy;
    }


    public String getMountPath() {
        return this.mountPath;
    }


    public void setMountPath(String mountPath) {
        this.mountPath = mountPath;
    }


    public String getWeight() {
        return this.weight;
    }


    public void setWeight(String weight) {
        this.weight = weight;
    }


    public String getFolderFacepic() {
        return this.folderFacepic;
    }


    public void setFolderFacepic(String folderFacepic) {
        this.folderFacepic = folderFacepic;
    }


    public String getFolderFullpic() {
        return this.folderFullpic;
    }


    public void setFolderFullpic(String folderFullpic) {
        this.folderFullpic = folderFullpic;
    }

}
