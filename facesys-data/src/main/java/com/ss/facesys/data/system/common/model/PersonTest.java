package com.ss.facesys.data.system.common.model;

import java.io.Serializable;


















public class PersonTest
  implements Serializable
{
  private static final long serialVersionUID = -2954360696463272777L;
  private String id;
  private String name;
  private int age;
  
  public PersonTest() {}
  
  public PersonTest(String id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }





  
  public String getId() { return this.id; }







  
  public void setId(String id) { this.id = id; }






  
  public String getName() { return this.name; }







  
  public void setName(String name) { this.name = name; }






  
  public int getAge() { return this.age; }







  
  public void setAge(int age) { this.age = age; }








  
  public PersonTest(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
