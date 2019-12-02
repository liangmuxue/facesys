package com.ss.isc.data.resource.common.model;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * PeopleFacedbFace 人口人像集关联表
 *
 * @author FrancisYs
 * @date 2019/8/25
 * @deprecated 2019/9/5 <p>人口与人像集的关联关系统一在表ss_facedb_people中管理（同时关联人像库信息）</p>
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_people_facedbface_ref")
public class PeopleFacedbFace implements Serializable {

    private static final long serialVersionUID = -4945355349483717487L;
    private String peopleId;
    private String facedbfaceId;
    @Transient
    private String facePic;
    @Transient
    private String idCardPic;

    public PeopleFacedbFace() {
    }

    public PeopleFacedbFace(String peopleId, String facedbfaceId) {
        this.peopleId = peopleId;
        this.facedbfaceId = facedbfaceId;
    }

    public String getPeopleId() {
        return this.peopleId;
    }


    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }


    public String getFacedbfaceId() {
        return this.facedbfaceId;
    }


    public void setFacedbfaceId(String facedbfaceId) {
        this.facedbfaceId = facedbfaceId;
    }


    public String getFacePic() {
        return this.facePic;
    }


    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }


    public String getIdCardPic() {
        return this.idCardPic;
    }


    public void setIdCardPic(String idCardPic) {
        this.idCardPic = idCardPic;
    }

}
