package com.notebook_domain.domain;

import java.util.Date;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/3/30 13:20
 */
public class TrashStation {
    private Integer tsid;
    private Integer uid;
    private Integer nid;
    private Date created;
    private String notebookName;

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    public Integer getTsid() {
        return tsid;
    }

    public void setTsid(Integer tsid) {
        this.tsid = tsid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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


    @Override
    public String toString() {
        return "TrashStation{" +
                "tsid=" + tsid +
                ", uid=" + uid +
                ", nid=" + nid +
                ", created=" + created +
                ", notebookName='" + notebookName + '\'' +
                '}';
    }
}
