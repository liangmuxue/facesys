package com.ss.facesys.data.collect.common.model;

























public class PeopleCount
{
  private String name;
  private String image;
  private String count;
  private String type;
  
  public String getName() { return this.name; }


  
  public void setName(String name) { this.name = name; }


  
  public String getImage() { return this.image; }


  
  public void setImage(String image) { this.image = image; }


  
  public String getCount() { return this.count; }


  
  public void setCount(String count) { this.count = count; }


  
  public String getType() { return this.type; }


  
  public void setType(String type) { this.type = type; }


  
  public PeopleCount(String name, String count) {
    this.name = name;
    this.count = count;
  }


  
  public PeopleCount() {}


  
  public PeopleCount(String name, String image, String count, String type) {
    this.name = name;
    this.image = image;
    this.count = count;
    this.type = type;
  }
}
