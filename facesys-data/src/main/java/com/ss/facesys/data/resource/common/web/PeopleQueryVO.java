package com.ss.facesys.data.resource.common.web;

import com.ss.request.Pagination;
import com.ss.valide.APIPageGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Arrays;

/**
 * 实有人口查询VO对象
 * @author FrancisYs
 * @date 2019/08/09
 * @email yaoshuai@ss-cas.com
 */
public class PeopleQueryVO extends Pagination {

    private static final long serialVersionUID = 2779311901985886233L;
    /** 小区编号 */
    private String villageCode;
    private Integer peopleType;
    private String credentialNo;
    private String peopleName;
    /** 性别 */
    private Integer genderCode;
    /** 人员标签 */
    private String[] labelArr;
    /** 当前用户编号 */
    @NotBlank(message = "{people.userIds.empty}", groups = {APIPageGroup.class})
    private String userIds;
    /** 区域编号 */
    private String regionCode;
    /** 多个小区编号 */
    private String villageCodes;

    /*  以下新增字段-FrancisYs-2019/08/09 */
    /** 民族编号 */
    private Integer nationCode;
    /** 是否从我的收藏中搜索 */
    private Integer ifCollection;
    /** 综合搜索框：身份证、手机号、姓名模糊匹配 */
    private String inputQuery;
    /** 人员标签：多个以","拼接 */
    private String labelStr;


    public String getVillageCode() {
        return this.villageCode;
    }
    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }
    public Integer getPeopleType() {
        return this.peopleType;
    }
    public void setPeopleType(Integer peopleType) {
        this.peopleType = peopleType;
    }
    public String getCredentialNo() {
        return this.credentialNo;
    }
    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }
    public String getPeopleName() {
        return this.peopleName;
    }
    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
    public Integer getGenderCode() {
        return this.genderCode;
    }
    public void setGenderCode(Integer genderCode) {
        this.genderCode = genderCode;
    }
    public String[] getLabelArr() {
        return this.labelArr;
    }
    public void setLabelArr(String[] labelArr) {
        this.labelArr = labelArr;
    }
    public String getUserIds() {
        return this.userIds;
    }
    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
    public String getRegionCode() {
        return this.regionCode;
    }
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    public String getVillageCodes() {
        return this.villageCodes;
    }
    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    public Integer getNationCode() {
        return nationCode;
    }
    public void setNationCode(Integer nationCode) {
        this.nationCode = nationCode;
    }

    public Integer getIfCollection() {
        return ifCollection;
    }
    public void setIfCollection(Integer ifCollection) {
        this.ifCollection = ifCollection;
    }
    public String getInputQuery() {
        return inputQuery;
    }
    public void setInputQuery(String inputQuery) {
        this.inputQuery = inputQuery;
    }
    public String getLabelStr() {
        return labelStr;
    }
    public void setLabelStr(String labelStr) {
        this.labelStr = labelStr;
    }

    @Override
    public String toString() {
        return "PeopleQueryVO{" +
                "villageCode='" + villageCode + '\'' +
                ", peopleType=" + peopleType +
                ", credentialNo='" + credentialNo + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", genderCode=" + genderCode +
                ", labelArr=" + Arrays.toString(labelArr) +
                ", userIds='" + userIds + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", nationCode=" + nationCode +
                ", ifCollection='" + ifCollection + '\'' +
                ", inputQuery='" + inputQuery + '\'' +
                ", labelStr='" + labelStr + '\'' +
                '}';
    }

}
