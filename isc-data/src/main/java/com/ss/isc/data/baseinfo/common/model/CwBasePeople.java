package com.ss.isc.data.baseinfo.common.model;

import java.io.Serializable;

public class CwBasePeople implements Serializable {
    private Integer id;

    private String peopleid;

    private String villagecode;

    private Short peopletype;

    private Integer credentialtype;

    private String credentialno;

    private String peoplename;

    private String gender;

    private String gendercode;

    private String nation;

    private Integer nationcode;

    private String birthdate;

    private String origin;

    private String origincode;

    private String domicile;

    private String domicilecode;

    private String streetcode;

    private String domicileroadname;

    private String domicileroadcode;

    private String domiciledetailaddress;

    private String domicileaddress;

    private String residence;

    private String residencecode;

    private String residenceroadname;

    private String residenceroadcode;

    private String residencedetailaddres;

    private String residenceaddress;

    private String education;

    private Integer educationcode;

    private String political;

    private Integer politicalcode;

    private String maritalstatus;

    private Integer maritalstatuscode;

    private String spousename;

    private String spouseno;

    private String nationality;

    private Integer nationalitycode;

    private Long entrytime;

    private String surnameeng;

    private String nameeng;

    private String phoneno;

    private String idcardpic;

    private String facepic;

    private Short source;

    private Long createtime;

    private Long updatetime;

    private String label;

    private String serviceplace;

    private Short isleave;

    private Long leavetime;

    private String jsondata;

    private Short deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeopleid() {
        return peopleid;
    }

    public void setPeopleid(String peopleid) {
        this.peopleid = peopleid == null ? null : peopleid.trim();
    }

    public String getVillagecode() {
        return villagecode;
    }

    public void setVillagecode(String villagecode) {
        this.villagecode = villagecode == null ? null : villagecode.trim();
    }

    public Short getPeopletype() {
        return peopletype;
    }

    public void setPeopletype(Short peopletype) {
        this.peopletype = peopletype;
    }

    public Integer getCredentialtype() {
        return credentialtype;
    }

    public void setCredentialtype(Integer credentialtype) {
        this.credentialtype = credentialtype;
    }

    public String getCredentialno() {
        return credentialno;
    }

    public void setCredentialno(String credentialno) {
        this.credentialno = credentialno == null ? null : credentialno.trim();
    }

    public String getPeoplename() {
        return peoplename;
    }

    public void setPeoplename(String peoplename) {
        this.peoplename = peoplename == null ? null : peoplename.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getGendercode() {
        return gendercode;
    }

    public void setGendercode(String gendercode) {
        this.gendercode = gendercode == null ? null : gendercode.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public Integer getNationcode() {
        return nationcode;
    }

    public void setNationcode(Integer nationcode) {
        this.nationcode = nationcode;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate == null ? null : birthdate.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getOrigincode() {
        return origincode;
    }

    public void setOrigincode(String origincode) {
        this.origincode = origincode == null ? null : origincode.trim();
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile == null ? null : domicile.trim();
    }

    public String getDomicilecode() {
        return domicilecode;
    }

    public void setDomicilecode(String domicilecode) {
        this.domicilecode = domicilecode == null ? null : domicilecode.trim();
    }

    public String getStreetcode() {
        return streetcode;
    }

    public void setStreetcode(String streetcode) {
        this.streetcode = streetcode == null ? null : streetcode.trim();
    }

    public String getDomicileroadname() {
        return domicileroadname;
    }

    public void setDomicileroadname(String domicileroadname) {
        this.domicileroadname = domicileroadname == null ? null : domicileroadname.trim();
    }

    public String getDomicileroadcode() {
        return domicileroadcode;
    }

    public void setDomicileroadcode(String domicileroadcode) {
        this.domicileroadcode = domicileroadcode == null ? null : domicileroadcode.trim();
    }

    public String getDomiciledetailaddress() {
        return domiciledetailaddress;
    }

    public void setDomiciledetailaddress(String domiciledetailaddress) {
        this.domiciledetailaddress = domiciledetailaddress == null ? null : domiciledetailaddress.trim();
    }

    public String getDomicileaddress() {
        return domicileaddress;
    }

    public void setDomicileaddress(String domicileaddress) {
        this.domicileaddress = domicileaddress == null ? null : domicileaddress.trim();
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence == null ? null : residence.trim();
    }

    public String getResidencecode() {
        return residencecode;
    }

    public void setResidencecode(String residencecode) {
        this.residencecode = residencecode == null ? null : residencecode.trim();
    }

    public String getResidenceroadname() {
        return residenceroadname;
    }

    public void setResidenceroadname(String residenceroadname) {
        this.residenceroadname = residenceroadname == null ? null : residenceroadname.trim();
    }

    public String getResidenceroadcode() {
        return residenceroadcode;
    }

    public void setResidenceroadcode(String residenceroadcode) {
        this.residenceroadcode = residenceroadcode == null ? null : residenceroadcode.trim();
    }

    public String getResidencedetailaddres() {
        return residencedetailaddres;
    }

    public void setResidencedetailaddres(String residencedetailaddres) {
        this.residencedetailaddres = residencedetailaddres == null ? null : residencedetailaddres.trim();
    }

    public String getResidenceaddress() {
        return residenceaddress;
    }

    public void setResidenceaddress(String residenceaddress) {
        this.residenceaddress = residenceaddress == null ? null : residenceaddress.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public Integer getEducationcode() {
        return educationcode;
    }

    public void setEducationcode(Integer educationcode) {
        this.educationcode = educationcode;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political == null ? null : political.trim();
    }

    public Integer getPoliticalcode() {
        return politicalcode;
    }

    public void setPoliticalcode(Integer politicalcode) {
        this.politicalcode = politicalcode;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus == null ? null : maritalstatus.trim();
    }

    public Integer getMaritalstatuscode() {
        return maritalstatuscode;
    }

    public void setMaritalstatuscode(Integer maritalstatuscode) {
        this.maritalstatuscode = maritalstatuscode;
    }

    public String getSpousename() {
        return spousename;
    }

    public void setSpousename(String spousename) {
        this.spousename = spousename == null ? null : spousename.trim();
    }

    public String getSpouseno() {
        return spouseno;
    }

    public void setSpouseno(String spouseno) {
        this.spouseno = spouseno == null ? null : spouseno.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public Integer getNationalitycode() {
        return nationalitycode;
    }

    public void setNationalitycode(Integer nationalitycode) {
        this.nationalitycode = nationalitycode;
    }

    public Long getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Long entrytime) {
        this.entrytime = entrytime;
    }

    public String getSurnameeng() {
        return surnameeng;
    }

    public void setSurnameeng(String surnameeng) {
        this.surnameeng = surnameeng == null ? null : surnameeng.trim();
    }

    public String getNameeng() {
        return nameeng;
    }

    public void setNameeng(String nameeng) {
        this.nameeng = nameeng == null ? null : nameeng.trim();
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getIdcardpic() {
        return idcardpic;
    }

    public void setIdcardpic(String idcardpic) {
        this.idcardpic = idcardpic == null ? null : idcardpic.trim();
    }

    public String getFacepic() {
        return facepic;
    }

    public void setFacepic(String facepic) {
        this.facepic = facepic == null ? null : facepic.trim();
    }

    public Short getSource() {
        return source;
    }

    public void setSource(Short source) {
        this.source = source;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getServiceplace() {
        return serviceplace;
    }

    public void setServiceplace(String serviceplace) {
        this.serviceplace = serviceplace == null ? null : serviceplace.trim();
    }

    public Short getIsleave() {
        return isleave;
    }

    public void setIsleave(Short isleave) {
        this.isleave = isleave;
    }

    public Long getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Long leavetime) {
        this.leavetime = leavetime;
    }

    public String getJsondata() {
        return jsondata;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata == null ? null : jsondata.trim();
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}