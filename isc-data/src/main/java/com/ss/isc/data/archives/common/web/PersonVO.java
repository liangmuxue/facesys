package com.ss.isc.data.archives.common.web;

import java.util.List;
import com.ss.isc.data.baseinfo.common.dto.EnumVO;

public class PersonVO {
  private String idCardPic;
  private String genderCode;
  private String peopleName;
  private String peopleType;
  private String credentialNo;
  private String birthDate;
  private Integer age;
  private String relation;
  private String phoneNo;
  private String label;
  private List<EnumVO> labels;
  private List<EnumVO> eList;
  private Integer isLeave;
  private Integer peopleRelation;

  public String getIdCardPic() { return this.idCardPic; }


  public void setIdCardPic(String idCardPic) { this.idCardPic = idCardPic; }


  public String getGenderCode() { return this.genderCode; }


  public void setGenderCode(String genderCode) { this.genderCode = genderCode; }


  public String getPeopleName() { return this.peopleName; }


  public void setPeopleName(String peopleName) { this.peopleName = peopleName; }


  public String getPeopleType() { return this.peopleType; }


  public void setPeopleType(String peopleType) { this.peopleType = peopleType; }


  public String getCredentialNo() { return this.credentialNo; }


  public void setCredentialNo(String credentialNo) { this.credentialNo = credentialNo; }


  public String getBirthDate() { return this.birthDate; }


  public void setBirthDate(String birthDate) { this.birthDate = birthDate; }


  public Integer getAge() { return this.age; }


  public void setAge(Integer age) { this.age = age; }


  public String getRelation() { return this.relation; }


  public void setRelation(String relation) { this.relation = relation; }


  public String getPhoneNo() { return this.phoneNo; }


  public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }


  public String getLabel() { return this.label; }


  public void setLabel(String label) { this.label = label; }


  public List<EnumVO> geteList() { return this.eList; }


  public void seteList(List<EnumVO> eList) { this.eList = eList; }


  public Integer getIsLeave() { return this.isLeave; }


  public void setIsLeave(Integer isLeave) { this.isLeave = isLeave; }



  public String toString() { return "PersonVO [idCardPic=" + this.idCardPic + ", genderCode=" + this.genderCode + ", peopleName=" + this.peopleName + ", peopleType=" + this.peopleType + ", credentialNo=" + this.credentialNo + ", birthDate=" + this.birthDate + ", age=" + this.age + ", relation=" + this.relation + ", phoneNo=" + this.phoneNo + ", label=" + this.label + ", eList=" + this.eList + ", isLeave=" + this.isLeave + "]"; }

    public List<EnumVO> getLabels() {
        return labels;
    }

    public void setLabels(List<EnumVO> labels) {
        this.labels = labels;
    }

  public Integer getPeopleRelation() {
    return peopleRelation;
  }

  public void setPeopleRelation(Integer peopleRelation) {
    this.peopleRelation = peopleRelation;
  }
}
