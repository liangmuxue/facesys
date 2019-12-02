package com.ss.isc.data.resource.common.model;

import java.io.Serializable;
import javax.persistence.Table;

/**
 * Facedb 小区关联人像库信息
 *
 * @author FrancisYs
 * @date 2019/8/22
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_village_facedb")
public class Facedb implements Serializable {

    private static final long serialVersionUID = 963711565171744136L;
    private String villageCode;
    private String facedbId;

    public String getFacedbId() {
        return this.facedbId;
    }

    public void setFacedbId(String facedbId) {
        this.facedbId = facedbId;
    }

    public String getVillageCode() {
        return this.villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public Facedb(String villageCode, String facedbId) {
        this.villageCode = villageCode;
        this.facedbId = facedbId;
    }

}
