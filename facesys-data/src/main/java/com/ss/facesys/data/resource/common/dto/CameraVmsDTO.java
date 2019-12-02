package com.ss.facesys.data.resource.common.dto;

import java.io.Serializable;




























public class CameraVmsDTO
  implements Serializable
{
  private static final long serialVersionUID = 202490291783921L;
  private String ip;
  private String port;
  private String userName;
  private String password;
  
  public String getIp() { return this.ip; }


  
  public void setIp(String ip) { this.ip = ip; }


  
  public String getPort() { return this.port; }


  
  public void setPort(String port) { this.port = port; }


  
  public String getUserName() { return this.userName; }


  
  public void setUserName(String userName) { this.userName = userName; }


  
  public String getPassword() { return this.password; }


  
  public void setPassword(String password) { this.password = password; }



  
  public String toString() { return "CameraVmsDTO [ip=" + this.ip + ", port=" + this.port + ", userName=" + this.userName + ", password=" + this.password + "]"; }
}
