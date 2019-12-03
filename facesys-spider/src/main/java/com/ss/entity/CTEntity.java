package com.ss.entity;

import javax.persistence.Column;

/**
 * CTEntity
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public class CTEntity implements ITableEntity {

    private static final long serialVersionUID = 5396928504857978911L;

    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "create_user_id")
    private String createUserId;

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return this.createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
}
