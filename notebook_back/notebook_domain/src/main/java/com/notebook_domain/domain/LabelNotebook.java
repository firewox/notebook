package com.notebook_domain.domain;

import java.util.Date;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/3/30 14:35
 */
public class LabelNotebook {
    private Integer lnid;
    private Integer uid;
    private Integer lid;
    private Integer nid;
    private Date created;
    private Integer interval = 0;
    private Integer repetition = 0;
    private Double easinessFactor = 2.5;
    private Integer finished = 0;
    private String labelNotebookName;
    private String labelNotebookContent;
    private String labelName;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getLnid() {
        return lnid;
    }

    public void setLnid(Integer lnid) {
        this.lnid = lnid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getRepetition() {
        return repetition;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }

    public Double getEasinessFactor() {
        return easinessFactor;
    }

    public void setEasinessFactor(Double easinessFactor) {
        this.easinessFactor = easinessFactor;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public String getLabelNotebookName() {
        return labelNotebookName;
    }

    public void setLabelNotebookName(String labelNotebookName) {
        this.labelNotebookName = labelNotebookName;
    }

    public String getLabelNotebookContent() {
        return labelNotebookContent;
    }

    public void setLabelNotebookContent(String labelNotebookContent) {
        this.labelNotebookContent = labelNotebookContent;
    }

    @Override
    public String toString() {
        return "LabelNotebook{" +
                "lnid=" + lnid +
                ", uid=" + uid +
                ", lid=" + lid +
                ", nid=" + nid +
                ", created=" + created +
                ", interval=" + interval +
                ", repetition=" + repetition +
                ", easinessFactor=" + easinessFactor +
                ", finished=" + finished +
                ", labelNotebookName='" + labelNotebookName + '\'' +
                ", labelNotebookContent='" + labelNotebookContent + '\'' +
                ", labelName='" + labelName + '\'' +
                '}';
    }
}
