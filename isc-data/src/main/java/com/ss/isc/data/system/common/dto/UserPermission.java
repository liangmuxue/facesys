package com.ss.isc.data.system.common.dto;

import java.io.Serializable;


public class UserPermission implements Serializable {

    private static final long serialVersionUID = 5544532807949520154L;
    private String orgId;
    private String loginName;
    private String userId;
    private String villageCodesStr;
    private String[] villageCodes;

    public String getOrgId() {
        return this.orgId;
    }


    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public String getLoginName() {
        return this.loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getVillageCodesStr() {
        return this.villageCodesStr;
    }


    public void setVillageCodesStr(String villageCodesStr) {
        this.villageCodesStr = villageCodesStr;
    }


    public String[] getVillageCodes() {
        return this.villageCodes;
    }


    public void setVillageCodes(String[] villageCodes) {
        this.villageCodes = villageCodes;
    }

}
