package com.ss.spider.system.sequence.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "CW_APP_SEQUENCE")
public class AppSequence implements Serializable {

    private static final long serialVersionUID = 5111283507681274257L;
    @Id
    @Column(name = "SEQ_CODE")
    private String seqCode;
    @Column(name = "SEQ_NAME")
    private String seqName;
    @Column(name = "START_VAL")
    private Integer startVal;
    @Column(name = "MAX_VAL")
    private Integer maxVal;
    @Column(name = "CURR_VAL")
    private Integer currVal;
    @Column(name = "NEXT_VAL")
    private Integer nextVal;
    @Column(name = "STEP")
    private Integer step;
    @Column(name = "ISCYCLE")
    private Integer iscycle;
    @Column(name = "REMARK")
    private String remark;

    public String getSeqCode() {
        return this.seqCode;
    }


    public void setSeqCode(String seqCode) {
        this.seqCode = seqCode;
    }


    public String getSeqName() {
        return this.seqName;
    }


    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }


    public Integer getStartVal() {
        return this.startVal;
    }


    public void setStartVal(Integer startVal) {
        this.startVal = startVal;
    }


    public Integer getMaxVal() {
        return this.maxVal;
    }


    public void setMaxVal(Integer maxVal) {
        this.maxVal = maxVal;
    }


    public Integer getCurrVal() {
        return this.currVal;
    }


    public void setCurrVal(Integer currVal) {
        this.currVal = currVal;
    }


    public Integer getNextVal() {
        return this.nextVal;
    }


    public void setNextVal(Integer nextVal) {
        this.nextVal = nextVal;
    }


    public Integer getStep() {
        return this.step;
    }


    public void setStep(Integer step) {
        this.step = step;
    }


    public Integer getIscycle() {
        return this.iscycle;
    }


    public void setIscycle(Integer iscycle) {
        this.iscycle = iscycle;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }

}
