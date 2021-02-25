package com.ss.facesys.data.system.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "cw_system_message")
public class SystemMessage implements Serializable {

  private static final long serialVersionUID = 6035225092199121479L;

  @Id
  private Integer id;
  @Column(name = "content")
  private String content;
  @Column(name = "status")
  private Integer status;
  @Column(name = "create_time")
  private Long createTime;
  @Column(name = "user_id")
  private String userId;
  @Column(name = "tenant_id")
  private String tenantId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }
}
