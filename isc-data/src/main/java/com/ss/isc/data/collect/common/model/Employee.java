package com.ss.isc.data.collect.common.model;

import java.io.Serializable;
import javax.persistence.Table;


























@Table
public class Employee
  implements Serializable
{
  private static final long serialVersionUID = 208805052306605642L;
  private int id;
  private String name;
  private String sex;
  private String nation;
  private String ipcard;
  private String address;
  private String company;
  private String telephone;
  private int companyId;
  private String rowNum;
  
  public String getRowNum() { return this.rowNum; }


  
  public void setRowNum(String rowNum) { this.rowNum = rowNum; }


  
  public int getId() { return this.id; }


  
  public void setId(int id) { this.id = id; }


  
  public String getName() { return this.name; }


  
  public void setName(String name) { this.name = name; }


  
  public String getSex() { return this.sex; }


  
  public void setSex(String sex) { this.sex = sex; }


  
  public String getNation() { return this.nation; }


  
  public void setNation(String nation) { this.nation = nation; }


  
  public String getIpcard() { return this.ipcard; }


  
  public void setIpcard(String ipcard) { this.ipcard = ipcard; }


  
  public String getAddress() { return this.address; }


  
  public void setAddress(String address) { this.address = address; }


  
  public String getCompany() { return this.company; }


  
  public void setCompany(String company) { this.company = company; }


  
  public String getTelephone() { return this.telephone; }


  
  public void setTelephone(String telephone) { this.telephone = telephone; }


  
  public int getCompanyId() { return this.companyId; }


  
  public void setCompanyId(int companyId) { this.companyId = companyId; }


  
  public static long getSerialversionuid() { return 208805052306605642L; }
}
