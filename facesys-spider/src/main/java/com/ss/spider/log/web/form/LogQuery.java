package com.ss.spider.log.web.form;

import com.ss.annotation.LongLength;
import com.ss.request.Pagination;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


public class LogQuery extends Pagination {

    private String keyword;
    private String userId;
    @Length(max = 32, message = "操作用户不超过{max}个字符")
    private String opUserId;
    @Length(max = 32, message = "访问者IP不超过{max}个字符")
    private String ip;
    private String url;
    @Length(max = 10, message = "模块编号不超过{max}个字符")
    private String moduleCode;
    @LongLength(min = 0L, max = 16L, message = "操作开始时间不超过{max}个字符")
    private Long operTimeB;
    @LongLength(min = 0L, max = 16L, message = "操作结束时间不超过{max}个字符")
    private Long operTimeE;
    @Length(max = 64, message = "操作人姓名不超过{max}个字符")
    private String name;
    @Length(max = 10, message = "操作类型不超过{max}个字符")
    private String type;
    @Range(min = 1L, max = 3L, message = "日志类型超出范围{min}-{max}")
    private Short logType;
    @Range(max = 10L, message = "操作结果超出范围{min}-{max}")
    private Short isSuccess;
    @Length(max = 32, message = "警员编号不超过{max}个字符")
    private String workCode;
    @Length(max = 1024, message = "描述不超过{max}个字符")
    private String desc;
    private String inPara;
    private String outPara;

    public String getKeyword() {
        return this.keyword;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getIp() {
        return this.ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getUrl() {
        return this.url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getModuleCode() {
        return this.moduleCode;
    }


    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }


    public Long getOperTimeB() {
        return this.operTimeB;
    }


    public void setOperTimeB(Long operTimeB) {
        this.operTimeB = operTimeB;
    }


    public Long getOperTimeE() {
        return this.operTimeE;
    }


    public void setOperTimeE(Long operTimeE) {
        this.operTimeE = operTimeE;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return this.type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Short getLogType() {
        return this.logType;
    }


    public void setLogType(Short logType) {
        this.logType = logType;
    }


    public String getWorkCode() {
        return this.workCode;
    }


    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }


    public String getDesc() {
        return this.desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getInPara() {
        return this.inPara;
    }


    public void setInPara(String inPara) {
        this.inPara = inPara;
    }


    public String getOutPara() {
        return this.outPara;
    }


    public void setOutPara(String outPara) {
        this.outPara = outPara;
    }


    public String getOpUserId() {
        return this.opUserId;
    }


    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }


    public Short getIsSuccess() {
        return this.isSuccess;
    }


    public void setIsSuccess(Short isSuccess) {
        this.isSuccess = isSuccess;
    }

}
