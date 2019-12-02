package com.ss.util.nasstorage.node;


public class NasstorageNode {

    private String serverDeputy;
    private String mountDeputy;
    private String mountPath;
    private Integer weight;

    public NasstorageNode(String serverDeputy, String mountDeputy, String mountPath, Integer weight) {
        this.serverDeputy = serverDeputy;
        this.mountDeputy = mountDeputy;
        this.mountPath = mountPath;
        this.weight = weight;
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


    public Integer getWeight() {
        return this.weight;
    }


    public void setWeight(Integer weight) {
        this.weight = weight;
    }


    public String toString() {
        return "NasstorageNode [serverDeputy=" + this.serverDeputy + ", mountDeputy=" + this.mountDeputy + ", mountPath=" + this.mountPath + ", weight=" + this.weight + "]";
    }

}
