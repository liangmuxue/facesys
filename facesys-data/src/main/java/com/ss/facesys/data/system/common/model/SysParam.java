package com.ss.facesys.data.system.common.model;

import java.io.Serializable;
import javax.persistence.Table;























@Table(name = "tbl_iss_sys_param_info")
public class SysParam
  implements Serializable
{
  private static final long serialVersionUID = 7753893750446928937L;
  private String id;
  private String paramID;
  private String paramKey;
  private String paramValue;
  private String remark;
  private String type;
  
  public String getType() { return this.type; }



  
  public void setType(String type) { this.type = type; }


  
  public String getId() { return this.id; }


  
  public void setId(String id) { this.id = id; }






  
  public String getParamID() { return this.paramID; }







  
  public void setParamID(String paramID) { this.paramID = paramID; }



  
  public String getParamKey() { return this.paramKey; }



  
  public void setParamKey(String paramKey) { this.paramKey = paramKey; }



  
  public String getParamValue() { return this.paramValue; }



  
  public void setParamValue(String paramValue) { this.paramValue = paramValue; }



  
  public String getRemark() { return this.remark; }



  
  public void setRemark(String remark) { this.remark = remark; }
}
