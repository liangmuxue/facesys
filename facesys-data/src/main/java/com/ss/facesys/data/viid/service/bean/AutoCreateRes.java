package com.ss.facesys.data.viid.service.bean;

import java.beans.ConstructorProperties;
import java.util.List;

public class AutoCreateRes {
    private List<String> autoCreateLibs;
    private List<String> autoCreateFaces;

    public AutoCreateRes() {
    }

    @ConstructorProperties({"autoCreateLibs", "autoCreateFaces"})
    public AutoCreateRes(List<String> autoCreateLibs, List<String> autoCreateFaces) {
        this.autoCreateLibs = autoCreateLibs;
        this.autoCreateFaces = autoCreateFaces;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AutoCreateRes)) return false;
        AutoCreateRes other = (AutoCreateRes) o;
        if (!other.canEqual(this)) return false;
        Object this$autoCreateLibs = getAutoCreateLibs(), other$autoCreateLibs = other.getAutoCreateLibs();
        if ((this$autoCreateLibs == null) ? (other$autoCreateLibs != null) : !this$autoCreateLibs.equals(other$autoCreateLibs))
            return false;
        Object this$autoCreateFaces = getAutoCreateFaces(), other$autoCreateFaces = other.getAutoCreateFaces();
        return !((this$autoCreateFaces == null) ? (other$autoCreateFaces != null) : !this$autoCreateFaces.equals(other$autoCreateFaces));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AutoCreateRes;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $autoCreateLibs = getAutoCreateLibs();
        result = result * 59 + (($autoCreateLibs == null) ? 0 : $autoCreateLibs.hashCode());
        Object $autoCreateFaces = getAutoCreateFaces();
        return result * 59 + (($autoCreateFaces == null) ? 0 : $autoCreateFaces.hashCode());
    }

    public String toString() {
        return "AutoCreateRes(autoCreateLibs=" + getAutoCreateLibs() + ", autoCreateFaces=" + getAutoCreateFaces() + ")";
    }

    public List<String> getAutoCreateLibs() {
        return this.autoCreateLibs;
    }

    public void setAutoCreateLibs(List<String> autoCreateLibs) {
        this.autoCreateLibs = autoCreateLibs;
    }

    public List<String> getAutoCreateFaces() {
        return this.autoCreateFaces;
    }

    public void setAutoCreateFaces(List<String> autoCreateFaces) {
        this.autoCreateFaces = autoCreateFaces;
    }
}
